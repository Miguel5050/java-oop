// ===== INTERFACE EXAMPLES =====
interface Drawable {
    void draw();
    default void display() {
        System.out.println("Displaying " + getClass().getSimpleName());
    }
}

interface Resizable {
    void resize(double factor);
    double getSize();
}

interface Colorable {
    void setColor(String color);
    String getColor();
}

// ===== POLYMORPHISM THROUGH INTERFACES =====
class Circle implements Drawable, Resizable, Colorable {
    private double radius;
    private String color;
    
    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle with radius " + radius);
    }
    
    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.println("Circle resized. New radius: " + radius);
    }
    
    @Override
    public double getSize() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String getColor() {
        return color;
    }
}

class Rectangle implements Drawable, Resizable, Colorable {
    private double length;
    private double width;
    private String color;
    
    public Rectangle(double length, double width, String color) {
        this.length = length;
        this.width = width;
        this.color = color;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle " + length + "x" + width);
    }
    
    @Override
    public void resize(double factor) {
        length *= factor;
        width *= factor;
        System.out.println("Rectangle resized. New dimensions: " + length + "x" + width);
    }
    
    @Override
    public double getSize() {
        return length * width;
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String getColor() {
        return color;
    }
}

// ===== POLYMORPHISM THROUGH INHERITANCE =====
abstract class Vehicle {
    protected String brand;
    protected int year;
    
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    
    public abstract void start();
    public abstract void stop();
    public abstract void accelerate();
    
    public void displayInfo() {
        System.out.println(brand + " (" + year + ")");
    }
}

class Car extends Vehicle {
    private int doors;
    
    public Car(String brand, int year, int doors) {
        super(brand, year);
        this.doors = doors;
    }
    
    @Override
    public void start() {
        System.out.println(brand + " car: Turning key and starting engine");
    }
    
    @Override
    public void stop() {
        System.out.println(brand + " car: Applying brakes and stopping");
    }
    
    @Override
    public void accelerate() {
        System.out.println(brand + " car: Pressing accelerator pedal");
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    
    public Motorcycle(String brand, int year, boolean hasSidecar) {
        super(brand, year);
        this.hasSidecar = hasSidecar;
    }
    
    @Override
    public void start() {
        System.out.println(brand + " motorcycle: Kick starting and throttling");
    }
    
    @Override
    public void stop() {
        System.out.println(brand + " motorcycle: Squeezing brakes and stopping");
    }
    
    @Override
    public void accelerate() {
        System.out.println(brand + " motorcycle: Twisting throttle");
    }
}

class Bicycle extends Vehicle {
    public Bicycle(String brand, int year) {
        super(brand, year);
    }
    
    @Override
    public void start() {
        System.out.println(brand + " bicycle: Pushing off and pedaling");
    }
    
    @Override
    public void stop() {
        System.out.println(brand + " bicycle: Squeezing hand brakes");
    }
    
    @Override
    public void accelerate() {
        System.out.println(brand + " bicycle: Pedaling faster");
    }
}

// Main class
public class Group4_PolymorphismInterfaces {
    public static void main(String[] args) {
        System.out.println("=== POLYMORPHISM THROUGH INTERFACES ===");
        
        // Array of Drawable objects
        Drawable[] drawables = {
            new Circle(5, "Red"),
            new Rectangle(4, 6, "Blue")
        };
        
        // Polymorphic method calls
        for (Drawable drawable : drawables) {
            drawable.draw();
            drawable.display();
            
            if (drawable instanceof Resizable) {
                ((Resizable) drawable).resize(2);
            }
            
            if (drawable instanceof Colorable) {
                System.out.println("Color: " + ((Colorable) drawable).getColor());
            }
            
            if (drawable instanceof Resizable) {
                System.out.println("Area: " + ((Resizable) drawable).getSize());
            }
            System.out.println();
        }
        
        System.out.println("\n=== POLYMORPHISM THROUGH INHERITANCE ===");
        
        // Array of Vehicle objects
        Vehicle[] vehicles = {
            new Car("Toyota", 2020, 4),
            new Motorcycle("Harley", 2019, false),
            new Bicycle("Trek", 2021)
        };
        
        // Polymorphic method calls
        for (Vehicle vehicle : vehicles) {
            vehicle.displayInfo();
            vehicle.start();
            vehicle.accelerate();
            vehicle.stop();
            System.out.println();
        }
        
        // Interface polymorphism with method parameters
        System.out.println("=== INTERFACE AS METHOD PARAMETER ===");
        processDrawable(new Circle(3, "Green"));
        processDrawable(new Rectangle(2, 3, "Yellow"));
    }
    
    // Method accepting interface type
    public static void processDrawable(Drawable drawable) {
        System.out.println("Processing drawable:");
        drawable.draw();
        drawable.display();
    }
}