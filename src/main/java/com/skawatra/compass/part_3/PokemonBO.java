package com.skawatra.compass.part_3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonBO {

    PokemonDO dataAccessObj = new PokemonDO();

    private static final String ERROR_MESSAGE_FORMAT = "ERROR: %s";

    public List<List<String>> getAvgWeightByPokemonType() {
        String sql = "SELECT typeName, AVG(weight) AS avgWeight FROM pokemon INNER JOIN pokemonTypes ON pokemon.Id = pokemonTypes.pokemonId GROUP BY typeId";
        ResultSet interimOutput = dataAccessObj.getData(sql);
        List<List<String>> output = new ArrayList<List<String>>();
        List<String> row = null;
        try {
            while (interimOutput.next()) {
                row = new ArrayList<String>();
                row.add(interimOutput.getString("typeName"));
                row.add(String.valueOf(interimOutput.getDouble("avgWeight")));
                output.add(row);
            }
        }catch (SQLException e) {
            System.out.println(String.format(ERROR_MESSAGE_FORMAT,e.getMessage()));
            return null;
        }
        return output;
    }

    public List<List<String>> getHighestAccuracyByPokemonType() {
        String sql = "SELECT typeName, moveName, MAX(accuracy) as maxAcc FROM pokemonMoves INNER JOIN pokemonTypes ON pokemonMoves.pokemonId = pokemonTypes.pokemonId GROUP BY typeId;";
        ResultSet interimOutput = dataAccessObj.getData(sql);
        List<List<String>> output = new ArrayList<List<String>>();
        List<String> row = null;
        try {
            while (interimOutput.next()) {
                row = new ArrayList<String>();
                row.add(interimOutput.getString("typeName"));
                row.add(interimOutput.getString("moveName"));
                row.add(String.valueOf(interimOutput.getInt("maxAcc")));
                output.add(row);
            }
        }catch (SQLException e) {
            System.out.println(String.format(ERROR_MESSAGE_FORMAT,e.getMessage()));
            return null;
        }
        return output;
    }

    public List<List<String>> getNumMovesByPokemon() {
        String sql = "SELECT name, numMoves from pokemon order by numMoves DESC";
        ResultSet interimOutput = dataAccessObj.getData(sql);
        List<List<String>> output = new ArrayList<List<String>>();
        List<String> row = null;
        try {
            while (interimOutput.next()) {
                row = new ArrayList<String>();
                row.add(interimOutput.getString("name"));
                row.add(String.valueOf(interimOutput.getInt("numMoves")));
                output.add(row);
            }
        }catch (SQLException e) {
            System.out.println(String.format(ERROR_MESSAGE_FORMAT,e.getMessage()));
            return null;
        }
        return output;
    }
}
