public enum API {
    IMDB_TOP_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorConteudoIMDB()),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=w4iQJ0FDppH2W3oBcKNCSsTBI0X00KqKiHftSb80&start_date=2022-12-25&end_date=2023-01-10", new ExtratorConteudoNasa()),
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