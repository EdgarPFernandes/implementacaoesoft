public class Ticket extends Product{
    private Session session;
    private Seat seat;
    private String ticketType;

    public Ticket(String productName, Double price,Seat seat, Session session, String ticketType) {
        super(productName, price);
        this.seat = seat;
        this.session = session;
        this.ticketType = ticketType;
    }

    public String getTicketType() {
        return ticketType;
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
