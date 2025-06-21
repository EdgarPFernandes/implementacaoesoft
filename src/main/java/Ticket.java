public class Ticket extends Product{
    private Session session;
    private Seat seat;

    public Ticket(String productName, Double price,Seat seat, Session session) {
        super(productName, price);
        this.seat = seat;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public Seat getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Seat: %s, Sala: %s, Time: %s)",
                getProductName(),
                getPrice() + "€",
                seat.getSeatCode(),
                session.getSala(),
                session.getHora());
    }
}
