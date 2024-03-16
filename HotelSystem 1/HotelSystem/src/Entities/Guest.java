package Entities;

import java.util.ArrayList;
import java.util.Scanner;

import static Entities.Employee.addNewEmployee;
import static Entities.Room.addNewRoom;
import static Helpers.Helper.*;


public class Guest {

        private int id;
        private String name;
        private String email;
        private int discount;

        public Guest(int id, String name, String email, int discount) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.discount = discount;
        }

        public Guest() {}

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getDiscount() {
            return discount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        static final String ANSI_BOLD_RED = "\u001B[31;1m";
        static final String ANSI_RESET = "\u001B[0m";
        static final String ANSI_BOLD_GREEN = "\u001B[32;1m";
        static final String ANSI_BOLD_BLUE = "\u001B[34;1m";


    public void print() {
            System.out.println(ANSI_BOLD_BLUE + "Id: "+id);
            System.out.println("Name: "+name);
            System.out.println("Email: "+email);
            System.out.println("Discount: "+discount + ANSI_RESET);
        }

    public static  void addNewGuest(ArrayList<Guest> guests, ArrayList<Employee>employees, ArrayList<Room>rooms, Scanner scanner) {
            if(employees.isEmpty() && rooms.isEmpty())
            {
                System.out.println(ANSI_BOLD_RED + "You cannot add an guest because you do not have employee and room"+ANSI_RESET);
                String prompt = validateStringInput(scanner, "Would you like to add new employee and room? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
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

                if(employees.isEmpty() && !rooms.isEmpty())
                {
                    System.out.println(ANSI_BOLD_RED + "You cannot add an guest because you do not have employee and room"+ANSI_RESET);
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

                if(!employees.isEmpty() && rooms.isEmpty())
                {
                    System.out.println(ANSI_BOLD_RED + "You cannot add an guest because you do not have employee and room"+ANSI_RESET);
                    prompt = validateStringInput(scanner, "Would you like to add a new room? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
                    if(prompt.equals("y"))
                    {
                        addNewRoom(rooms, scanner);

                    }
                    else
                    {
                        return;
                    }
                }

                }


            String name = scanner.nextLine();
            String email = "";
            int discount  = 0;
            boolean nameValid = false, emailValid = false, discountValid = false;

            while(true)
            {
                if(!nameValid)
                {
                    System.out.println("Enter the name of the guest: ");
                    name = scanner.nextLine().trim();

                    if(!name.isEmpty() && !name.matches(".*\\d.*"))
                    {
                        nameValid = true;
                    }
                    else
                    {
                        System.out.println(ANSI_BOLD_RED +"Error: Name cannot be empty and contain numbers. Please enter a valid string." + ANSI_RESET);
                    }


                }

                if(nameValid && !emailValid)
                {
                    System.out.println("Enter your email");
                    email = scanner.nextLine().trim();

                    if(!email.isEmpty() && !email.isBlank())
                    {
                        emailValid = true;
                    }

                    else
                    {
                        System.out.println(ANSI_BOLD_RED +"Error: Email cannot be empty. Please enter a valid string"+ ANSI_RESET);
                    }

                }

                if(emailValid && !discountValid)
                {
                    while (true) {
                        discount = validateIntInput(scanner, "Enter discount for the guest: ", ANSI_BOLD_RED + "Error: Invalid input. Please enter valid integer"+ANSI_RESET);

                        if (discount >= 0 && discount <= 100) {
                            discountValid = true;
                            break;
                        } else {
                            System.out.println("Error: Discount should be between 0 and 100. Please try again.");
                        }
                    }

                }

                if(nameValid && emailValid && discountValid)
                {
                    break;
                }

            }

            Guest guest = new Guest(guests.size(), name, email, discount);
            guests.add(guest);
            System.out.println(ANSI_BOLD_GREEN +"Guest added successfully!"+ ANSI_RESET);

    }

    public static void showAllGuests(ArrayList<Guest> guests) {
        if(guests.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is no guest" + ANSI_RESET);
            System.out.println();
            return;
        }
        for (Guest guest : guests) {
            System.out.println(ANSI_BOLD_BLUE + "--------------------------------");
            guest.print();
            System.out.println("--------------------------------" + ANSI_RESET);
            System.out.println();
        }
    }

    public static void searchGuestByName(ArrayList<Guest> guests, Scanner scanner) {

        if(guests.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is no guest to search!" + ANSI_RESET);
            System.out.println();
            return;
        }
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println();
        for (Guest guest : guests) {
            if (guest.getName().equals(name) )
            {
                guest.print();
                System.out.println();
            }
            else
            {
                System.out.println("Guest does not exist! ");
            }
        }


    }

    public static void removeGuest(ArrayList<Guest> guests, Scanner scanner)
    {
        if(guests.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Error: There is no guest to remove!" + ANSI_RESET);
            return;
        }

        boolean found = false;
        String guestToRemove = validateStringInput(scanner, "Enter name of guest you want to remove: ", "Error: Invalid input. Please enter a valid string");
        for( int i = 0; i <= guests.size(); i ++ )
        {
            if(guests.get(i).getName().equals(guestToRemove))
            {
                guests.remove(i);
                System.out.println(ANSI_BOLD_GREEN + "Guest removed successfully! " + ANSI_RESET);
                System.out.println();
                found = true;
                break;
            }

        }

        if(!found)
        {
            System.out.println(ANSI_BOLD_RED + "Error: No employee found with the name " + guestToRemove + "." + ANSI_RESET);
            System.out.println();
        }


    }


    public static void editGuest(ArrayList<Guest> guests, ArrayList<Employee>employees, ArrayList<Room>rooms, Scanner scanner) {

        if(guests.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED +"Error: There is no guest to edit!" + ANSI_RESET);
            String prompt = validateStringInput(scanner, "Would you like to add a new guest? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
            if(prompt.equals("y"))
            {
                addNewGuest(guests,employees, rooms, scanner);

            }
            else
            {
                return;
            }
        }
        int id = validateIntOrKeepCurrent(scanner, "Enter guest id (int): \n-1 to show all guests", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        if (id==-1) {
            searchGuestByName(guests, scanner);
            id = validateIntOrKeepCurrent(scanner, "Enter guest id (int): \n-1 to keep it", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer."+ ANSI_RESET);
        }
        Guest guest = new Guest();
        if (id == -1) {
            guest = guests.get(id+1);
        } else {
            guests.get(id);
        }


        String name = validateStringOrKeepCurrent(scanner, "Enter guest name (String): \n-1to keep it ", ANSI_BOLD_RED +"Error: Invalid input. Please enter a valid string."+ ANSI_RESET);
        if (!name.equals("-1")) guest.setName(name);

        String email = validateStringOrKeepCurrent(scanner, "Enter Guest's Email (String): \n-1to keep it ", ANSI_BOLD_RED +"Error: Invalid input. Please enter a valid string."+ ANSI_RESET);
        if (!email.equals("-1"))  guest.setEmail(email);

        int discount = validateIntOrKeepCurrent(scanner, "Enter discount (int): \n-1 to keep it", ANSI_BOLD_RED +"Error: Invalid input. Please enter a valid integer."+ ANSI_RESET);
        if (discount!=-1) guest.setDiscount(discount);

        if (id == -1) {
            guests.set(id+1, guest);
        } else {
            guests.set(id, guest);
        }

        System.out.println(ANSI_BOLD_GREEN +"Guest edited successfully!" + ANSI_RESET);

    }


}
