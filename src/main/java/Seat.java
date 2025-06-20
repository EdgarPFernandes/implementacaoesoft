public class Seat {
    private char row;
    private int number;

    public Seat(char row, int number) {
        this.row = row;
        this.number = number;
    }

    public char getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public String getSeatCode() {
        return String.format("%c%d", row, number);
    }

    @Override
    public String toString() {
        return getSeatCode();
    }
}
