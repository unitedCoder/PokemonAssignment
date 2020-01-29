package com.skawatra.compass.part_2;

import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.*;

import java.util.List;

/**
 * OVERVIEW: Class implements business logic for the pokemon services
 */
public class PokemonBO {

    // API object required to get data from the pokemon services
    private PokeApiClient pokeApiObj = null;

    private static final String ERROR_MESSAGE_FORMAT = "ERROR: %s";
    private static final String INFO_MESSAGE_FORMAT = "INFO: %s";


    PokemonBO() {
        pokeApiObj = new PokeApiClient();
        System.out.println(String.format(INFO_MESSAGE_FORMAT,"API object created."));
    }


    /**
     * Gets pokemons and relevant details from the api
     * and inserts into the datasore
     * 1. Pokemon
     * 2. Pokemon Types
     * 3. Pokemon Moves
     * @param numPokemon
     * @return the number of pokemon stored successfully in db
     */
    public int putPokemons(int numPokemon) {

        Pokemon currentP = null;
        List<PokemonMove> pMoves = null;
        int pWeight = -1, accuracy = -1, stored = 0;
        List<PokemonType> pTypes = null;
        Move currentM = null;

        System.out.println(String.format("INFO: Request to store - %d pokemon(s).",numPokemon));

        NamedApiResourceList pokemonList = pokeApiObj.getPokemonList(0,numPokemon);
        System.out.println(String.format(INFO_MESSAGE_FORMAT,"Pokemon List retrieved"));
        for(NamedApiResource pokemon: pokemonList.getResults()) {
            currentP = pokeApiObj.getPokemon(pokemon.getId());
            // Pokemon weight
            pWeight = currentP.getWeight();
            // Pokemon moves
            pMoves = currentP.getMoves();
            // Pokemon types
            pTypes = currentP.getTypes();

            // store in db
            PokemonDO obj = new PokemonDO();

            // pokemon
            obj.insertPokemon(currentP.getId(),pokemon.getName(),pWeight,pMoves.size());

            for(PokemonMove pMove:pMoves) {
                currentM = this.getPokemonMove(pMove.getMove().getId());
                // Pokemon accuracy
                accuracy = currentM.getAccuracy()==null?0:currentM.getAccuracy();
                // pokemon move
                obj.insertPokemonMove(
                        currentM.getId(),
                        currentM.getName(),
                        currentP.getId(),
                        accuracy
                );
            }


            for(PokemonType pType:pTypes) {
                //pokemon type
                obj.insertPokemonType(
                        pType.getType().getId(),
                        pType.getType().getName(),
                        currentP.getId()
                );
            }
            stored++;
        }
        return stored;
    }


    /**
     * Gets the Move object associated given the moveId
     * @param moveId
     * @return
     */
    private Move getPokemonMove(int moveId){
        if(moveId<=0) {
            return null;
        }
        return pokeApiObj.getMove(moveId);
    }



}
