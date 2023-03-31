## Imersão JAVA

Projeto imergido através da Alura - que é uma plataforma de tecnologia voltado para o mercado de trabalho. Foi composto por buscar listas de melhores filmes e series na API , Extrair os dados e Mostrar na saída


## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Aula 1
Desafios:
1. Consumir o endpoint de filmes mais populares da API do IMDB. Procure também, na documentação da API do IMDB, o endpoint que retorna as melhores séries e o que retorna as séries mais populares.

2. Usar a criatividade para deixar a saída dos dados mais bonitinha: usar emojis com código UTF-8, mostrar a nota do filme como estrelinhas, decorar o terminal com cores, negrito e itálico usando códigos ANSI, e mais!

3. Colocar a chave da API do IMDB em algum lugar fora do código como um arquivo de configuração (p. ex, um arquivo .properties) ou uma variável de ambiente. 

## API Application Programming Interface (Interface de Programação de Aplicação)
APIs são mecanismos que permitem que dois componentes de software se comuniquem usando um conjunto de definições e protocolos. Por exemplo, o sistema de software do instituto meteorológico contém dados meteorológicos diários. A aplicação para a previsão do tempo em seu telefone “fala” com esse sistema por meio de APIs e mostra atualizações meteorológicas diárias no telefone. 

## Aula 2

Importando imagens(arquivo local ou url), transformando em uma nova imagem .png com um texto em fundo transparente.
GeradorDeFigurinhas - Classe geradora de Stickers para WhatsApp.

Desafios:
1 - Criando diretório para saida das imagens caso não exista.

    // Gerando o diretório "saida/".
    var diretorio = new File("saida/");
    diretorio.mkdir();                                  

    // Criando a imagem no diretório "saida/" + nome da série.
     gerador.Criar(inputStream, "saida/" + nomeArquivo, texto, selo, classificacao);
    
2 - Centralizando o texto na nova imagem.

    // Texto + medidas
           FontMetrics fontMetrics = graphics.getFontMetrics();
           Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
           int alturaTexto = (int)retangulo.getHeight();
           int larguraTexto = (int)retangulo.getWidth();

    // Posição do texto em X e Y.
            int posXTexto = seloImage.getWidth() + 30; // posição X do texto começa320 pixels à direita da imagem selo
            int posYTexto = seloImageY + seloImage.getHeight() / 2 + alturaTexto + -150; // posição Y do texto centralizada em relação à imagem selo
            posXTexto = posXTexto - larguraTexto / 2;           

    // Desenhando o texto na nova imagem.
             graphics.drawString(texto, posXTexto, posYTexto);
    
3 - Alterando a fonte para uma pre-instalada no windows.

    // nova fonte
            var fonte = new Font("Impact", Font.BOLD, 80);
            graphics.setFont(fonte);
            graphics.setColor(corTexto);
    
4 - Desenhando contorno no texto.

        // Definindo estilo do contorno.
                FontRenderContext fontRenderContext = graphics.getFontRenderContext();
                var textLayout = new TextLayout(texto, fonte, fontRenderContext);

        // Definindo posição.
                Shape outLine = textLayout.getOutline(null);
                AffineTransform transform = graphics.getTransform();
                transform.translate(posXTexto, posYTexto);
                graphics.setTransform(transform);

        // Definindo largura do contorno.
                var outLineStoke = new BasicStroke(largura * 0.004f);
                graphics.setStroke(outLineStoke);

        // Definindo cor e desenhando o contorno.
                graphics.setColor(Color.BLACK);
                graphics.draw(outLine);
                graphics.setClip(outLine);
    
5 - Modificando texto de acordo com o Rating.

     // definir cor do texto com base na classificação
                Color corTexto = Color.YELLOW;
                        if (classificacao.equals("este eu reprovo")) {
                            corTexto = Color.RED;
                        } else if (classificacao.equals("este eu aprovo")) {
                            corTexto = Color.GREEN;
                        }
   
                         graphics.setColor(corTexto);

    // Passando o texto atualizado para o gerador de imagens.
              String classificacao = "classificacao";
              gerador.Criar(inputStream, "saida/" + nomeArquivo, texto, selo, classificacao);
    
    // Metodo Criar() recebendo o texto.
             public void Criar(InputStream inputStream, String nomeArquivo, String texto, InputStream selo, String classificacao) throws Exception{

    // Colocando sobreposição da imagem.
                 if (imDbRating >= 8.5 ){
                    texto = "filme recomendado";
                    selo = new FileInputStream("selos/aprovado.png");
                }else{
                    texto = "não recomendado";
                    selo = new FileInputStream("selos/reprovado.png");
                }

    // Desenhando a sobreposição.
               BufferedImage imgOriginal = ImageIO.read(inputStream);
               BufferedImage seloImage = ImageIO.read(selo);

                Graphics2D graphics = (Graphics2D)novaImagen.getGraphics();
                graphics.drawImage(imgOriginal, 0, 0, null);

                graphics.drawImage(imgOriginal, 0, 0, null);


