package com.skawatra.compass.part_2;

/**
 * OVERVIEW: Class to push pokemon data to the datastore
 */
public class PokemonPush {

    /**
     * Function to push the pokemons to the datastore
     * @param numPokemon The number of pokemons to be pushed
     * @return
     */
    public static boolean push(int numPokemon) {

        // invalid input check
        if (numPokemon <= 0) {
            return false;
        }
        try {
            PokemonBO obj = new PokemonBO();
            int stored = obj.putPokemons(numPokemon);
            System.out.println(String.format("INFO: Pokemon stored - %d.",stored));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
