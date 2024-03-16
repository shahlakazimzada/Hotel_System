package Entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static Entities.Employee.addNewEmployee;
import static Entities.Guest.addNewGuest;
import static Entities.Room.addNewRoom;
import static Helpers.Helper.*;

public class Reservation {

    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private double price;
    private String status;
    private Guest guest;
    private Room room;
    static final String ANSI_BOLD_RED = "\u001B[31;1m";
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BOLD_GREEN = "\u001B[32;1m";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final String ANSI_BOLD_BLUE = "\u001B[34;1m";
    static final String ANSI_BOLD_WHITE = "\u001B[37;1m";



    public Reservation(LocalDate arrivalDate, LocalDate departureDate, double price, String status, Guest guest, Room room) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.price = price;
        this.status = status;
        this.guest = guest;
        this.room = room;
    }

    public Reservation() {}

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalDatetoString() {
        return arrivalDate.format(formatter);
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public String getDepartureDatetoString() {
        return departureDate.format(formatter);
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void print() {
        System.out.println(ANSI_BOLD_GREEN + "\n***********************************");
        System.out.println("Arrival Date: "+ arrivalDate.format(formatter));
        System.out.println("Departure Date: "+ departureDate.format(formatter));
        int days = Period.between(arrivalDate, departureDate).getDays();
        System.out.println(days + " Days" + ANSI_RESET);
        System.out.println(ANSI_BOLD_BLUE + "******* Guest information *******" + ANSI_RESET);
        guest.print();
        System.out.println(ANSI_BOLD_WHITE + "******* Room information *******" + ANSI_RESET);
        room.print();
        System.out.println("*********** Total ***********");
        double price = days*room.getPrice();
        System.out.println("Price: "+price);
        System.out.println("Total after discount: "+this.price);
        System.out.println("***********************************\n");
    }


    public static void createNewReservation(ArrayList<Guest> guests, ArrayList<Room> rooms,ArrayList<Employee> employees,ArrayList<Reservation> reservations, Scanner scanner) {
        if(employees.isEmpty() && rooms.isEmpty() && guests.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "You cannot reserve a room because you do not have employee, room and guest"+ANSI_RESET);
            String prompt = validateStringInput(scanner, "Would you like to add new employee, room and guest? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
            if(prompt.equals("y"))
            {
                System.out.println(ANSI_BOLD_GREEN + "Adding employee..." + ANSI_RESET);
                addNewEmployee(employees, scanner);
                System.out.println(ANSI_BOLD_GREEN + "Adding room..." + ANSI_RESET);
                addNewRoom(rooms, scanner);
                System.out.println(ANSI_BOLD_GREEN + "Adding guest..." + ANSI_RESET);
                addNewGuest(guests, employees, rooms, scanner);

            }
            else
            {
                return;
            }

            if(employees.isEmpty() && !rooms.isEmpty() && !guests.isEmpty())
            {
                System.out.println(ANSI_BOLD_RED + "You cannot reserve a room because you do not have employee"+ANSI_RESET);
                prompt = validateStringInput(scanner, "Would you like to add a new employee? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
                if(prompt.equals("y"))
                {
                    addNewEmployee(employees, scanner);

                }
                else
                {
                    return;
                }

            }

            if(!employees.isEmpty() && rooms.isEmpty() && !guests.isEmpty())
            {
                System.out.println(ANSI_BOLD_RED + "You cannot reserve a room because you do not have a room"+ANSI_RESET);
                prompt = validateStringInput(scanner, "Would you like to add a new room? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
                if(prompt.equals("y"))
                {
                    System.out.println(ANSI_BOLD_GREEN + "Adding room..." + ANSI_RESET);
                    addNewRoom(rooms, scanner);

                }
                else
                {
                    return;
                }
            }

            if(!employees.isEmpty() && rooms.isEmpty() && guests.isEmpty())
            {
                System.out.println(ANSI_BOLD_RED + "You cannot reserve a room because you do not have guest and room"+ANSI_RESET);
                prompt = validateStringInput(scanner, "Would you like to add new room and guest? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
                if(prompt.equals("y"))
                {
                    System.out.println(ANSI_BOLD_GREEN + "Adding room..." + ANSI_RESET);
                    addNewRoom(rooms, scanner);
                    System.out.println(ANSI_BOLD_GREEN + "Adding guest..." + ANSI_RESET);
                    addNewGuest(guests, employees, rooms, scanner);

                }
                else
                {
                    return;
                }
            }
            if(employees.isEmpty() && rooms.isEmpty() && !guests.isEmpty())
            {
                System.out.println(ANSI_BOLD_RED + "You cannot reserve a room because you do not have employee and room"+ANSI_RESET);
                prompt = validateStringInput(scanner, "Would you like to add new room and employee? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
                if(prompt.equals("y"))
                {
                    System.out.println(ANSI_BOLD_GREEN + "Adding employee..." + ANSI_RESET);
                    addNewEmployee(employees, scanner);
                    System.out.println(ANSI_BOLD_GREEN + "Adding room..." + ANSI_RESET);
                    addNewRoom(rooms, scanner);
                }
                else
                {
                    return;
                }
            }

            if(employees.isEmpty() && !rooms.isEmpty() && guests.isEmpty())
            {
                System.out.println(ANSI_BOLD_RED + "You cannot reserve a room because you do not have employee and guest"+ANSI_RESET);
                prompt = validateStringInput(scanner, "Would you like to add new guest and employee? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
                if(prompt.equals("y"))
                {
                    System.out.println(ANSI_BOLD_GREEN + "Adding employee..." + ANSI_RESET);
                    addNewEmployee(employees, scanner);
                    System.out.println(ANSI_BOLD_GREEN + "Adding guest..." + ANSI_RESET);
                    addNewGuest(guests, employees, rooms, scanner);

                }
                else
                {
                    return;
                }
            }



        }
        String arrDate;
        String depDate;

        LocalDate arrivalDate = null;
        LocalDate departureDate = null;

        do {
            System.out.println("Enter arrival date (yyyy-MM-dd): ");
            arrDate = scanner.next();

            System.out.println("Enter departure date (yyyy-MM-dd): ");
            depDate = scanner.next();

            if (!isValidDate(arrDate)) {
                System.out.println(ANSI_BOLD_RED + "Invalid date format for arrival. Please try again."+ANSI_RESET);
            } else {
                arrivalDate = LocalDate.parse(arrDate, formatter);
            }

            if (!isValidDate(depDate)) {
                System.out.println(ANSI_BOLD_RED + "Invalid date format for departure. Please try again." + ANSI_RESET);
            } else {
                departureDate = LocalDate.parse(depDate, formatter);
            }

            if (arrivalDate != null && departureDate != null && arrivalDate.isAfter(departureDate)) {
                System.out.println("Error: Arrival date cannot be after the departure date!");
                arrivalDate = null;
                departureDate = null;
            }
        } while (arrivalDate == null || departureDate == null || arrivalDate.isAfter(departureDate));




        int guestId = validateIntInput(scanner, "Enter guest id (int): \n-1 to search guest by name", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);

        if (guestId==-1) {
            Guest.searchGuestByName(guests, scanner);
            guestId = validateIntInput(scanner, "Enter guest id (int): ", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        }

        int roomId = validateIntInput(scanner, "Enter room id (int): \n-1 to show all rooms", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        if (roomId==-1) {
            Room.showAllRooms(rooms);
            roomId = validateIntInput(scanner, "Enter room id (int): ", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        }

        arrivalDate = LocalDate.parse(arrDate, formatter);
        departureDate = LocalDate.parse(depDate, formatter);

        Guest guest = guests.get(guestId);
        Room room = Room.getRoomById(roomId, rooms);
        if (room.isReserved(arrivalDate, departureDate)) {
            System.out.println("This room is reserved!");
            return;
        } else {
            int days = Period.between(arrivalDate, departureDate).getDays();
            double sum = days*room.getPrice();
            double total = sum - sum*guest.getDiscount()/100;
            System.out.println("Total before discount = "+sum);
            System.out.println("Total after discount = "+total);
            System.out.println("Will you pay now?\n1. Yes\n2. No");
            int j;
            while (true) {
                System.out.println("Enter a number (1 for Paid, other for Reserved): ");
                if (scanner.hasNextInt()) {
                    j = scanner.nextInt();
                    break;
                } else {
                    System.out.println(ANSI_BOLD_RED +"Invalid input. Please enter an integer."+ ANSI_RESET);
                    scanner.next();
                }
            }

            String status = (j == 1) ? "Paid" : "Reserved";

            Reservation r = new Reservation(arrivalDate, departureDate, total, status, guest, room);
            reservations.add(r);
            r.print();
            System.out.println();
        }

    }

    public static void showAllReservations(ArrayList<Reservation> reservations, Scanner scanner) {
        if(reservations.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is no reservation." + ANSI_RESET);
            return;
        }

        for (Reservation r : reservations) {
            System.out.println(ANSI_BOLD_GREEN + "\n---------------------------------------");
            System.out.println("id: "+reservations.indexOf(r));
            System.out.println("Arrival Date: " + r.getArrivalDatetoString());
            System.out.println("Departure Date: " + r.getDepartureDatetoString());
            System.out.println("Guest Name: " + r.getGuest().getName());
            System.out.println("Room id: " + r.getRoom().getId());
            System.out.println("Price: "+ r.getPrice());
            System.out.println("Status: "+r.getStatus());
            System.out.println("---------------------------------------" + ANSI_RESET);
        }
    }


    public static void getReservation(ArrayList<Reservation> reservations, Scanner scanner) {

        if(reservations.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is no reservation to search." + ANSI_RESET);
            return;
        }
        int option = validateIntInput(scanner, "1 for by GUEST NAME, 2 for by ID", ANSI_BOLD_RED + "Error: Invalid input. Enter valid integer" + ANSI_RESET);
        boolean found = false;
        boolean guestExists = false;
        String guestName;
        int guestID;

        if (option == 1) {
            guestName = validateStringInput(scanner, "Enter guest name: ", ANSI_BOLD_RED + "Error: Invalid input. Please enter valid string" + ANSI_RESET);
            for (Reservation r : reservations) {
                String name = r.getGuest().getName();
                if (guestName.equals(name)) {
                    guestExists = true;
                    r.print();
                    found = true;
                }
            }

            if (!guestExists) {
                System.out.println(ANSI_BOLD_RED + "Error: No guest name found with " + guestName + "." + ANSI_RESET);
            } else if (!found) {
                System.out.println("Guest " + guestName + " does not have any reservations.");
            }
        } else if (option == 2) {
            guestID = validateIntInput(scanner, "Enter guest ID: ", ANSI_BOLD_RED + "Error: Invalid input. Please enter valid integer." + ANSI_RESET);
            for (Reservation r : reservations) {
                int id = r.getGuest().getId();
                if (guestID == id) {
                    guestExists = true;
                    r.print();
                    found = true;
                }
            }

            if (!guestExists) {
                System.out.println(ANSI_BOLD_RED + "Error: No ID found with " + guestID + "." + ANSI_RESET);
            } else if (!found) {
                System.out.println("Guest with ID " + guestID + " does not have any reservations.");
            }
        } else {
            System.out.println(ANSI_BOLD_RED + "You do not have other options!" + ANSI_RESET);
        }

    }

    public static void removeReservation(ArrayList<Reservation> reservations, Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println(ANSI_BOLD_RED + "There is no reservation to remove." + ANSI_RESET);
            return;
        }

        int id = validateIntInput(scanner, "Enter reservation id (int) to remove: \n-1 to show all reservations", ANSI_BOLD_RED + "Error: Invalid ID. Please enter a valid integer." + ANSI_RESET);
        if (id == -1) {
            showAllReservations(reservations, scanner);
            id = validateIntInput(scanner, "Enter reservation id (int) to remove: ", ANSI_BOLD_RED + "Error: Invalid ID. Please enter a valid integer." + ANSI_RESET);
        }

        if (id >= 0 && id <= reservations.size()) {
            Reservation reservationToRemove = reservations.get(id);
            reservations.remove(reservationToRemove);
            System.out.println(ANSI_BOLD_GREEN + "Reservation removed successfully!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_BOLD_RED + "Invalid reservation ID. No reservation removed." + ANSI_RESET);
        }

    }


    public static void editReservation(ArrayList<Guest> guests, ArrayList<Room> rooms, ArrayList<Reservation> reservations, Scanner scanner) {

        int id = validateIntInput(scanner, "Enter reservation id (int): \n-1 to show all reservations' ids", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        if (id == -1) {
            showAllReservations(reservations, scanner);
            id = validateIntInput(scanner, "Enter reservation id (int): ", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        }

        Reservation reservation = new Reservation();
        if (id == -1) {
            reservations.get(id+1);
        } else {
            reservations.get(id);
        }


        String arrDate;
        do {
            System.out.println("Enter arrival date (yyyy-MM-dd): \n-1 to keep it");
            arrDate = scanner.next();
            if (!isValidDate(arrDate)) {
                System.out.println(ANSI_BOLD_RED +"Invalid date format. Please try again."+ ANSI_RESET);
            }
        } while (!isValidDate(arrDate));

        if (arrDate.equals("-1")) {
            arrDate = reservation.getArrivalDatetoString();
        }


        String depDate;
        do {
            System.out.println("Enter departure date (yyyy-MM-dd): \n-1 to keep it");
            depDate = scanner.next();
            if (!isValidDate(depDate)) {
                System.out.println(ANSI_BOLD_RED +"Invalid date format. Please try again."+ ANSI_RESET);
            }
        } while (!isValidDate(arrDate));

        if (depDate.equals("-1")) {
            depDate = reservation.getDepartureDatetoString();
        }

        int roomId = validateIntInput(scanner, "Enter room id (int): \n-1 to keep it\n-2 to show all rooms", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        if (roomId == -1) {
            roomId = reservation.getRoom().getId();
        } else if (roomId == -2) {
            Room.showAllRooms(rooms);
            roomId = validateIntInput(scanner, "Enter room id (int): ", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        }

        LocalDate arrivalDate = LocalDate.parse(arrDate, formatter);
        LocalDate departureDate = LocalDate.parse(depDate, formatter);

        Guest guest = reservation.getGuest();
        Room room = Room.getRoomById(roomId, rooms);
        if (room.isReserved(arrivalDate, departureDate)) {
            System.out.println("This room is reserved!");
            return;
        } else {
            int days = Period.between(arrivalDate, departureDate).getDays();
            double sum = days * room.getPrice();
            double total = sum - sum * guest.getDiscount() / 100;
            System.out.println("Total before discount = " + sum);
            System.out.println("Total after discount = " + total);
            System.out.println("Will you pay now?\n1. Yes\n2. No");
            int j = 0;
            boolean isValidInput = false;

            while (!isValidInput) {
                System.out.print("Enter 1 for Paid, other for Reserved: ");
                try {
                    j = scanner.nextInt();
                    isValidInput = true;
                } catch (Exception e) {
                    System.out.println(ANSI_BOLD_RED +"Invalid input, please enter an integer."+ ANSI_RESET);
                    scanner.nextLine();
                }
            }

            String status;
            if (j == 1) {
                status = "Paid";
            } else {
                status = "Reserved";
            }

            reservation.setArrivalDate(arrivalDate);
            reservation.setDepartureDate(departureDate);
            reservation.setRoom(room);
            reservation.setStatus(status);
            reservation.setPrice(total);
            if (id == -1) {
                reservations.set(id+1, reservation);
            } else {
                reservations.set(id, reservation);
            }

            reservation.print();
            System.out.println();
        }

    }

    public static void payReservation(ArrayList<Reservation> reservations, Scanner scanner) {

        if(reservations.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is no reservation to pay." + ANSI_RESET);
            return;
        }
        int id = validateIntInput(scanner, "Enter reservation id (int): \n-1 to show all reservations' ids", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        if (id==-1) {
            showAllReservations(reservations, scanner);
            id = validateIntInput(scanner, "Enter reservation id (int): ", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        }

        Reservation reservation = reservations.get(id);
        if (reservation.getStatus().equals("Reserved")) {
            reservation.setStatus("Paid");
            System.out.println(ANSI_BOLD_GREEN +"Reservation paid successfully!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_BOLD_GREEN + "This reservation is already paid!" + ANSI_RESET);
        }
    }


}
