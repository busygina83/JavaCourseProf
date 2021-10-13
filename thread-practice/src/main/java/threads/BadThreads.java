package threads;

public class BadThreads {

    static String message;
    //static Object lock = new Object();

    private static class CorrectorThread extends Thread {

        public void run() {
            message = "Помиловать";
        }
    }

    public static void main(String args[]) throws InterruptedException {

        for (int i=0; i<1000; i++) {
            CorrectorThread correctorThread = new CorrectorThread();
            message = "Казнить";
            Thread.sleep(10);
            correctorThread.start();
            //example getState
            System.out.println(correctorThread.getState());
            //
            correctorThread.join();
            if (message.equalsIgnoreCase("Казнить")) {
                System.out.println(message);
            }
        }
    }
}
