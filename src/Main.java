
import java.util.Arrays;
import java.util.Scanner;

public class Main {
        private final static Scanner SCANNER = new Scanner(System.in);
        private final static char EMPTY = 'S';
        private final static char RESERVED = 'B';
        private final static int FRONT_HALF_PRICE = 10;
        private final static int BACK_HALF_PRICE = 8;
        private static int rows;
        private static int seatsInRow;
        private static char[][] seats;
        private static int totalSeats;
        private static int currentIncome;
        private static int reservedSeats;

        public static void main(String[] args) {
                start();
        }

        public static void start() {
                System.out.println("Enter the number of rows:");
                rows = SCANNER.nextInt();
                System.out.println("Enter the number of seats in each row:");
                seatsInRow = SCANNER.nextInt();
                seats = new char[rows][seatsInRow];
                totalSeats = rows * seatsInRow;
                fillSeats();
                chooseAction();
        }

        private static void chooseAction() {
                while (true) {
                        System.out.println();
                        System.out.println("1. Show the seats");
                        System.out.println("2. Buy a ticket");
                        System.out.println("3. Statistics");
                        System.out.println("0. Exit");
                        int input = SCANNER.nextInt();

                        switch (input) {
                                case 0:
                                        return;
                                case 1:
                                        printSeats();
                                        break;
                                case 2:
                                        buyTicket();
                                        break;
                                case 3:
                                        printStatistics();
                                        break;
                                default:
                                        System.out.println("Unknown command");
                        }
                }
        }

        private static void fillSeats() {
                for (char[] row : seats) {
                        Arrays.fill(row, EMPTY);
                }
        }

        private static void printSeats() {
                System.out.println("\nCinema:");
                for (int i = 0; i <= rows; i++) {
                        for (int j = 0; j <= seatsInRow; j++) {
                                if (i == 0) {
                                        System.out.print(j == 0 ? "  " : j + " ");
                                } else {

                                        System.out.print(j == 0 ? i + " " : seats[i - 1][j - 1] + " ");
                                }
                        }
                        System.out.println();
                }
        }

        private static void buyTicket() {
                while (true) {
                        System.out.println("\nEnter a row number:");
                        int row = SCANNER.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seat = SCANNER.nextInt();

                        if (row >= 1 && row <= rows && seat >= 1 && seat <= seatsInRow) {
                                if (seats[row - 1][seat - 1] != RESERVED) {
                                        int ticketPrice = getTicketPrice(row);
                                        System.out.printf("Ticket price: $%d%n", ticketPrice);
                                        seats[row - 1][seat - 1] = RESERVED;
                                        currentIncome += ticketPrice;
                                        reservedSeats++;
                                        break;
                                } else {
                                        System.out.println("That ticket has already been purchased!");
                                }
                        } else {
                                System.out.println("Wrong input!");
                        }
                }
        }

        private static int getTicketPrice(int row) {
                int frontHalf = rows / 2;
                if (totalSeats <= 60 || row <= frontHalf) {
                        return FRONT_HALF_PRICE;
                } else {
                        return BACK_HALF_PRICE;
                }
        }

        private static void printStatistics() {
                int frontHalfSeats = rows / 2 * seatsInRow;
                int backHalfSeats = totalSeats - frontHalfSeats;
                int totalIncome = totalSeats <= 60 ? totalSeats * FRONT_HALF_PRICE
                        : frontHalfSeats * FRONT_HALF_PRICE + backHalfSeats * BACK_HALF_PRICE;

                System.out.println();
                System.out.printf("Number of purchased tickets: %d%n", reservedSeats);
                System.out.printf("Percentage: %.2f%%%n", (double)reservedSeats / totalSeats * 100);
                System.out.printf("Current income: $%d%n", currentIncome);
                System.out.printf("Total income: $%d%n", totalIncome);
        }
}


