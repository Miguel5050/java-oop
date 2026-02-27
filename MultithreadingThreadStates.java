import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// ===== PROCESS DEMONSTRATION =====
class ProcessInfo {
    public static void demonstrateProcesses() {
        System.out.println("=== PROCESS DEMONSTRATION ===");
        System.out.println("A process is an independent program with its own memory space.");
        
        // Get current process info
        ProcessHandle currentProcess = ProcessHandle.current();
        ProcessHandle.Info info = currentProcess.info();
        
        System.out.println("\nCurrent Process Information:");
        System.out.println("PID: " + currentProcess.pid());
        System.out.println("Command: " + info.command().orElse("N/A"));
        System.out.println("Arguments: " + info.arguments().map(arr -> String.join(" ", arr)).orElse("N/A"));
        System.out.println("Start Time: " + info.startInstant().orElse(null));
        System.out.println("Total CPU Time: " + info.totalCpuDuration().orElse(null));
        System.out.println("User: " + info.user().orElse("N/A"));
        
        // List all processes (showing first 5)
        System.out.println("\nAll Running Processes (first 5):");
        ProcessHandle.allProcesses()
            .limit(5)
            .forEach(process -> {
                System.out.printf("PID: %5d, Cmd: %s%n", 
                    process.pid(), 
                    process.info().command().orElse("N/A"));
            });
    }
}

// ===== THREAD CREATION METHODS =====
class ThreadStateDemo {
    public static void demonstrateThreadStates() {
        System.out.println("\n=== THREAD STATES DEMONSTRATION ===");
        
        // Create a thread and observe its states
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread running - State: " + Thread.currentThread().getState());
                
                // Simulate some work
                Thread.sleep(500);
                
                synchronized (ThreadStateDemo.class) {
                    Thread.sleep(200);
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "StateDemoThread");
        
        System.out.println("NEW State - After creation: " + thread.getState());
        
        thread.start();
        System.out.println("RUNNABLE State - After start(): " + thread.getState());
        
        try {
            Thread.sleep(100);
            System.out.println("TIMED_WAITING State - During sleep(): " + thread.getState());
            
            Thread.sleep(300);
            
            thread.join();
            System.out.println("TERMINATED State - After completion: " + thread.getState());
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Method 1: Extending Thread class
class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        System.out.println(getName() + " (Priority: " + getPriority() + ") started");
        
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " - Count: " + i);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted");
                return;
            }
        }
        
        System.out.println(getName() + " completed");
    }
}

// Method 2: Implementing Runnable interface
class MyRunnable implements Runnable {
    private String taskName;
    
    public MyRunnable(String taskName) {
        this.taskName = taskName;
    }
    
    @Override
    public void run() {
        System.out.println(taskName + " started");
        
        for (int i = 1; i <= 3; i++) {
            System.out.println(taskName + " - Step " + i);
            
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted");
                return;
            }
        }
        
        System.out.println(taskName + " completed");
    }
}

// Method 3: Using Callable and Future
class MyCallable implements Callable<String> {
    private String taskName;
    private int duration;
    
    public MyCallable(String taskName, int duration) {
        this.taskName = taskName;
        this.duration = duration;
    }
    
    @Override
    public String call() throws Exception {
        System.out.println(taskName + " (Callable) started");
        Thread.sleep(duration);
        return taskName + " completed after " + duration + "ms";
    }
}

// ===== THREAD SYNCHRONIZATION EXAMPLES =====
class BankAccount {
    private int balance;
    private final Object lock = new Object();
    
    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }
    
    // Synchronized method
    public synchronized void withdraw(int amount, String threadName) {
        if (balance >= amount) {
            System.out.println(threadName + " is withdrawing $" + amount);
            
            try {
                Thread.sleep(100); // Simulate processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            balance -= amount;
            System.out.println(threadName + " completed withdrawal. Balance: $" + balance);
        } else {
            System.out.println(threadName + " - Insufficient funds! Balance: $" + balance);
        }
    }
    
    // Synchronized block (more efficient)
    public void deposit(int amount, String threadName) {
        synchronized(lock) {
            System.out.println(threadName + " is depositing $" + amount);
            balance += amount;
            System.out.println(threadName + " deposited. Balance: $" + balance);
        }
    }
    
    public int getBalance() {
        return balance;
    }
}

// ===== THREAD POOL EXAMPLE =====
class ThreadPoolDemo {
    public static void demonstrateThreadPool() {
        System.out.println("\n=== THREAD POOL DEMONSTRATION ===");
        
        // Create a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit tasks
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " running on " + 
                                 Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " completed");
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// ===== DAEMON THREAD EXAMPLE =====
class DaemonThreadDemo {
    public static void demonstrateDaemonThread() {
        System.out.println("\n=== DAEMON THREAD DEMONSTRATION ===");
        
        Thread daemonThread = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("Daemon thread running... count: " + count++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        daemonThread.setDaemon(true);
        daemonThread.setName("Background-Daemon");
        
        daemonThread.start();
        
        // Main thread continues
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Main thread ending. Daemon will automatically terminate.");
    }
}

// ===== THREAD PRIORITY DEMONSTRATION =====
class PriorityDemo extends Thread {
    public PriorityDemo(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " (Priority: " + getPriority() + ") - " + i);
            
            // Yield to allow other threads to run
            Thread.yield();
        }
    }
}

// ===== THREAD INTERRUPTION DEMO =====
class InterruptibleTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Interruptible task started");
        
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Working...");
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Interrupted during sleep!");
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }
        
        System.out.println("Task was interrupted, cleaning up and exiting");
    }
}

// Main class
public class Group9_MultithreadingThreadStates {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("JAVA MULTITHREADING, CONCURRENCY & THREAD STATES");
        System.out.println("=========================================\n");
        
        System.out.println("Main Thread: " + Thread.currentThread().getName());
        System.out.println("Main Thread Priority: " + Thread.currentThread().getPriority());
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        
        // Process demonstration
        ProcessInfo.demonstrateProcesses();
        
        // Thread states demonstration
        ThreadStateDemo.demonstrateThreadStates();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== THREAD CREATION METHODS ===");
        
        // Method 1: Extending Thread class
        System.out.println("\n--- Method 1: Extending Thread class ---");
        MyThread thread1 = new MyThread("Thread-1");
        MyThread thread2 = new MyThread("Thread-2");
        
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Method 2: Implementing Runnable interface
        System.out.println("\n--- Method 2: Implementing Runnable ---");
        Thread t1 = new Thread(new MyRunnable("Runnable-1"));
        Thread t2 = new Thread(new MyRunnable("Runnable-2"));
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Method 3: Using Callable and Future
        System.out.println("\n--- Method 3: Callable and Future ---");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        Future<String> future1 = executor.submit(new MyCallable("Callable-1", 1000));
        Future<String> future2 = executor.submit(new MyCallable("Callable-2", 1500));
        
        try {
            System.out.println("Future 1 result: " + future1.get());
            System.out.println("Future 2 result: " + future2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        executor.shutdown();
        
        // Thread synchronization example
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== THREAD SYNCHRONIZATION ===");
        
        BankAccount account = new BankAccount(1000);
        
        // Create multiple threads accessing same account
        Thread[] withdrawalThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int amount = 200;
            final String threadName = "Withdraw-Thread-" + (i + 1);
            
            withdrawalThreads[i] = new Thread(() -> {
                account.withdraw(amount, threadName);
            });
        }
        
        // Start all threads
        for (Thread t : withdrawalThreads) {
            t.start();
        }
        
        // Wait for all to complete
        for (Thread t : withdrawalThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Final Balance: $" + account.getBalance());
        
        // Thread pool demonstration
        ThreadPoolDemo.demonstrateThreadPool();
        
        // Daemon thread demonstration
        DaemonThreadDemo.demonstrateDaemonThread();
        
        // Thread priority demonstration
        System.out.println("\n=== THREAD PRIORITY DEMONSTRATION ===");
        PriorityDemo lowPriority = new PriorityDemo("Low-Priority");
        PriorityDemo highPriority = new PriorityDemo("High-Priority");
        
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        highPriority.setPriority(Thread.MAX_PRIORITY);
        
        lowPriority.start();
        highPriority.start();
        
        try {
            lowPriority.join();
            highPriority.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Thread interruption demonstration
        System.out.println("\n=== THREAD INTERRUPTION DEMONSTRATION ===");
        Thread interruptible = new Thread(new InterruptibleTask());
        interruptible.start();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Interrupting the task...");
        interruptible.interrupt();
        
        try {
            interruptible.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Summary
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== SUMMARY: PROCESSES vs THREADS ===");
        System.out.println("PROCESSES:");
        System.out.println("- Independent execution units");
        System.out.println("- Have their own memory space");
        System.out.println("- Inter-process communication is expensive");
        System.out.println("- Example: Running multiple Java programs");
        
        System.out.println("\nTHREADS:");
        System.out.println("- Lightweight sub-processes");
        System.out.println("- Share memory space within a process");
        System.out.println("- Communication is easy via shared memory");
        System.out.println("- States: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED");
    }
}