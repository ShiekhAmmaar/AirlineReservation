class Reservation {
    private User user;
    private Seat seat;

    public Reservation(User user, Seat seat) {
        this.user = user;
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public Seat getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "Reservation Details:\n" +
                "User: " + user.getName() + " (" + user.getEmail() + ")\n" +
                "Seat Class: " + seat.getSeatClass() + "\n" +
                "Price: $" + seat.getPrice();
    }
}
