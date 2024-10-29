import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import Database.*;

public class Main {
    public static void main(String[] args) throws Exception {
        DB database = new DB("pokemondb", "root", "");
        API Pokemonapi = new API("https://pokeapi.co/api/v2/pokemon/");
        API Giphyapi = new API("https://api.giphy.com/v1/gifs/search", "3SdzK14EkF04p5jKUX09B3rRmdkrGGjj");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Inserisci il pokemon da visualizzare, scrivi [exit] per terminare il programma oppure [show] per mostrare tutti i pokemon salvati: ");
            String op = scanner.nextLine();

            if(op.equals("exit")){
                break;
            }

            if(op.equals("show")){
                System.out.println(database.selectAll("pokemon"));
                continue;
            }

            Pokemon pok = Pokemonapi.GETPokemonRequest(op);
            if (pok == null) {
                System.out.println("Pokemon non trovato. Riprova.");
                continue;
            }

            GIF gif = Giphyapi.GETGifRequest(op);
            if (gif == null) {
                System.out.println("Gif non trovata. Riprova.");
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
            String gif_url = gif.getData().getFirst().getImages().getOriginal().getUrl();
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(gif_url));
            }

            System.out.print("Se vuoi salvarlo nel database digita [1], altrimenti [0]: ");
            int opt = scanner.nextInt();
            scanner.nextLine();
            if (opt == 1) {
                database.insert("pokemon", pok.getId(), pok.getName(), types[0], types[1], gif_url);
            }
        }

        scanner.close();
    }
}