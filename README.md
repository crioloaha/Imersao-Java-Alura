## Imersão JAVA

## Aula 3

Refatorando o App, transferindo parte do código em novas classes.

Novas classes criada para:
 - Conteudo
 - ClienteHttp

Criando uma interface para as novas classes extratoras:
 - ExtratorConteudoIMDB
 - ExtratorConteudoNasa
 
## Modificado da Aula 2
1 - Criado a classe ClienteHttp, alteramos todo o código que buscava os dados em .json para im objeto do tipo ClienteHttp.

      // var http = new ClienteHttp();
         String json = http.buscaDados(url);
         
2 - O mesmo para os ExtratorConteudo.

         // fazer uma conexão HTTP e buscar os top 3 Séries de acordo com IMDB
         String API_KEY = System.getenv("API_KEY");
         System.out.println(API_KEY);

         // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
         // ExtratorConteudo extrator = new ExtratorConteudoIMDB();

         String url = "https://api.nasa.gov/planetary/apod?api_key=" + API_KEY + "&start_date=2022-07-4&end_date=2022-07-10";
         ExtratorConteudo extrator = new ExtratorConteudoNasa();
         
3 - Criação de uma lista de conteudos

        // List<Conteudo> conteudos = extrator.extrairConteudos(json);

4 -No looping que gera as imagens pegamos cada conteudo da lista, chamando suas propriedades pelos metodos .getTitulo() e .getUrlImage()

        // for (int i = 0; i < 3; i++) {

             Conteudo conteudo = conteudos.get(i);

             InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
             String nomeArquivo = conteudo.getTitulo();      

             System.out.println("\u001b[37;1mTitulo:\u001b[44m " + conteudo.getTitulo() + " \u001b[0m");
             System.out.println("\u001b[37;1mPoster:" + " \u001b[0m" + conteudo.getUrlImagem());

             gerador.Criar(inputStream, nomeArquivo);

             System.out.println("\n");
         }
         
         
Algumas modificações do Gerador de Figurinhas
1 - Retirada a sobreposição de selos("aprovado"/"reprovado").

2 - Variavel "texto" subistituida pelo próprio titulo da imagem("nomeArquivo").
           graphics.drawString(nomeArquivo, posXTexto, posYTexto);
      
3 - Diretório de saida e formato da imagem definidos no ImageIO.write().
          ImageIO.write(novaImagen, "png", new File("saida/" + nomeArquivo + ".png"));

## Desafios Aula 3
1 - Transformando a classe Conteudo para record.

    // public record Conteudo(String titulo, String urlImagem){}

Nos metodos que retornam o titulo e a url de Conteudo é retirado a expressão "get"

      //.getTtitulo()

      .getUurlImage()

2 - Criando classe ClienteHttpException.

      // public class ClienteHttpExeption extends RuntimeException{

           public ClienteHttpExeption(String msg) {
               super(msg);

           }

       }

Alteração do ClienteHttp.

        // catch(IOException | InterruptedException ex){
             throw new ClienteHttpExeption("\nErro na consulta da URL\n(╯°□°)╯︵ ┻━┻\n");

         }
         
3 - Utilizando .stram() e expressão lambda nos Extratores.

        // public List<Conteudo> extrairConteudos(String json){

 extrair só os dados que interessam (titulo, poster, classificação)
        //   var parser = new JsonParser();
           List<Map<String, String>> listaDeAtributos = parser.parse(json);

           return listaDeAtributos.stream()
               .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url")))
               .toList();

           }

4 - Utilizando uma enum API para guardar as urls das APIs.

           // public enum API {
               IMDB_TOP_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorConteudoIMDB()),
               NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=" + System.getenv("API_KEY") + "&start_date=2022-07-4&end_date=2022-07-10", new       ExtratorConteudoNasa()),
               IMDB_POPULAR_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json", new ExtratorConteudoIMDB());

               private String url;
               private ExtratorConteudo ext;

               API(String url, ExtratorConteudo ext){
                   this.url = url;
                   this.ext = ext;

               }

               public String getUrl() {
                   return url;
               }

               public ExtratorConteudo getExt() {
                   return ext;
               }

           }
