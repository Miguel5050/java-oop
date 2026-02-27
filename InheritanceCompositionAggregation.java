import java.util.ArrayList;
import java.util.List;

// ===== INHERITANCE EXAMPLE =====
class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    
    @Override
    public void eat() {
        System.out.println(name + " the dog is eating dog food");
    }
    
    public void bark() {
        System.out.println(name + " is barking: Woof! Woof!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed);
    }
}

class Cat extends Animal {
    private boolean isIndoor;
    
    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }
    
    @Override
    public void eat() {
        System.out.println(name + " the cat is eating cat food");
    }
    
    public void meow() {
        System.out.println(name + " says: Meow!");
    }
}

// ===== COMPOSITION EXAMPLE (Strong relationship - parts cannot exist without whole) =====
class Room {
    private String type;
    private double area;
    
    public Room(String type, double area) {
        this.type = type;
        this.area = area;
    }
    
    public void describe() {
        System.out.println(type + " room, Area: " + area + " sq ft");
    }
}

class House {
    private String address;
    private List<Room> rooms; // Composition: Rooms are created with the house
    
    public House(String address) {
        this.address = address;
        this.rooms = new ArrayList<>();
        // Rooms are created and owned by the house
        rooms.add(new Room("Living", 300));
        rooms.add(new Room("Kitchen", 150));
        rooms.add(new Room("Bedroom", 200));
    }
    
    public void describeHouse() {
        System.out.println("House at: " + address);
        System.out.println("Contains:");
        for (Room room : rooms) {
            room.describe();
        }
    }
}

// ===== AGGREGATION EXAMPLE (Weak relationship - parts can exist independently) =====
class Student {
    private String name;
    private String studentId;
    
    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }
    
    public void study() {
        System.out.println(name + " is studying");
    }
    
    @Override
    public String toString() {
        return name + " (ID: " + studentId + ")";
    }
}

class University {
    private String name;
    private List<Student> students; // Aggregation: Students can exist without the university
    
    public University(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        students.add(student);
        System.out.println(student + " added to " + name);
    }
    
    public void removeStudent(Student student) {
        students.remove(student);
        System.out.println(student + " removed from " + name);
    }
    
    public void listStudents() {
        System.out.println("\nStudents at " + name + ":");
        for (Student student : students) {
            System.out.println("- " + student);
        }
    }
}

// Main class
public class Group2_InheritanceCompositionAggregation {
    public static void main(String[] args) {
        System.out.println("=== INHERITANCE DEMO ===");
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        Cat cat = new Cat("Whiskers", 2, true);
        
        dog.displayInfo();
        dog.eat();
        dog.bark();
        
        System.out.println();
        cat.displayInfo();
        cat.eat();
        cat.meow();
        
        System.out.println("\n=== COMPOSITION DEMO ===");
        House house = new House("123 Main St");
        house.describeHouse();
        // If house is destroyed, rooms are destroyed too
        
        System.out.println("\n=== AGGREGATION DEMO ===");
        Student student1 = new Student("Alice", "S001");
        Student student2 = new Student("Bob", "S002");
        Student student3 = new Student("Charlie", "S003");
        
        University university = new University("Tech University");
        university.addStudent(student1);
        university.addStudent(student2);
        university.addStudent(student3);
        
        university.listStudents();
        
        // Students can exist even if removed from university
        university.removeStudent(student2);
        System.out.println("\nAfter removing Bob:");
        university.listStudents();
        
        System.out.println("\n" + student2 + " still exists independently");
    }
}