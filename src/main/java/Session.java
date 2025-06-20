import java.io.Serializable;

public class Session implements Serializable {
    private String movie;
    private String sala;
    private String hora;
    private String date;

    public Session(Movie movie, String sala, String hora, String date) {
        this.movie = movie.getTitle();
        this.sala = sala;
        this.hora = hora;
        this.date = date;
    }

    public String getMovie() {return movie;}
    public String getSala() {return sala;}
    public String getHora() {return hora;}
    public String getDate() {return date;}

    public void setMovie(String movie) {this.movie = movie;}
    public void setSala(String sala) {this.sala = sala;}
    public void setHora(String hora) {this.hora = hora;}
    public void setDate(String date) {this.date = date;}
}
