import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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

        // extrair informaçoes (titulo, nota , poste)

        JsonParser parser = new JsonParser();

        List<Map<String,String>> listaFilmes = parser.parse(body);
        // manipular os dados

        // final String starcode = "\u2B50";
        GeraStickers geraStickers = new GeraStickers();
        for (Map<String,String> filme : listaFilmes){

            String URLImagem = filme.get("image");

            // Removendo o parametro que reduz a resolução da imagem
            int LengthURL = URLImagem.length();
            String parametroARemover = URLImagem.substring(LengthURL-32, LengthURL-4);
            URLImagem = URLImagem.replace(parametroARemover, "");
            
            // Pegando titulo do filme
            String titulo = filme.get("title");
            String nomeArquivo = titulo + ".png";

            InputStream inputStream = new URL(URLImagem).openStream();

            geraStickers.criar(inputStream, nomeArquivo);

            // String rating = filme.get("imDbRating");
            // int nota = Integer.parseInt(rating.substring(0, 1));
            
            // String star ="";

            // for (int i = 0; i < nota; i++) {
            //      star += starcode;
            // }

            System.out.println("\u001b[44m \u001b[1m Tiulo: \u001b[m \u001b[2m"+titulo+"\u001b[m \n");
            // System.out.println("\u001b[1m Capa: \u001b[m \u001b[2m"+filme.get("image")+"\u001b[m");
            // System.out.println("\u001b[1m Nota: \u001b[m \u001b[31m"+filme.get("imDbRating")+"\u001b[m");
            // System.out.println("\u001b[44m"+star+" \u001b[m\n");

        }
    }
}
