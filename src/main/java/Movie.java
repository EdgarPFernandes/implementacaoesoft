import java.io.Serializable;

public class Movie implements Serializable {
    private String movieName;
    private String movieGenre;
    private String origin;
    private String director;
    private String studio;
    private String languages;
    private String subtitles;
    private int id;
    private int age;
    private int duration;
    private int movieYear;
    private Double precoLicenciamento;

    public Movie(int id, String movieName, String movieGenre, String origin, String director,
                 String studio, String languages, String subtitles, int age, int duration, int movieYear, Double precoLicenciamento) {
        this.id = id;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.origin = origin;
        this.director = director;
        this.studio = studio;
        this.languages = languages;
        this.subtitles = subtitles;
        this.age = age;
        this.duration = duration;
        this.movieYear = movieYear;
        this.precoLicenciamento = precoLicenciamento;
    }

    //GETs
    public int getId() {return id;}

    public String getTitle() {return movieName;}

    public String getGenre() {return movieGenre;}

    public String getCountry() {return origin;}

    public String getDirector() {return director;}

    public String getStudio() {return studio;}

    public String getLanguages() {return languages;}

    public String getSubtitles() {return subtitles;}

    public int getAge() {return age;}

    public int getDuration() {return duration;}

    public int getYear() {return movieYear;}

    public Double getPrecoLicenciamento() {return precoLicenciamento;}

    //SETs
    public void setId(int id) {this.id = id;}

    public void setMovieName(String movieName) {this.movieName = movieName;}

    public void setMovieGenre(String movieGenre) {this.movieGenre = movieGenre;}

    public void setOrigin(String origin) {this.origin = origin;}

    public void setDirector(String director) {this.director = director;}

    public void setStudio(String studio) {this.studio = studio;}

    public void setLanguages(String languages) {this.languages = languages;}

    public void setSubtitles(String subtitles) {this.subtitles = subtitles;}

    public void setAge(int age) {this.age = age;}

    public void setDuration(int duration) {this.duration = duration;}

    public void setMovieYear(int movieYear) {this.movieYear = movieYear;}

    public void setPrecoLicenciamento(double precoLicenciamento) {this.precoLicenciamento = precoLicenciamento;}

    @Override
    public String toString() {
        return movieName;
    }

}
