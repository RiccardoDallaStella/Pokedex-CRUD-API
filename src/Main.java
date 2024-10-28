import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import Database.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        DB database = new DB("pokemondb", "root", "");
        API api = new API("https://pokeapi.co/api/v2/pokemon/");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Inserisci il pokemon da visualizzare o scrivi no per terminare il programma: ");
            String op = scanner.nextLine();

            if(op.equals("no")){
                break;
            }

            Pokemon pok = api.GETRequest(op);
            if (pok == null) {
                System.out.println("Pokemon non trovato. Riprova.");
                continue;
            }

            List<Pokemon.Types> typesList = pok.getTypes();
            String[] types = {"", ""};

            if (typesList != null) {
                for (int i = 0; i < typesList.size(); i++) {
                    if (typesList.get(i).getType().getName() != null) {
                        types[i] = typesList.get(i).getType().getName();
                    }
                }
            }

            System.out.println("Nome: " + pok.getName());
            System.out.println("Tipo 1: " + types[0]);
            if (!types[1].equals("")) {
                System.out.println("Tipo 2: " + types[1]);
            }

            System.out.print("Se vuoi salvarlo nel database digita, 1 altrimenti 0: ");
            int opt = scanner.nextInt();
            scanner.nextLine();
            if (opt == 1) {
                database.insert("pokemon", pok.getId(), pok.getName(), types[0], types[1]);
            }
        }

        scanner.close();
    }
}