import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
            // fazer conexão http

        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);


        // extrair informaçoes (titulo, nota , poste)

        JsonParser parser = new JsonParser();

        List<Map<String,String>> listaFilmes = parser.parse(body);
        // manipular os dados

        final String starcode = "\u2B50";
        for (Map<String,String> filme : listaFilmes){

            String rating = filme.get("imDbRating");
            int nota = Integer.parseInt(rating.substring(0, 1));
            
            String star ="";

            for (int i = 0; i < nota; i++) {
                 star += starcode;
            }

            System.out.println("\u001b[1m Tiulo: \u001b[m \u001b[2m"+filme.get("title")+"\u001b[m");
            System.out.println("\u001b[1m Capa: \u001b[m \u001b[2m"+filme.get("image")+"\u001b[m");
            System.out.println("\u001b[1m Nota: \u001b[m \u001b[31m"+filme.get("imDbRating")+"\u001b[m");
            System.out.println("\u001b[44m"+star+" \u001b[m\n");

        }
    }
}
