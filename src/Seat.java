class Seat {
    private String seatClass;
    private double price;

    public Seat(String seatClass, double price) {
        this.seatClass = seatClass;
        this.price = price;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public double getPrice() {
        return price;
    }
}
