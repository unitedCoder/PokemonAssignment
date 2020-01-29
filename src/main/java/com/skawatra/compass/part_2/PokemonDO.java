package com.skawatra.compass.part_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PokemonDO {

    // SQLite Connection string
    private String connectionString = "jdbc:sqlite:/Users/skawatra/testDB.db";

    private static final String ERROR_MESSAGE_FORMAT = "ERROR: %s";

    PokemonDO() {
        // use default value
    }

    PokemonDO(String connectionString) {
        this.connectionString = connectionString;
    }


    /**
     * Inserts pokemon details in the `Pokemon` table
     * @param id pokemon id
     * @param name pokemon name
     * @param weight pokemon weight
     * @param numMoves number of moves possible by a pokemon
     * @return
     */
    public boolean insertPokemon(int id, String name, int weight, int numMoves) {
        String sql = "INSERT INTO pokemon(id,name,weight,numMoves) VALUES(?,?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, weight);
            pstmt.setInt(4, numMoves);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(String.format(ERROR_MESSAGE_FORMAT,e.getMessage()));
            System.out.println(
                    String.format("ERROR: Error while insertion (pokemon). id: %d, name: %s, weight: %d, numMoves: %d",id,name,weight,numMoves)
            );
            return false;
        }
        return true;
    }

    /**
     * Inserts the Moves associated with a pokemon
     * @param moveId
     * @param moveName
     * @param pokemonId
     * @param accuracy
     * @return
     */
    public boolean insertPokemonMove(int moveId, String moveName, int pokemonId, int accuracy) {
        String sql = "INSERT INTO pokemonMoves(moveId,moveName,pokemonId,accuracy) VALUES(?,?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, moveId);
            pstmt.setString(2, moveName);
            pstmt.setInt(3, pokemonId);
            pstmt.setInt(4, accuracy);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(String.format(ERROR_MESSAGE_FORMAT,e.getMessage()));
            System.out.println(
                    String.format("ERROR: Error while insertion (pokemonMove). id: %d, name: %s, pokemonId: %d, accuracy: %d",moveId,moveName,pokemonId,accuracy)
            );
            return false;
        }
        return true;
    }

    /**
     * Inserts the pokemon type details associated with a pokemon
     * @param typeId
     * @param typeName
     * @param pokemonId
     * @return
     */
    public boolean insertPokemonType(int typeId, String typeName, int pokemonId) {
        String sql = "INSERT INTO pokemonTypes(typeId,typeName,pokemonId) VALUES(?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeId);
            pstmt.setString(2, typeName);
            pstmt.setInt(3, pokemonId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(String.format(ERROR_MESSAGE_FORMAT,e.getMessage()));
            System.out.println(
                    String.format("ERROR: Error while insertion (pokemonType). typeId: %d, typeName: %s, pokemonId: %d",typeId,typeName,pokemonId)
            );
            return false;
        }
        return true;
    }

    /* region: Utility */

    /**
     * Function returns a connection object with the datastore
     * @return
     */
    private Connection connect() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.connectionString);
        } catch (SQLException e) {
            System.out.println(String.format(ERROR_MESSAGE_FORMAT,e.getMessage()));
        }
        return conn;
    }
}
