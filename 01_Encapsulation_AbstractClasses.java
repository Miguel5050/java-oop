// Encapsulation Example
class BankAccount {
    private String accountNumber;
    private double balance;
    private String ownerName;
    
    // Constructor
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance > 0 ? initialBalance : 0;
    }
    
    // Public getters (accessors)
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    // Public setters (mutators) with validation
    public void setOwnerName(String ownerName) {
        if (ownerName != null && !ownerName.trim().isEmpty()) {
            this.ownerName = ownerName;
        }
    }
    
    // Public methods to interact with private data
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount + ", New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + ", New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount");
        }
    }
}

// Abstract Class Example
abstract class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract methods
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    
    // Concrete method
    public void displayInfo() {
        System.out.println("Color: " + color);
        System.out.println("Area: " + calculateArea());
        System.out.println("Perimeter: " + calculatePerimeter());
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}

// Main class to demonstrate
public class Group1_EncapsulationAbstract {
    public static void main(String[] args) {
        System.out.println("=== ENCAPSULATION DEMO ===");
        BankAccount account = new BankAccount("123456", "John Doe", 1000);
        System.out.println("Account: " + account.getAccountNumber());
        System.out.println("Owner: " + account.getOwnerName());
        System.out.println("Initial Balance: $" + account.getBalance());
        
        account.deposit(500);
        account.withdraw(200);
        
        System.out.println("\n=== ABSTRACT CLASSES & METHODS DEMO ===");
        Circle circle = new Circle("Red", 5);
        Rectangle rectangle = new Rectangle("Blue", 4, 6);
        
        System.out.println("Circle:");
        circle.displayInfo();
        
        System.out.println("\nRectangle:");
        rectangle.displayInfo();
    }
}