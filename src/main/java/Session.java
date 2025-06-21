import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Session implements Serializable {
    private String movie;
    private String sala;
    private String hora;
    private String date;
    private List<Seat> reservedSeats;

    public Session(Movie movie, String sala, String hora, String date) {
        this.movie = movie.getTitle();
        this.sala = sala;
        this.hora = hora;
        this.date = date;
        this.reservedSeats = new ArrayList<>();
    }

    public String getMovie() {return movie;}
    public String getSala() {return sala;}
    public String getHora() {return hora;}
    public String getDate() {return date;}
    public List<Seat> getReservedSeats() {return reservedSeats;}

    public void setMovie(String movie) {this.movie = movie;}
    public void setSala(String sala) {this.sala = sala;}
    public void setHora(String hora) {this.hora = hora;}
    public void setDate(String date) {this.date = date;}

    public void reserveSeat(Seat seat) {
        reservedSeats.add(seat);
    }

    public boolean isSeatReserved(Seat seat) {
        return reservedSeats.stream()
                .anyMatch(s -> s.getRow() == seat.getRow() && s.getNumber() == seat.getNumber());
    }
}
