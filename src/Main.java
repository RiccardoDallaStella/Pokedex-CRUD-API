import Database.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DB database = new DB("dbesempio", "root", "");
        System.out.println(database.selectAll("persone"));

        if (database.insert("persone", "Marco", "Rossi", 18, "m")) {
            System.out.println(database.selectAll("persone"));
        } else {
            System.out.println("Inserimento non avvenuto!");
        }
        System.out.println();

        if (database.updateById(3, "persone", "Mario", "Bianchi", 19, "m")) {
            System.out.println(database.selectAll("persone"));
        } else {
            System.out.println("Aggiornamento non avvenuto!");
        }
        System.out.println();

        if (database.deleteByName("persone", "Mario")) {
            System.out.println(database.selectAll("persone"));
        } else {
            System.out.println("Eliminazine non avvenuta!");
        }
    }
}