import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {

    public static void main(String[] args) {
        // Create an instance of EmployeeDAO
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Menu for CRUD operations
        int choice;
        do {
            System.out.println("\nEmployee Management System Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Validate user input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter employee salary: ");

                    // Validate salary input
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a valid salary.");
                        scanner.next(); // consume the invalid input
                    }

                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline character

                    Employee newEmployee = new Employee(0, name, designation, salary);
                    employeeDAO.addEmployee(newEmployee);
                    System.out.println("Employee added successfully!");
                    break;

                case 2:
                    // View All Employees
                    List<Employee> allEmployees = employeeDAO.getAllEmployees();
                    System.out.println("\nAll Employees:");
                    for (Employee employee : allEmployees) {
                        System.out.println(employee);
                    }
                    break;

                case 3:
                    // Update Employee
                    System.out.print("Enter employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    Employee existingEmployee = employeeDAO.getEmployeeById(updateId);
                    if (existingEmployee != null) {
                        System.out.print("Enter updated employee name: ");
                        String updatedName = scanner.nextLine();
                        System.out.print("Enter updated employee designation: ");
                        String updatedDesignation = scanner.nextLine();
                        System.out.print("Enter updated employee salary: ");

                        // Validate updated salary input
                        while (!scanner.hasNextDouble()) {
                            System.out.println("Invalid input. Please enter a valid salary.");
                            scanner.next(); // consume the invalid input
                        }

                        double updatedSalary = scanner.nextDouble();
                        scanner.nextLine(); // consume the newline character

                        Employee updatedEmployee = new Employee(updateId, updatedName, updatedDesignation, updatedSalary);
                        employeeDAO.updateEmployee(updatedEmployee);
                        System.out.println("Employee updated successfully!");
                    } else {
                        System.out.println("Employee not found with ID: " + updateId);
                    }
                    break;

                case 4:
                    // Delete Employee
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    employeeDAO.deleteEmployee(deleteId);
                    System.out.println("Employee deleted successfully!");
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting Employee Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }

        } while (choice != 5);

        // Close resources
        scanner.close();
        employeeDAO.closeConnection();
    }
}
