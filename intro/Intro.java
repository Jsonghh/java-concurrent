public class Intro implements Runnable {
    private int count = 10;
    public  synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()
                            + " count = " + count);
    }

    public static void main(String[] args) {
        Intro t = new Intro();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}