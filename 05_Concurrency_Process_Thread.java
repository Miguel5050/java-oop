// ===== PROCESS DEMO (Simulated) =====
class ProcessDemo {
    public static void demonstrateProcess() {
        System.out.println("=== PROCESS DEMONSTRATION ===");
        System.out.println("Current Process ID: " + ProcessHandle.current().pid());
        System.out.println("Process Information:");
        
        // Get current process info
        ProcessHandle currentProcess = ProcessHandle.current();
        ProcessHandle.Info info = currentProcess.info();
        
        System.out.println("Command: " + info.command().orElse("N/A"));
        System.out.println("Start Time: " + info.startInstant().orElse(null));
        System.out.println("Total CPU Time: " + info.totalCpuDuration().orElse(null));
        
        // List all running processes (simplified)
        System.out.println("\nListing some running processes:");
        ProcessHandle.allProcesses()
            .limit(5)
            .forEach(process -> {
                System.out.println("PID: " + process.pid() + 
                    ", Cmd: " + process.info().command().orElse("N/A"));
            });
    }
}

// ===== THREAD DEMO =====

// Method 1: Extending Thread class
class MyThread extends Thread {
    private String threadName;
    
    public MyThread(String name) {
        this.threadName = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " - Count: " + i + 
                             " (Priority: " + getPriority() + ")");
            
            // Simulate some work
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted");
            }
        }
        System.out.println(threadName + " finished");
    }
}

// Method 2: Implementing Runnable interface
class MyRunnable implements Runnable {
    private String taskName;
    
    public MyRunnable(String name) {
        this.taskName = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(taskName + " - Step " + i);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted");
            }
        }
        System.out.println(taskName + " completed");
    }
}

// Thread with synchronization example
class Counter {
    private int count = 0;
    
    // Synchronized method to prevent race condition
    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + 
                         " incremented count to: " + count);
    }
    
    public int getCount() {
        return count;
    }
}

class CounterThread extends Thread {
    private Counter counter;
    
    public CounterThread(Counter counter, String name) {
        super(name);
        this.counter = counter;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            counter.increment();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Main class
public class Group5_ConcurrencyProcessThread {
    public static void main(String[] args) {
        // Process demonstration
        ProcessDemo.demonstrateProcess();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== THREAD DEMONSTRATION ===");
        System.out.println("Main Thread: " + Thread.currentThread().getName() + 
                         " (Priority: " + Thread.currentThread().getPriority() + ")");
        
        // Creating threads by extending Thread class
        System.out.println("\n--- Threads extending Thread class ---");
        MyThread thread1 = new MyThread("Thread-1");
        MyThread thread2 = new MyThread("Thread-2");
        
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        
        thread1.start();
        thread2.start();
        
        // Wait for threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Creating threads by implementing Runnable
        System.out.println("\n--- Threads implementing Runnable ---");
        MyRunnable runnable1 = new MyRunnable("Task-1");
        MyRunnable runnable2 = new MyRunnable("Task-2");
        
        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Synchronization example
        System.out.println("\n--- Thread Synchronization Example ---");
        Counter sharedCounter = new Counter();
        
        CounterThread[] threads = new CounterThread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new CounterThread(sharedCounter, "CounterThread-" + (i+1));
            threads[i].start();
        }
        
        // Wait for all counter threads to finish
        for (CounterThread ct : threads) {
            try {
                ct.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Final counter value: " + sharedCounter.getCount());
        
        // Thread states demonstration
        System.out.println("\n=== THREAD STATES DEMONSTRATION ===");
        demonstrateThreadStates();
    }
    
    private static void demonstrateThreadStates() {
        Thread thread = new Thread(() -> {
            System.out.println("Thread running - State: " + 
                             Thread.currentThread().getState());
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("After creation - State: " + thread.getState());
        
        thread.start();
        System.out.println("After start - State: " + thread.getState());
        
        try {
            Thread.sleep(100);
            System.out.println("During execution - State: " + thread.getState());
            
            thread.join();
            System.out.println("After completion - State: " + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}