import java.util.ArrayList;
import java.util.Scanner;

public class FlightSystem {
    private static ArrayList<Seat> seats = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    static {
        // Initialize seats with different classes and prices
        for (int i = 0; i < 10; i++) {
            seats.add(new Seat("Economy", 100.0));
            seats.add(new Seat("Business", 200.0));
            seats.add(new Seat("First Class", 300.0));
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Show available seats");
            System.out.println("2. Book a seat");
            System.out.println("3. Cancel reservation");
            System.out.println("4. View reservations");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAvailableSeats();
                    break;
                case 2:
                    bookSeat();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    viewReservations();
                    break;
                case 5:
                    System.out.println("Exiting the program. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showAvailableSeats() {
        System.out.println("Available seats:");
        for (String seatClass : new String[]{"Economy", "Business", "First Class"}) {
            int availableSeats = getAvailableSeats(seatClass);
            System.out.println(seatClass + ": " + availableSeats + " seats available");
        }
    }

    private static void bookSeat() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your email: ");
        String email = scanner.next();

        System.out.println("Choose a seat class:");
        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            System.out.println((i + 1) + ". " + seat.getSeatClass() + " - $" + seat.getPrice());
        }

        int seatClassChoice = scanner.nextInt();

        if (seatClassChoice >= 1 && seatClassChoice <= seats.size()) {
            Seat selectedSeat = seats.get(seatClassChoice - 1);
            int availableSeats = getAvailableSeats(selectedSeat.getSeatClass());

            if (availableSeats > 0) {
                seats.remove(selectedSeat);
                Reservation reservation = new Reservation(new User(name, email), selectedSeat);
                reservations.add(reservation);
                System.out.println("Seat booked successfully:\n" + reservation);
            } else {
                System.out.println("Sorry, no available seats in the selected class. Please try another class.");
            }
        } else {
            System.out.println("Invalid seat class choice. Please try again.");
        }
    }

    private static void cancelReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the reservation number you want to cancel: ");
        int reservationNumber = scanner.nextInt();

        if (reservationNumber >= 1 && reservationNumber <= reservations.size()) {
            Reservation canceledReservation = reservations.remove(reservationNumber - 1);
            seats.add(new Seat(canceledReservation.getSeat().getSeatClass(), canceledReservation.getSeat().getPrice()));
            System.out.println("Reservation canceled successfully:\n" + canceledReservation);
        } else {
            System.out.println("Invalid reservation number. Please try again.");
        }
    }

    private static void viewReservations() {
        System.out.println("List of reservations:");
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            System.out.println((i + 1) + ". " + reservation);
        }
    }

    private static int getAvailableSeats(String seatClass) {
        int count = 0;
        for (Seat seat : seats) {
            if (seat.getSeatClass().equals(seatClass)) {
                count++;
            }
        }
        return count;
    }
}