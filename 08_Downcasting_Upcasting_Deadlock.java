// ===== CLASSES FOR CASTING DEMONSTRATION =====
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void displayInfo() {
        System.out.println("Animal: " + name);
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
    
    // Dog-specific method
    public void fetch() {
        System.out.println(name + " is fetching the ball");
    }
    
    public void wagTail() {
        System.out.println(name + " is wagging tail");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow! Meow!");
    }
    
    // Cat-specific method
    public void scratch() {
        System.out.println(name + " is scratching");
    }
    
    public void purr() {
        System.out.println(name + " is purring");
    }
}

// Another class for casting demo
class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " chirps: Chirp! Chirp!");
    }
    
    public void fly() {
        System.out.println(name + " is flying");
    }
}

// ===== DEADLOCK DEMONSTRATION =====
class DeadlockDemo {
    // Two resources
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();
    
    public void demonstrateDeadlock() {
        System.out.println("\n=== DEADLOCK DEMONSTRATION ===");
        System.out.println("Creating deadlock situation...\n");
        
        // Thread 1 tries to lock resource1 then resource2
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked resource 1");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread 1: Waiting for resource 2...");
                
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });
        
        // Thread 2 tries to lock resource2 then resource1 (opposite order)
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Locked resource 2");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread 2: Waiting for resource 1...");
                
                synchronized (resource1) {
                    System.out.println("Thread 2: Locked resource 1");
                }
            }
        });
        
        thread1.start();
        thread2.start();
        
        // Let them run for a while to see deadlock
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nChecking thread states:");
        System.out.println("Thread 1 state: " + thread1.getState());
        System.out.println("Thread 2 state: " + thread2.getState());
        
        if (thread1.getState() == Thread.State.BLOCKED && 
            thread2.getState() == Thread.State.BLOCKED) {
            System.out.println("\n⚠ DEADLOCK DETECTED! Both threads are blocked waiting for each other.");
            System.out.println("This is a classic deadlock situation.");
        }
        
        // Note: In a real application, we'd need to handle this
        // For demo purposes, we'll interrupt the threads
        thread1.interrupt();
        thread2.interrupt();
    }
    
    // Deadlock prevention example - always lock resources in the same order
    public void preventDeadlock() {
        System.out.println("\n=== DEADLOCK PREVENTION ===");
        System.out.println("Locking resources in consistent order...\n");
        
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread A: Locked resource 1");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                synchronized (resource2) {
                    System.out.println("Thread A: Locked resource 2");
                }
            }
            System.out.println("Thread A: Completed successfully");
        });
        
        Thread thread2 = new Thread(() -> {
            synchronized (resource1) { // Same order: resource1 first, then resource2
                System.out.println("Thread B: Locked resource 1");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                synchronized (resource2) {
                    System.out.println("Thread B: Locked resource 2");
                }
            }
            System.out.println("Thread B: Completed successfully");
        });
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Both threads completed without deadlock!");
    }
}

// Main class
public class Group8_DowncastingUpcastingDeadlock {
    public static void main(String[] args) {
        System.out.println("=== UPCASTING AND DOWNCASTING DEMONSTRATION ===\n");
        
        // ===== UPCASTING EXAMPLES =====
        System.out.println("--- UPCASTING (Child to Parent) ---");
        
        // Creating objects
        Dog dog = new Dog("Buddy");
        Cat cat = new Cat("Whiskers");
        Bird bird = new Bird("Tweety");
        
        // Upcasting - implicit and safe
        Animal animal1 = dog;  // Upcasting Dog to Animal
        Animal animal2 = cat;  // Upcasting Cat to Animal
        Animal animal3 = bird; // Upcasting Bird to Animal
        
        System.out.println("Upcasting performed:");
        System.out.println("animal1 (Dog upcast to Animal)");
        System.out.println("animal2 (Cat upcast to Animal)");
        System.out.println("animal3 (Bird upcast to Animal)");
        
        // Using upcasted references - can only call Animal methods
        System.out.println("\nCalling methods through upcasted references:");
        animal1.displayInfo();
        animal1.makeSound(); // Calls overridden method (runtime polymorphism)
        animal1.eat();
        
        System.out.println();
        animal2.displayInfo();
        animal2.makeSound(); // Calls overridden method
        
        System.out.println("\nUpcasting allows treating all animals uniformly:");
        Animal[] animals = {animal1, animal2, animal3};
        for (Animal animal : animals) {
            animal.makeSound(); // Polymorphic behavior
        }
        
        // ===== DOWNCASTING EXAMPLES =====
        System.out.println("\n--- DOWNCASTING (Parent to Child) ---");
        System.out.println("Downcasting requires explicit casting and can be risky!");
        
        // Correct downcasting with instanceof check
        System.out.println("\nCorrect downcasting (with instanceof check):");
        if (animal1 instanceof Dog) {
            Dog downcastedDog = (Dog) animal1; // Explicit downcasting
            System.out.println("Successfully downcasted animal1 to Dog");
            downcastedDog.fetch(); // Now we can call Dog-specific methods
            downcastedDog.wagTail();
        }
        
        if (animal2 instanceof Cat) {
            Cat downcastedCat = (Cat) animal2;
            System.out.println("\nSuccessfully downcasted animal2 to Cat");
            downcastedCat.scratch();
            downcastedCat.purr();
        }
        
        // Demonstrating ClassCastException (commented out to prevent crash)
        System.out.println("\n--- DANGER: Incorrect downcasting ---");
        System.out.println("The following would cause ClassCastException:");
        System.out.println("Dog wrongDog = (Dog) animal2; // animal2 is actually a Cat!");
        System.out.println("This is why instanceof check is crucial!");
        
        // Safe downcasting demonstration
        System.out.println("\n--- Safe downcasting practice ---");
        safeDowncast(animal1);
        safeDowncast(animal2);
        safeDowncast(animal3);
        
        // ===== DEADLOCK DEMONSTRATION =====
        DeadlockDemo deadlockDemo = new DeadlockDemo();
        deadlockDemo.demonstrateDeadlock();
        
        // Wait a bit before showing prevention
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        deadlockDemo.preventDeadlock();
        
        // Summary
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== SUMMARY ===");
        System.out.println("UPCASTING:");
        System.out.println("- Converting child class reference to parent class");
        System.out.println("- Always safe and implicit");
        System.out.println("- Example: Animal a = new Dog();");
        System.out.println("\nDOWNCASTING:");
        System.out.println("- Converting parent class reference to child class");
        System.out.println("- Requires explicit cast and instanceof check");
        System.out.println("- Example: Dog d = (Dog) animalRef;");
        System.out.println("\nDEADLOCK:");
        System.out.println("- Situation where two or more threads are blocked forever");
        System.out.println("- Caused by circular dependency in resource locking");
        System.out.println("- Prevention: Always lock resources in the same order");
    }
    
    // Method demonstrating safe downcasting
    public static void safeDowncast(Animal animal) {
        System.out.println("\nAttempting to process animal: " + animal.name);
        
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dog.fetch();
            dog.wagTail();
        } else if (animal instanceof Cat) {
            Cat cat = (Cat) animal;
            cat.scratch();
            cat.purr();
        } else if (animal instanceof Bird) {
            Bird bird = (Bird) animal;
            bird.fly();
        } else {
            System.out.println("Unknown animal type");
        }
    }
}