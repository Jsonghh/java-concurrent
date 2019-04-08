import java.util.concurrent.TimeUnit;

/**
 * if synchroized the write method but not the read method
 * dirty read would happen often
 * <p>
 * as the read method is not synchrozed, we could read info that is under writing yet not finished
 * -> we should synchronize both the read and write methods
 */

public class Account {

// bank account name
    String name;

// bank account balnance
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(() -> a.set("Fred", 100.0)).start();
        System.out.println(a.getBalance()); // 0.0 
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance()); // 100.0
    }
}