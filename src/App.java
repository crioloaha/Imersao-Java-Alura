import java.io.File;
import java.io.FileInputStream;
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

        // fazer uma conexão HTTP e buscar os top 10 filmes de acordo com IMDB
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String IMDB_KEY = System.getenv("IMDB_KEY");
        System.out.println(IMDB_KEY);

        // String url = "https://imdb-api.com/en/API/TopTVs/" + IMBD_KEY;  (API da imdb com instabilidade, usando arquivo pronto disponibilizado pela Alura)
        
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeSeries = parser.parse(body);

        // exibir e manipular os dados 
        var gerador = new CriadorDeFigurinhas();

        var diretorio = new File("saida/");
        diretorio.mkdir();

        for (int i = 0; i < 10; i++) {
            Map<String,String> serie = listaDeSeries.get(i);

            String urlImage = serie.get("image");
            String titulo = serie.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = titulo + ".png";      

           
            System.out.println("\u001b[37;1mTitulo:\u001b[44m " + serie.get("title") + " \u001b[0m");
            System.out.println("\u001b[37;1mPoster:" + " \u001b[0m" + serie.get("image"));
            double imDbRating = Double.parseDouble(serie.get("imDbRating"));
            System.out.println(imDbRating);
            double rating = Double.parseDouble(serie.get("imDbRating"));
            int starNumber = (int) rating;
                    for(int n = 1; n <= starNumber; n++){
                        System.out.print("🌟");
                    }
                    if (rating > 9) {
                    System.out.print(" Mais bem avaliado \uD83D\uDC4F");
                    }
                System.out.println("\n");
                
            
            String texto;
            
            InputStream selo;
                if (imDbRating >= 8.5 ){
                    texto = "filme recomendado";
                    selo = new FileInputStream("selos/aprovado.png");
                }else{
                    texto = "não recomendado";
                    selo = new FileInputStream("selos/reprovado.png");
                }
            String classificacao = "classificacao";
            gerador.Criar(inputStream, "saida/" + nomeArquivo, texto, selo, classificacao);

          
        } 
    }
}