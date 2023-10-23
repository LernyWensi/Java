package Lab_9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Database {
    private Connection connection = null;
    private Statement statement = null;
    private HashMap<String, String> selectForTables = new HashMap<String, String>();

    public Database(
            String currentPath) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + currentPath + "/src/Lab_8/main.sqlite");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            selectForTables.put("Newspapers", "Name, ApproximateAudience, TargetAudience, AnnuallyCirculation,"
                    + "(select Holdings.Name from Holdings where Holdings.HoldingID = Newspapers.HoldingID) as BelongsTo");

            selectForTables.put("TVChannels", "Name, ApproximateAudience, TargetAudience, Type,"
                    + "(select Holdings.Name from Holdings where Holdings.HoldingID = TVChannels.HoldingID) as BelongsTo");

            selectForTables.put("Holdings", "Name,"
                    + "(select count (*) from Newspapers where Holdings.HoldingID = Newspapers.HoldingID) as NumberOfNewspapers,"
                    + "(select count (*) from TVChannels where Holdings.HoldingID = TVChannels.HoldingID) as NumberOfTVChannels");
        }
    }

    public JTable getTable(String table) {
        ArrayList<ArrayList<String>> rowsData = new ArrayList<ArrayList<String>>();
        ArrayList<String> columnsNames = new ArrayList<String>();

        try {
            ResultSet resultSet = null;

            if (selectForTables.containsKey(table)) {
                resultSet = statement
                        .executeQuery(String.format("select %s from %s", selectForTables.get(table), table));
            } else {
                resultSet = statement.executeQuery(String.format(
                        "select Name, ApproximateAudience, 'Newspaper' as Type from Newspapers where Newspapers.HoldingID = (select HoldingID from Holdings where Name = '%s')"
                                + " union select Name, ApproximateAudience, 'TVChannel' as Type from TVChannels where TVChannels.HoldingID = (select HoldingID from Holdings where Name = '%s')",
                        table,
                        table));
            }

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                columnsNames.add(resultSetMetaData.getColumnName(i));
            }

            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<String>();

                for (String columnName : columnsNames) {
                    row.add(resultSet.getString(columnName));
                }

                rowsData.add(row);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        DefaultTableModel tableModel = new DefaultTableModel(columnsNames.toArray(), 0);
        rowsData.forEach(row -> tableModel.addRow(row.toArray()));

        return new JTable(tableModel);
    }

    public void closeConnection() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

    }
}
