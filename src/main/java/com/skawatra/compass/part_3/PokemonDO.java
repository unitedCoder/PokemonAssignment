package com.skawatra.compass.part_3;

import java.sql.*;

public class PokemonDO {

    // SQLite Connection string
    private static final String connectionString = "jdbc:sqlite:/Users/skawatra/testDB.db";

    private static final String ERROR_MESSAGE_FORMAT = "ERROR: %s";

    public ResultSet getData(String sql) {
        ResultSet output = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = this.connect();
            pstmt = conn.prepareStatement(sql);
            output = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(String.format(ERROR_MESSAGE_FORMAT,e.getMessage()));
            return null;
        } finally {
            pstmt = null;
        }
        return output;
    }

    /* region: Utility function */
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
