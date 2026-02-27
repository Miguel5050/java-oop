// ===== ABSTRACT CLASS EXAMPLE 1: Payment System =====
abstract class Payment {
    protected double amount;
    protected String transactionId;
    
    public Payment(double amount) {
        this.amount = amount;
        this.transactionId = generateTransactionId();
    }
    
    // Abstract methods
    public abstract void processPayment();
    public abstract boolean validatePayment();
    
    // Concrete method
    public void printReceipt() {
        System.out.println("\n=== PAYMENT RECEIPT ===");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Amount: $" + amount);
        System.out.println("Status: " + (validatePayment() ? "Valid" : "Invalid"));
    }
    
    // Private helper method
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }
}

class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;
    
    public CreditCardPayment(double amount, String cardNumber, String cardHolder, 
                            String expiryDate, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    @Override
    public void processPayment() {
        if (validatePayment()) {
            System.out.println("Processing credit card payment of $" + amount);
            System.out.println("Card Holder: " + cardHolder);
            System.out.println("Card: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
            System.out.println("Payment approved!");
        } else {
            System.out.println("Credit card payment failed validation");
        }
    }
    
    @Override
    public boolean validatePayment() {
        return cardNumber != null && cardNumber.length() == 16 &&
               cvv != null && cvv.length() == 3 &&
               expiryDate != null && expiryDate.matches("\\d{2}/\\d{2}");
    }
}

class PayPalPayment extends Payment {
    private String email;
    private String password;
    
    public PayPalPayment(double amount, String email, String password) {
        super(amount);
        this.email = email;
        this.password = password;
    }
    
    @Override
    public void processPayment() {
        if (validatePayment()) {
            System.out.println("Processing PayPal payment of $" + amount);
            System.out.println("PayPal Account: " + email);
            System.out.println("Payment approved!");
        } else {
            System.out.println("PayPal payment failed validation");
        }
    }
    
    @Override
    public boolean validatePayment() {
        return email != null && email.contains("@") &&
               password != null && password.length() >= 6;
    }
}

// ===== ABSTRACT CLASS EXAMPLE 2: Employee Management =====
abstract class Employee {
    protected String name;
    protected int id;
    protected double baseSalary;
    
    public Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }
    
    // Abstract methods
    public abstract double calculateSalary();
    public abstract String getRole();
    
    // Concrete methods
    public void displayInfo() {
        System.out.println("\n=== EMPLOYEE INFORMATION ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Role: " + getRole());
        System.out.println("Base Salary: $" + baseSalary);
        System.out.println("Total Salary: $" + calculateSalary());
    }
    
    public void work() {
        System.out.println(name + " is working");
    }
}

class FullTimeEmployee extends Employee {
    private double bonus;
    private double benefits;
    
    public FullTimeEmployee(String name, int id, double baseSalary, 
                           double bonus, double benefits) {
        super(name, id, baseSalary);
        this.bonus = bonus;
        this.benefits = benefits;
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + bonus + benefits;
    }
    
    @Override
    public String getRole() {
        return "Full-Time Employee";
    }
    
    @Override
    public void work() {
        super.work();
        System.out.println(name + " works 40 hours per week");
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;
    
    public PartTimeEmployee(String name, int id, double hourlyRate, int hoursWorked) {
        super(name, id, 0); // Base salary not applicable for part-time
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
    
    @Override
    public String getRole() {
        return "Part-Time Employee";
    }
    
    @Override
    public void work() {
        super.work();
        System.out.println(name + " works " + hoursWorked + " hours per week");
    }
}

class Intern extends Employee {
    private String school;
    private int duration; // in months
    
    public Intern(String name, int id, double stipend, String school, int duration) {
        super(name, id, stipend);
        this.school = school;
        this.duration = duration;
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary; // Stipend is fixed
    }
    
    @Override
    public String getRole() {
        return "Intern";
    }
    
    @Override
    public void work() {
        super.work();
        System.out.println(name + " is learning and assisting");
    }
}

// ===== ABSTRACT CLASS EXAMPLE 3: Database Connection =====
abstract class DatabaseConnection {
    protected String host;
    protected int port;
    protected String database;
    protected boolean connected;
    
    public DatabaseConnection(String host, int port, String database) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.connected = false;
    }
    
    // Abstract methods
    public abstract void connect();
    public abstract void disconnect();
    public abstract void executeQuery(String query);
    
    // Concrete method
    public boolean isConnected() {
        return connected;
    }
    
    public void displayConnectionInfo() {
        System.out.println("\nDatabase: " + database);
        System.out.println("Host: " + host + ":" + port);
        System.out.println("Status: " + (connected ? "Connected" : "Disconnected"));
    }
}

class MySQLConnection extends DatabaseConnection {
    public MySQLConnection(String host, int port, String database) {
        super(host, port, database);
    }
    
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL database...");
        connected = true;
        System.out.println("Connected to MySQL successfully!");
    }
    
    @Override
    public void disconnect() {
        System.out.println("Disconnecting from MySQL...");
        connected = false;
        System.out.println("Disconnected from MySQL");
    }
    
    @Override
    public void executeQuery(String query) {
        if (connected) {
            System.out.println("Executing MySQL query: " + query);
            System.out.println("MySQL query executed successfully");
        } else {
            System.out.println("Error: Not connected to MySQL database");
        }
    }
}

class PostgreSQLConnection extends DatabaseConnection {
    public PostgreSQLConnection(String host, int port, String database) {
        super(host, port, database);
    }
    
    @Override
    public void connect() {
        System.out.println("Connecting to PostgreSQL database...");
        connected = true;
        System.out.println("Connected to PostgreSQL successfully!");
    }
    
    @Override
    public void disconnect() {
        System.out.println("Disconnecting from PostgreSQL...");
        connected = false;
        System.out.println("Disconnected from PostgreSQL");
    }
    
    @Override
    public void executeQuery(String query) {
        if (connected) {
            System.out.println("Executing PostgreSQL query: " + query);
            System.out.println("PostgreSQL query executed successfully");
        } else {
            System.out.println("Error: Not connected to PostgreSQL database");
        }
    }
}

// Main class
public class Group7_AbstractClassesMethods {
    public static void main(String[] args) {
        System.out.println("=== ABSTRACT CLASSES AND METHODS - PAYMENT SYSTEM ===");
        
        // Payment system demonstration
        Payment[] payments = {
            new CreditCardPayment(100.50, "1234567890123456", "John Doe", "12/25", "123"),
            new PayPalPayment(75.25, "john@email.com", "password123")
        };
        
        for (Payment payment : payments) {
            payment.processPayment();
            payment.printReceipt();
            System.out.println();
        }
        
        System.out.println("=".repeat(50));
        System.out.println("=== ABSTRACT CLASSES - EMPLOYEE MANAGEMENT ===");
        
        // Employee management demonstration
        Employee[] employees = {
            new FullTimeEmployee("Alice Johnson", 1001, 50000, 5000, 2000),
            new PartTimeEmployee("Bob Smith", 1002, 25, 20),
            new Intern("Charlie Brown", 1003, 2000, "University of Tech", 3)
        };
        
        for (Employee employee : employees) {
            employee.work();
            employee.displayInfo();
            System.out.println();
        }
        
        System.out.println("=".repeat(50));
        System.out.println("=== ABSTRACT CLASSES - DATABASE CONNECTIONS ===");
        
        // Database connection demonstration
        DatabaseConnection[] connections = {
            new MySQLConnection("localhost", 3306, "mydb"),
            new PostgreSQLConnection("localhost", 5432, "mydb")
        };
        
        for (DatabaseConnection connection : connections) {
            connection.displayConnectionInfo();
            connection.connect();
            connection.executeQuery("SELECT * FROM users");
            connection.disconnect();
            System.out.println();
        }
        
        System.out.println("=".repeat(50));
        System.out.println("=== KEY POINTS ABOUT ABSTRACT CLASSES ===");
        System.out.println("1. Cannot be instantiated directly");
        System.out.println("2. Can have both abstract and concrete methods");
        System.out.println("3. Can have constructors");
        System.out.println("4. Can have instance variables");
        System.out.println("5. Subclasses must implement all abstract methods");
        System.out.println("6. Provide a template/contract for subclasses");
    }
}