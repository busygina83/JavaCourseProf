package stopping;
import java.sql.Timestamp;

/**
 * Created jdev on 07.06.2017.
 */
public class ThreadStop {

    public static void main (String[] args) throws InterruptedException {

        stopping.StoppedThread stopped = new stopping.StoppedThread();

        System.out.println(new Timestamp(System.currentTimeMillis()) + ":   после создания, до запуска => " + stopped.getState());


        //запуск нити, но это не run()!!!!
        stopped.start();

        System.out.println(new Timestamp(System.currentTimeMillis()) + ":   после запуска, до завершения => " + stopped.getState());

        //мы не дождемся завершения так как цикл бесконечен
        stopped.join(10_000);

        //stopped.setNeedStop(true);
        //stopped.join();

        System.out.println(new Timestamp(System.currentTimeMillis()) + ":   после завершения => " + stopped.getState());


        stopping.StoppedThread stopped2 = new stopping.StoppedThread();

        System.out.println(new Timestamp(System.currentTimeMillis()) + ":   после создания, до запуска stopped2 => " + stopped2.getState());


        //запуск нити, но это не run()!!!!
        stopped2.start();

        System.out.println(new Timestamp(System.currentTimeMillis()) + ":   после запуска, до завершения stopped2 => " + stopped2.getState());

        //мы не дождемся завершения так как цикл бесконечен
        //stopped2.join();

//        stopped2.setNeedStop(true);
        //останавливаем через stop - но это не рекомендуется, объясняется в самом методе, лучше через переменную
        stopped2.stop();
        stopped2.join();

        System.out.println(new Timestamp(System.currentTimeMillis()) + ":   после завершения stopped2 => " + stopped2.getState());

    }
}
