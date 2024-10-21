package Database;

import java.sql.*;

public class DB {
    private Connection conn;

    public DB(String dbName, String username, String password) throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + "?user=" + username + "&password=" + password);
            if (conn != null)
                System.out.println("Connessione avvenuta con successo");
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

    }

    public String select(String field, String table, String search, String value) throws SQLException {

        try{
            if(!conn.isValid(5))
                return null;
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }

        String result = "";
        String query = "SELECT " + field + " FROM " + table + " WHERE " + search + " = '" + value + "'";

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

    public boolean insert(String table, String nome, String cognome, int eta, String sesso) {

        try{
            if(!conn.isValid(5))
                return false;
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }

        String query = "INSERT INTO " + table + " (nome, cognome, eta, sesso) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, cognome);
            stmt.setInt(3, eta);
            stmt.setString(4, sesso);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean updateById(int id, String table, String nome, String cognome, Integer eta, String sesso) {

        try{
            if(!conn.isValid(5))
                return false;
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }

        String query = "UPDATE " + table + " SET nome = ?, Cognome = ?, eta = ?, Sesso = ? WHERE ID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, cognome);
            stmt.setInt(3, eta);
            stmt.setString(4, sesso);
            stmt.setInt(5, id);
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

        String query = "DELETE FROM " + table + " WHERE Nome = ?";

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
