package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static Helpers.Helper.*;

public class Room
{

    private int id;
    private int floor;
    private int capacity;
    private String type;
    private String description;
    private double price;
    private ArrayList<String> reservedDates;
    static final String ANSI_BOLD_RED = "\u001B[31;1m";
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BOLD_GREEN = "\u001B[32;1m";
    static final String ANSI_BOLD_WHITE = "\u001B[37;1m";



    public Room(int id, int floor, int capacity, String type, String description, double price) {
        this.id = id;
        this.floor = floor;
        this.capacity = capacity;
        this.type = type;
        this.description = description;
        this.price = price;
        reservedDates = new ArrayList<>();
    }

    public Room() {
        reservedDates = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public boolean isReserved(LocalDate startDate, LocalDate finishDate) {
        boolean b = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (LocalDate date=startDate;date.isBefore(finishDate);date=date.plusDays(1)) {
            String d = date.format(formatter);
            if (reservedDates.contains(d)) {
                b = true;
                break;
            }
        }
        return b;
    }

    public void print() {
        System.out.println(ANSI_BOLD_WHITE + "Id: "+id);
        System.out.println("Floor: "+floor);
        System.out.println("Capacity: "+capacity);
        System.out.println("Type: "+type);
        System.out.println("Description: "+description);
        System.out.println("Price: "+price + ANSI_RESET);
    }


    public static void addNewRoom(ArrayList<Room> rooms, Scanner scanner) {
        int floor =  0;
        int  capacity = 0;
        double price = 0;
        String type = scanner.nextLine();
        String description = "";
        boolean floorValid = false, capacityValid = false, typeValid = false, descriptionValid = false, priceValid = false;

        while (true) {
            if (!floorValid) {
                System.out.println("Enter floor (int): ");
                try {
                    floor = Integer.parseInt(scanner.nextLine().trim());
                    floorValid = true;
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_BOLD_RED +"Error: Invalid floor number. Please enter a valid integer." + ANSI_RESET);
                }
            }

            if (floorValid && !capacityValid) {
                System.out.println("Enter capacity (int): ");
                try {
                    capacity = Integer.parseInt(scanner.nextLine().trim());
                    capacityValid = true;
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_BOLD_RED +"Error: Invalid capacity. Please enter a valid integer."+ ANSI_RESET);
                }
            }

            if (capacityValid && !typeValid) {
                System.out.println("Enter type: ");
                type = scanner.nextLine().trim();

                if (!type.isEmpty() && !type.matches(".*\\d.*")) {
                    typeValid = true;
                } else {
                    System.out.println(ANSI_BOLD_RED +"Error: Type cannot be empty and contain numbers. Please enter a valid string."+ ANSI_RESET);
                }
            }

            if (typeValid && !descriptionValid) {
                System.out.println("Enter description: ");
                description = scanner.nextLine().trim();

                if (!description.isEmpty() && !description.matches(".*\\d.*") ) {
                    descriptionValid = true;
                } else {
                    System.out.println(ANSI_BOLD_RED +"Error: Description cannot be empty or contain numbers. Please enter a valid string."+ ANSI_RESET);
                }
            }

            if (descriptionValid && !priceValid) {
                System.out.println("Enter price (double): ");
                try {
                    price = Double.parseDouble(scanner.nextLine().trim());
                    priceValid = true;
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_BOLD_RED +"Error: Invalid price. Please enter a valid number."+ ANSI_RESET);
                }
            }

            if (floorValid && capacityValid && typeValid && descriptionValid && priceValid) {
                break;
            }
        }

        int id = 1 + rooms.size();
        Room room = new Room(id, floor, capacity, type, description, price);
        rooms.add(room);
        System.out.println(ANSI_BOLD_GREEN + "Room added successfully!" + ANSI_RESET);
        System.out.println();

    }

    public static void showAllRooms(ArrayList<Room> rooms) {
        if(rooms.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is no room" + ANSI_RESET);
            return;
        }
        for (Room room : rooms) {
            System.out.println(ANSI_BOLD_WHITE + "--------------------------------");
            room.print();
            System.out.println("--------------------------------" + ANSI_RESET);
            System.out.println();
        }
    }

    public static void editRoom(ArrayList<Room> rooms, Scanner scanner) {

        if(rooms.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED +"Error: There is no room to edit!" + ANSI_RESET);
            String prompt = validateStringInput(scanner, "Would you like to add a new room? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
            if(prompt.equals("y"))
            {
                addNewRoom(rooms, scanner);

            }
            else
            {
                return;
            }
        }
        int id = validateIntOrKeepCurrent(scanner, "Enter room id (int): \n-1 to show all employees", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        if (id == -1) {
            showAllRooms(rooms);
            int j = validateIntOrKeepCurrent(scanner, "Enter room id (int): \n-1 to keep it", ANSI_BOLD_RED+ "Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
            id = j;
        }

        Room room;
        if (id == -1) {
            room = getRoomById(id+1, rooms);
        } else {
            room = getRoomById(id, rooms);
        }


        int floor = validateIntOrKeepCurrent(scanner, "Enter floor number (int): \n-1 to keep it", ANSI_BOLD_RED+"Error: Invalid Floor number. Please enter a valid integer."+ ANSI_RESET);
        if (floor!=-1)  room.setFloor(floor);

        int capacity = validateIntOrKeepCurrent(scanner, "Enter capacity (int): \n-1 to keep it", ANSI_BOLD_RED+"Error: Invalid Capacity number. Please enter a valid integer."+ ANSI_RESET);
        if (capacity!=-1) room.setCapacity(capacity);

        String type = validateStringOrKeepCurrent(scanner, "Enter type  : \n-1 to keep it", ANSI_BOLD_RED +"Error: Please enter a valid String."+ ANSI_RESET);
        if (!type.equals("-1")) room.setType(type);

        String description = validateStringOrKeepCurrent(scanner, "Enter description : \n-1 to keep it", ANSI_BOLD_RED +"Error: Invalid string type. Please enter a valid string."+ ANSI_RESET);
        if (!description.equals("-1")) room.setDescription(description);

        double price = validateDoubleInput(scanner, "Enter price (double): \n-1 to keep it", ANSI_BOLD_RED +"Error: Invalid price. Please enter a valid double."+ ANSI_RESET);
        if (price!=-1) room.setPrice(price);


        for (Room r : rooms) {
            if (r.getId()==id) {
                r = room;
                break;
            }
        }

        if (id == -1) {
            rooms.set(id+1,room);
        } else {
            rooms.set(id,room);
        }

        System.out.println(ANSI_BOLD_GREEN +"Room edited successfully!" + ANSI_RESET);


    }

    public static Room getRoomById(int id, ArrayList<Room> rooms) {

        if(rooms.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is room to search." + ANSI_RESET);
            return null;
        }
        Room room = new Room();
        for (Room r : rooms) {
            if (r.getId()==id) {
                room = r;
                break;
            }
        }
        return room;
    }


}
