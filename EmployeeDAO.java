import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO() {
        try {
            // Initialize the connection (you need to replace the connection URL, username, and password)
            String url = "jdbc:mysql://localhost:3308/EmployeeDB";
            String username = "root";
            String password = "ROOT";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create operation
    public void addEmployee(Employee employee) {
        try {
            String query = "INSERT INTO employees (id, name, designation, salary) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setString(2, employee.getName());
                preparedStatement.setString(3, employee.getDesignation());
                preparedStatement.setDouble(4, employee.getSalary());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read operation
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "SELECT * FROM employees";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String designation = resultSet.getString("designation");
                    double salary = resultSet.getDouble("salary");
                    employees.add(new Employee(id, name, designation, salary));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Update operation
    public void updateEmployee(Employee employee) {
        try {
            String query = "UPDATE employees SET name=?, designation=?, salary=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, employee.getDesignation());
                preparedStatement.setDouble(3, employee.getSalary());
                preparedStatement.setInt(4, employee.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete operation
    public void deleteEmployee(int employeeId) {
        try {
            String query = "DELETE FROM employees WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, employeeId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read operation for fetching an employee by ID
    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        try {
            String query = "SELECT * FROM employees WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, employeeId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String designation = resultSet.getString("designation");
                        double salary = resultSet.getDouble("salary");
                        employee = new Employee(id, name, designation, salary);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }



    // Close the connection when done
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
