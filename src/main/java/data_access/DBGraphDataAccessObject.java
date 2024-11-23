package data_access;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import use_case.main.GraphDataAccessInterface;
import use_case.note.DataAccessException;

import java.util.Map;

public class DBGraphDataAccessObject implements GraphDataAccessInterface {

    private Connection connection;

    @Override
    public String saveGraph(Map<String, String> graphMap) throws DataAccessException {
        String insertQuery = "INSERT INTO Graph (graph_key, graph_value) VALUES (?, ?)";

        try {
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate("DELETE FROM Graph");

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                for (Map.Entry<String, String> entry : graphMap.entrySet()) {
                    preparedStatement.setString(1, entry.getKey());
                    preparedStatement.setString(2, entry.getValue());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }

            connection.commit();
            return "Graph data saved successfully.";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                throw new DataAccessException("Error during rollback: " + rollbackException.getMessage(), rollbackException);
            }
            throw new DataAccessException("Error saving graph data: " + e.getMessage(), e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DataAccessException("Error resetting auto-commit: " + e.getMessage(), e);
            }
        }
    }


    @Override
    public String loadGraph() throws DataAccessException {
        String selectQuery = "SELECT graph_key, graph_value FROM Graph";
        Map<String, String> graphMap = new HashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String key = resultSet.getString("graph_key");
                String value = resultSet.getString("graph_value");
                graphMap.put(key, value);
            }

            // Convert the map to a JSON-like string for easy readability
            return graphMap.toString();
        } catch (SQLException e) {
            throw new DataAccessException("Error loading graph data: " + e.getMessage(), e);
        }
    }

}
