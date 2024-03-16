package Entities;

import java.util.ArrayList;
import java.util.Scanner;

import static Helpers.Helper.*;

public class Employee {

    private int id;
    private String name;
    private double salary;
    private String job;

    public Employee(){}

    static final String ANSI_BOLD_RED = "\u001B[31;1m";
    static final String ANSI_RESET = "\u001B[0m";
     static final String ANSI_BOLD_GREEN = "\u001B[32;1m";
    final String ANSI_BOLD_YELLOW = "\u001B[33;1m";




    public Employee(int id, String name, double salary, String job)
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.job = job;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void print() {
        System.out.println(ANSI_BOLD_YELLOW + "\n-------------------------");
        System.out.println("id: "+id);
        System.out.println("Name: "+name);
        System.out.println("Salary: "+salary);
        System.out.println("Job: "+job);
        System.out.println("-------------------------\n" + ANSI_RESET);
    }

    public static void addNewEmployee(ArrayList<Employee> employees, Scanner scanner) {
        String name = scanner.nextLine();
        String job = "";
        double salary = 0;
        boolean nameValid = false, salaryValid = false, jobValid = false;

        while (true) {
            if (!nameValid) {
                System.out.println("Enter name of employee: ");
                name = scanner.nextLine().trim();

                if (name.isEmpty() || name.matches(".*\\d.*")) {
                    System.out.println(ANSI_BOLD_RED +"Error: Name cannot be empty or contain numbers. Please enter a valid name." + ANSI_RESET);
                } else {
                    nameValid = true;
                }
            }

            if (nameValid && !salaryValid) {
                System.out.println("Enter salary (double): ");
                try {
                    salary = Double.parseDouble(scanner.nextLine().trim());
                    salaryValid = true;
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_BOLD_RED + "Error: Invalid salary. Please enter a number." + ANSI_RESET);
                }
            }

            if (salaryValid && !jobValid) {
                System.out.println("Enter job: ");
                job = scanner.nextLine().trim();

                if (job.isEmpty() || job.matches(".*\\d.*")) {
                    System.out.println(ANSI_BOLD_RED + "Error: Job cannot be empty or contain numbers. Please enter a valid string." + ANSI_RESET);
                } else {
                    jobValid = true;
                }
            }

            if (nameValid && salaryValid && jobValid) {
                break;
            }
        }

        int id = employees.size();
        Employee employee = new Employee(id, name, salary, job);
        employees.add(employee);
        System.out.println(ANSI_BOLD_GREEN + "Employee added successfully!" + ANSI_RESET);
    }


    public static void showAllEmployees(ArrayList<Employee> employees) {
        if(employees.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is no employee" + ANSI_RESET);
            return;
        }
        for (Employee employee : employees) {
            employee.print();
        }
    }

    public static void removeEmployee(ArrayList<Employee> employees, Scanner scanner)
    {
        if(employees.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED + "Warning: There is no employee" + ANSI_RESET);
            return;
        }

        String nameToRemove = validateStringInput(scanner, "Enter name of the employee you want to remove: ", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);

        boolean found = false;

        for(int i = 0; i <= employees.size(); i++)
        {
            if(employees.get(i).getName().equals(nameToRemove))
            {
                employees.remove(i);
                System.out.println(ANSI_BOLD_GREEN + "Employee removed successfully!" +ANSI_RESET);
                System.out.println();
                found = true;
                break;
            }


        }
        if(!found)
        {
            System.out.println(ANSI_BOLD_RED + "Error: No employee found with the name " + nameToRemove +"." + ANSI_RESET);
            System.out.println();
        }




    }

    public static void editEmployeeData(ArrayList<Employee> employees, Scanner scanner) {


        if(employees.isEmpty())
        {
            System.out.println(ANSI_BOLD_RED +"Error: There is no employee to edit!" + ANSI_RESET);
            String prompt = validateStringInput(scanner, "Would you like to add a new employee? (y/n)", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
            if(prompt.equals("y"))
            {
                addNewEmployee(employees, scanner);

            }
            else
            {
                return;
            }
        }

        int id = validateIntOrKeepCurrent(scanner, "Enter id (int): \n-1 to show all employees", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer." +ANSI_RESET);

        if (id==-1)
        {
            showAllEmployees(employees);
            id = validateIntOrKeepCurrent(scanner, "Enter id (int): \n-1 to keep it", ANSI_BOLD_RED +"Error: Invalid ID. Please enter a valid integer." + ANSI_RESET);

        }
        if((id < 0 && id != -1) || id >= employees.size())
        {
            System.out.println(ANSI_BOLD_RED + "Error: Employee ID does not exist!" + ANSI_RESET);
        }

        Employee employee = new Employee();
        if (id == -1) {
            employee = employees.get(id+1);
        } else {
            employees.get(id);
        }

        String name = validateStringOrKeepCurrent(scanner, "Enter name (String): \n-1to keep it ", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
        if (!name.equals("-1")) {
            employee.setName(name);
        }

        double salary = validateDoubleOrKeepCurrent(scanner, "Enter salary (double): \n-1 to keep it", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid number." + ANSI_RESET );
        if (salary != -1) {
            employee.setSalary(salary);
        }

        String job = validateStringOrKeepCurrent(scanner, "Enter job (String): \n-1 to keep it", ANSI_BOLD_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
        if (!job.equals("-1")) {
            employee.setJob(job);
        }
        if (id == -1) {
            employees.set(id+1, employee);
        } else {
            employees.set(id, employee);
        }
        System.out.println(ANSI_BOLD_GREEN + "Employee edited successfully!" + ANSI_RESET);

    }





}
