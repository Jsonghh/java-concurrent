import java.util.concurrent.TimeUnit;

/**
 * Can we call the synchronized methods and asyncrhonized ones at the same time?
 * - Yes, surely we can.
 */
public class T {
    
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }
    
    public void m2() {
        System.out.println(Thread.currentThread().getName() + " m2 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}

// new Thread(t, "THREAD" + i).start();
// new Thread(t::m1, "threadName").start()
/* 
    new Thread(new Runnable() {
        public void run() {
            t.m1();
        }
    });
*/
