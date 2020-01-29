package com.skawatra.compass.part_3;

import java.util.List;

/**
 * OVERVIEW: Class exposing functions for the pokemon services
 */
public class PokemonPull {
    public static List<List<String>> getAvgWeightByPokemonType() {
        PokemonBO obj = new PokemonBO();
        return obj.getAvgWeightByPokemonType();
    }

    public static List<List<String>> getHighestAccuracyByPokemonType() {
        PokemonBO obj = new PokemonBO();
        return obj.getHighestAccuracyByPokemonType();
    }

    public static List<List<String>> getNumMovesByPokemon() {
        PokemonBO obj = new PokemonBO();
        return obj.getNumMovesByPokemon();
    }
}
