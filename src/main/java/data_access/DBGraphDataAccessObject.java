package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import entity.Graph;
import use_case.main.GraphDataAccessInterface;
import use_case.note.DataAccessException;

public class DBGraphDataAccessObject implements GraphDataAccessInterface {

    private Connection connection;

    @Override
    public String saveGraph(Map<String, String> graphMap) throws DataAccessException {
        String insertQuery = "INSERT INTO Graph (equation, path_to_image) VALUES (?, ?)";

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
    public List<Map<String, String>> loadAllGraphs() throws DataAccessException {
    String selectQuery = "SELECT equation, path_to_image FROM Graph";
    List<Map<String, String>> graphList = new ArrayList<>();

    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            Map<String, String> graphMap = new HashMap<>();
            graphMap.put("equation", resultSet.getString("equation"));
            graphMap.put("path_to_image", resultSet.getString("path_to_image"));

            graphList.add(graphMap);
        }
    } catch (SQLException e) {
        throw new DataAccessException("Error loading graph data - " + e.getMessage(), e);
    }

    return graphList;
}
}
