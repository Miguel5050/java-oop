// ===== INTERFACES =====
interface Flyable {
    void fly();
    default void takeOff() {
        System.out.println("Taking off...");
    }
}

interface Swimmable {
    void swim();
    default void dive() {
        System.out.println("Diving...");
    }
}

interface Runnable {
    void run();
}

// ===== BASE CLASS =====
class Animal {
    protected String name;
    protected int age;
    
    // Constructor
    public Animal() {
        this.name = "Unknown";
        this.age = 0;
        System.out.println("Animal default constructor called");
    }
    
    // Parameterized constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Animal parameterized constructor called for: " + name);
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    public void displayInfo() {
        System.out.println("Animal: " + name + ", Age: " + age);
    }
}

// ===== CLASS EXTENDING ANOTHER CLASS AND IMPLEMENTING MULTIPLE INTERFACES =====
class Duck extends Animal implements Flyable, Swimmable, Runnable {
    private String featherColor;
    
    // Constructor calling base class constructor
    public Duck(String name, int age, String featherColor) {
        super(name, age); // Invoking base class constructor
        this.featherColor = featherColor;
        System.out.println("Duck constructor called");
    }
    
    // Override methods from interfaces
    @Override
    public void fly() {
        System.out.println(name + " the duck is flying with " + featherColor + " feathers");
    }
    
    @Override
    public void swim() {
        System.out.println(name + " the duck is swimming");
    }
    
    @Override
    public void run() {
        System.out.println(name + " the duck is running");
    }
    
    // Override parent method
    @Override
    public void eat() {
        System.out.println(name + " the duck is eating aquatic plants");
    }
    
    // Additional method
    public void quack() {
        System.out.println(name + " says: Quack! Quack!");
    }
}

// Another example: Class extending and implementing interfaces
interface Honkable {
    void honk();
}

interface Drivable {
    void drive();
}

class Vehicle {
    protected String brand;
    protected String model;
    
    public Vehicle() {
        this.brand = "Unknown";
        this.model = "Unknown";
        System.out.println("Vehicle default constructor called");
    }
    
    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Vehicle parameterized constructor called");
    }
    
    public void start() {
        System.out.println(brand + " " + model + " is starting");
    }
}

class Car extends Vehicle implements Drivable, Honkable {
    private int doors;
    
    // Constructor chaining with super
    public Car() {
        super(); // Call to default constructor (implicitly called even if not written)
        this.doors = 4;
        System.out.println("Car default constructor called");
    }
    
    public Car(String brand, String model, int doors) {
        super(brand, model); // Call to parameterized constructor
        this.doors = doors;
        System.out.println("Car parameterized constructor called");
    }
    
    @Override
    public void drive() {
        System.out.println(brand + " " + model + " is being driven");
    }
    
    @Override
    public void honk() {
        System.out.println(brand + " " + model + " honks: Beep Beep!");
    }
    
    public void displayInfo() {
        System.out.println("Car: " + brand + " " + model + " with " + doors + " doors");
    }
}

// Example showing different constructor types
class Person {
    private String name;
    private int age;
    private String address;
    
    // Default constructor
    public Person() {
        this("Unknown", 0, "Unknown");
        System.out.println("Default constructor called");
    }
    
    // Constructor with one parameter
    public Person(String name) {
        this(name, 0, "Unknown");
        System.out.println("Single-parameter constructor called");
    }
    
    // Constructor with two parameters
    public Person(String name, int age) {
        this(name, age, "Unknown");
        System.out.println("Two-parameter constructor called");
    }
    
    // Constructor with all parameters (main constructor)
    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
        System.out.println("Main constructor called");
    }
    
    // Copy constructor
    public Person(Person other) {
        this(other.name, other.age, other.address);
        System.out.println("Copy constructor called");
    }
    
    public void display() {
        System.out.println("Person: " + name + ", Age: " + age + ", Address: " + address);
    }
}

// Main class
public class Group6_ClassExtensionInterfaceConstructors {
    public static void main(String[] args) {
        System.out.println("=== CLASS EXTENDING AND IMPLEMENTING INTERFACES ===");
        
        // Create Duck object - demonstrates extending class and implementing interfaces
        Duck duck = new Duck("Donald", 5, "white");
        System.out.println();
        
        duck.displayInfo();
        duck.eat();
        duck.sleep();
        duck.fly();
        duck.swim();
        duck.run();
        duck.quack();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== CONSTRUCTOR INVOCATION EXAMPLES ===");
        
        // Car examples demonstrating constructor chaining
        System.out.println("\n--- Creating default car ---");
        Car car1 = new Car();
        car1.displayInfo();
        car1.start();
        car1.drive();
        car1.honk();
        
        System.out.println("\n--- Creating parameterized car ---");
        Car car2 = new Car("Toyota", "Camry", 4);
        car2.displayInfo();
        car2.start();
        car2.drive();
        car2.honk();
        
        System.out.println("\n--- Person constructors demonstration ---");
        Person person1 = new Person(); // Calls all constructors in chain
        System.out.println();
        
        Person person2 = new Person("Alice");
        System.out.println();
        
        Person person3 = new Person("Bob", 25);
        System.out.println();
        
        Person person4 = new Person("Charlie", 30, "123 Main St");
        System.out.println();
        
        Person person5 = new Person(person4); // Copy constructor
        System.out.println();
        
        System.out.println("Person objects:");
        person1.display();
        person2.display();
        person3.display();
        person4.display();
        person5.display();
    }
}