import Entities.Employee;
import Entities.Guest;
import Entities.Reservation;
import Entities.Room;

import java.util.ArrayList;
import java.util.Scanner;

import static Helpers.Helper.validateIntInput;

public class Main {


    private static ArrayList<Room> rooms;
    private static ArrayList<Guest> guests;
    private static ArrayList<Employee> employees;
    private static ArrayList<Reservation> reservations;

    public static void main(String[] args) {

        rooms = new ArrayList<>();
        guests = new ArrayList<>();
        employees = new ArrayList<>();
        reservations = new ArrayList<>();

            final String ANSI_RESET = "\u001B[0m";

            // Bold Colors
             final String ANSI_BOLD_BLACK = "\u001B[30;1m";
             final String ANSI_BOLD_RED = "\u001B[31;1m";
            final String ANSI_BOLD_GREEN = "\u001B[32;1m";
            final String ANSI_BOLD_YELLOW = "\u001B[33;1m";
            final String ANSI_BOLD_BLUE = "\u001B[34;1m";
             final String ANSI_BOLD_PURPLE = "\u001B[35;1m";
             final String ANSI_BOLD_CYAN = "\u001B[36;1m";
            final String ANSI_BOLD_WHITE = "\u001B[37;1m";
        // Background Colors
        final String ANSI_BACKGROUND_BLACK = "\u001B[40m";
        final String ANSI_BACKGROUND_RED = "\u001B[41m";
        final String ANSI_BACKGROUND_GREEN = "\u001B[42m";
        final String ANSI_BACKGROUND_YELLOW = "\u001B[43m";
        final String ANSI_BACKGROUND_BLUE = "\u001B[44m";
        final String ANSI_BACKGROUND_PURPLE = "\u001B[45m";
        final String ANSI_BACKGROUND_CYAN = "\u001B[46m";
        final String ANSI_BACKGROUND_WHITE = "\u001B[47m";



        System.out.println(ANSI_BOLD_YELLOW +"__        __     _                                _                    \n" +
                "\\ \\      / /___ | |  ___  ___   _ __ ___    ___  | |_  ___             \n" +
                " \\ \\ /\\ / // _ \\| | / __|/ _ \\ | '_ ` _ \\  / _ \\ | __|/ _ \\            \n" +
                "  \\ V  V /|  __/| || (__| (_) || | | | | ||  __/ | |_| (_) |           \n" +
                "   \\_/\\_/  \\___||_| \\___|\\___/ |_| |_| |_| \\___|  \\__|\\___/            \n" +
                " _   _         _         _                                             \n" +
                "| | | |  ___  | |_  ___ | |                                            \n" +
                "| |_| | / _ \\ | __|/ _ \\| |                                            \n" +
                "|  _  || (_) || |_|  __/| |                                            \n" +
                "|_| |_| \\___/  \\__|\\___||_|                                            \n" +
                " __  __                                                            _   \n" +
                "|  \\/  |  __ _  _ __    __ _   __ _   ___  _ __ ___    ___  _ __  | |_ \n" +
                "| |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '_ ` _ \\  / _ \\| '_ \\ | __|\n" +
                "| |  | || (_| || | | || (_| || (_| ||  __/| | | | | ||  __/| | | || |_ \n" +
                "|_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_| |_| |_| \\___||_| |_| \\__|\n" +
                " ____               _         |___/                                    \n" +
                "/ ___|  _   _  ___ | |_  ___  _ __ ___                                 \n" +
                "\\___ \\ | | | |/ __|| __|/ _ \\| '_ ` _ \\                                \n" +
                " ___) || |_| |\\__ \\| |_|  __/| | | | | |                               \n" +
                "|____/  \\__, ||___/ \\__|\\___||_| |_| |_|                               \n" +
                "        |___/                                                          " +ANSI_RESET);
        int i = 0;
        while (i!=19) {

            System.out.println(ANSI_BOLD_BLUE + "1. Add new room" + ANSI_RESET );
            System.out.println(ANSI_BOLD_YELLOW +"2. Show all rooms"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_GREEN +"3. Edit room data"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_BLUE + "4. Add new guest"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_YELLOW +"5. Show all guests"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_CYAN +"6. Search guest by name"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_GREEN +"7. Edit guest data"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_CYAN +"8. Create new reservation"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_YELLOW +"9. Show all reservations"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_PURPLE + "10. Get reservation"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_GREEN +"11. Edit reservation"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_CYAN +"12. Pay reservation"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_BLUE +"13. Add new Employee"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_YELLOW +"14. Show all employees"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_GREEN +"15. Edit employee data"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_WHITE +"16. Remove employee"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_WHITE +"17. Remove guest"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_WHITE +"18. Remove reservation"+ ANSI_RESET);
            System.out.println(ANSI_BOLD_CYAN +"19. Quit" + ANSI_RESET );

            Scanner scanner  = new Scanner(System.in);
            i = validateIntInput(scanner, ANSI_BOLD_PURPLE + "Enter your choice: " + ANSI_RESET, ANSI_BOLD_RED +"Invalid input.Please enter a valid integer."+ANSI_RESET);
            while(i<= 0 || i >= 20)
            {
                System.out.println(ANSI_BOLD_RED + "Error: Choice should be between 1 and 19! " +ANSI_RESET);
                i = validateIntInput(scanner, ANSI_BOLD_PURPLE +"Enter your choice: " + ANSI_RESET, ANSI_BOLD_RED +"Invalid input.Please enter a valid integer."+ANSI_RESET);

            }

            switch (i) {
                case 1:
                    Room.addNewRoom(rooms, scanner);
                    break;
                case 2:
                    Room.showAllRooms(rooms);
                    break;
                case 3:
                    Room.editRoom(rooms, scanner);
                    break;
                case 4:
                    Guest.addNewGuest(guests, employees, rooms, scanner);
                    break;
                case 5:
                    Guest.showAllGuests(guests);
                    break;
                case 6:
                    Guest.searchGuestByName(guests, scanner);
                    break;
                case 7:
                    Guest.editGuest(guests, employees, rooms, scanner);
                    break;
                case 8:
                    Reservation.createNewReservation(guests, rooms,employees,reservations, scanner);
                    break;
                case 9:
                    Reservation.showAllReservations(reservations, scanner);
                    break;
                case 10:
                    Reservation.getReservation(reservations, scanner);
                    break;
                case 11:
                    Reservation.editReservation(guests, rooms, reservations, scanner);
                    break;
                case 12:
                    Reservation.payReservation(reservations, scanner);
                    break;
                case 13:
                    Employee.addNewEmployee(employees, scanner);
                    break;
                case 14:
                    Employee.showAllEmployees(employees);
                    break;
                case 15:
                    Employee.editEmployeeData(employees, scanner);
                    break;
                case 16:
                    Employee.removeEmployee(employees, scanner);
                    break;
                case 17:
                    Guest.removeGuest(guests, scanner);
                    break;
                case 18:
                    Reservation.removeReservation(reservations, scanner);
                    break;
                case 19:
                    scanner.close();
                    break;
            }
        }

    }
}
