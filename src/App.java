import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexao HTTP e buscar os TOP 250 filmes

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // extrair (parciar) os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> movie: listaDeFilmes) {
            System.out.println("\u001b[1m\u001b[40mRank: " + movie.get("rank") + " \u001b[m");
            System.out.println("\u001b[1mTítulo:\u001b[m " + movie.get("title"));
            System.out.println("\u001b[1mBanner URL:\u001b[m " + movie.get("image"));
            System.out.println("\u001b[1mAvaliação:\u001b[m " + movie.get("imDbRating"));
            double rating = Double.parseDouble(movie.get("imDbRating"));
            int starNumber = (int) rating;
            for(int n = 1; n <= starNumber; n++){
                System.out.print("🌟");
            }
            if (rating > 9) {
                System.out.print("  Mais bem avaliado \uD83D\uDC4F");
            }
            System.out.println("\n");

            // Imprime a imagem
            String capaUrl = movie.get("image");
            if (capaUrl != null && !capaUrl.isEmpty()) {
                System.out.println("Imagem da capa:");
                URI capaUri = URI.create(capaUrl);
                BufferedImage img = ImageIO.read(capaUri.toURL());
                System.out.println(img);
            }
        }
        
    }
}
