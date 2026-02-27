// ===== OVERLOADING VS OVERRIDING DEMO =====
class Calculator {
    // Method Overloading - same method name, different parameters
    public int add(int a, int b) {
        System.out.println("Adding two integers");
        return a + b;
    }
    
    public double add(double a, double b) {
        System.out.println("Adding two doubles");
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        System.out.println("Adding three integers");
        return a + b + c;
    }
    
    public String add(String a, String b) {
        System.out.println("Concatenating strings");
        return a + b;
    }
}

class AdvancedCalculator extends Calculator {
    // Method Overriding - same method signature as parent
    @Override
    public int add(int a, int b) {
        System.out.println("AdvancedCalculator adding with validation");
        if (a < 0 || b < 0) {
            System.out.println("Warning: Adding negative numbers");
        }
        return super.add(a, b);
    }
    
    @Override
    public double add(double a, double b) {
        System.out.println("AdvancedCalculator adding doubles with rounding");
        double result = super.add(a, b);
        return Math.round(result * 100.0) / 100.0;
    }
    
    // New method specific to AdvancedCalculator
    public int add(int[] numbers) {
        System.out.println("Adding array of integers");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
}

// ===== ACCESSORS (GETTERS) AND MUTATORS (SETTERS) DEMO =====
class Person {
    // Private fields - encapsulation
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private double salary;
    
    // Constructor
    public Person(String name, int age) {
        this.name = name;
        setAge(age); // Using setter for validation
    }
    
    // Accessors (Getters)
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        // Masked phone number for privacy
        if (phoneNumber != null && phoneNumber.length() >= 10) {
            return phoneNumber.substring(0, 3) + "-***-**" + phoneNumber.substring(phoneNumber.length() - 4);
        }
        return phoneNumber;
    }
    
    public double getSalary() {
        return salary;
    }
    
    // Mutators (Setters) with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: Name cannot be empty");
        }
    }
    
    public void setAge(int age) {
        if (age > 0 && age < 150) {
            this.age = age;
        } else {
            System.out.println("Error: Age must be between 1 and 149");
        }
    }
    
    public void setEmail(String email) {
        // Simple email validation
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            System.out.println("Error: Invalid email format");
        }
    }
    
    public void setPhoneNumber(String phoneNumber) {
        // Simple phone validation - must be 10 digits
        if (phoneNumber != null && phoneNumber.matches("\\d{10}")) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Error: Phone number must be 10 digits");
        }
    }
    
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Salary cannot be negative");
        }
    }
    
    // Business methods
    public void displayInfo() {
        System.out.println("\nPerson Information:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + (email != null ? email : "Not set"));
        System.out.println("Phone: " + (phoneNumber != null ? getPhoneNumber() : "Not set"));
        System.out.println("Salary: $" + (salary > 0 ? salary : "Not set"));
    }
}

// Main class
public class Group3_OverridingOverloadingAccessors {
    public static void main(String[] args) {
        System.out.println("=== METHOD OVERLOADING DEMO ===");
        Calculator basicCalc = new Calculator();
        
        System.out.println("Sum of 5 and 3: " + basicCalc.add(5, 3));
        System.out.println("Sum of 5.5 and 3.7: " + basicCalc.add(5.5, 3.7));
        System.out.println("Sum of 1, 2, 3: " + basicCalc.add(1, 2, 3));
        System.out.println("Concatenation: " + basicCalc.add("Hello", " World"));
        
        System.out.println("\n=== METHOD OVERRIDING DEMO ===");
        AdvancedCalculator advCalc = new AdvancedCalculator();
        
        System.out.println("Advanced: " + advCalc.add(-5, 3));
        System.out.println("Advanced with doubles: " + advCalc.add(5.555, 3.333));
        
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Sum of array: " + advCalc.add(numbers));
        
        System.out.println("\n=== ACCESSORS AND MUTATORS DEMO ===");
        Person person = new Person("John Doe", 30);
        
        // Using mutators
        person.setEmail("john.doe@email.com");
        person.setPhoneNumber("1234567890");
        person.setSalary(50000);
        
        // Try invalid values
        person.setAge(200); // Should show error
        person.setEmail("invalid-email"); // Should show error
        person.setPhoneNumber("123"); // Should show error
        person.setSalary(-1000); // Should show error
        
        // Using accessors
        person.displayInfo();
        
        // Individual getters
        System.out.println("\nIndividual Getters:");
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Phone (masked): " + person.getPhoneNumber());
        System.out.println("Salary: $" + person.getSalary());
    }
}