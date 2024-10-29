package Database;

import java.sql.*;

public class DB {
    private Connection conn;

    public DB(String dbName, String username, String password) throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + "?user=" + username + "&password=" + password);
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

    }

    public String selectAll(String table) throws SQLException {

        try{
            if(!conn.isValid(5))
                return null;
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }

        String result = "";
        String query = "SELECT * FROM " + table;

        try{
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) { //preso per visualizzare in tabella
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    result += rs.getString(i) + "\t";
                    if (rs.getString(i).length() < 8) result += "\t";
                }
                result += "\n";
            }
        }
        catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }

        return result;
    }

    public boolean insert(String table, int id, String name, String type1, String type2, String gif) {

        try{
            if(!conn.isValid(5))
                return false;
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }

        String query = "INSERT INTO " + table + " (pokedex_number, name, type1, type2, gif_url) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, type1);
            stmt.setString(4, type2);
            stmt.setString(5, gif);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deleteByName(String table, String nome) {

        try{
            if(!conn.isValid(5))
                return false;
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }

        String query = "DELETE FROM " + table + " WHERE name = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nome);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }

            return true;
    }
}
