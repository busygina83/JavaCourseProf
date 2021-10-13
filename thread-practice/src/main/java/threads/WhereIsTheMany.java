package threads;

/**
 * Created by jdev on 04.06.2017.
 */
public class WhereIsTheMany {
    static int account = 0;
    static int transNum = 100_000;
    static int threadNum = 200;

    static Object lock = new Object();

    public static void main(String... args) throws InterruptedException {
        Runnable transaction = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //присваиваем переменным текущие параметры потока
                long getId = Thread.currentThread().getId();
                String getName = Thread.currentThread().getName();

                //если id потока кратно 10 вызовем yield ()
                if (getId % 10 == 0) {
                    Thread.yield();
                    System.out.println(getName + " yielded");
                }

                //находим остаток от деления id потока на 3
                long j = getId%3;

                //каждому третьему потоку присваиваем соответствующий приоритет
                if (j==0) {Thread.currentThread().setPriority(Thread.MIN_PRIORITY);}
                else if (j==1) {Thread.currentThread().setPriority(Thread.NORM_PRIORITY);}
                else if (j==2) {Thread.currentThread().setPriority(Thread.MAX_PRIORITY);}
                System.out.println("j=" + j + "; " + getId + "_" + getName + " has "
                        + Thread.currentThread().getPriority() + " priority");

                for (int i = 0; i < transNum; i++) {
                    synchronized (lock) {
                        WhereIsTheMany.account++;
                    }
                }
            }
        };

        //создаем заданное число нитей, и запускаем их на выполнение
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(transaction);
            threads[i].start();
        }

        //начинаем ждать завершения всех нитей
        for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];
            thread.join();
        }
        //если мы здесь то значит все нити завершили выполнение, выводим результат
        System.out.println("account = [" + account + "]" + " must be = [" + transNum*threadNum + "]");

        //удивляемся если разница не равна нулю
        if (transNum*threadNum - account != 0)
            System.out.println("where is my : " + (transNum*threadNum - account) + "$ !!!!!");
    }
}
