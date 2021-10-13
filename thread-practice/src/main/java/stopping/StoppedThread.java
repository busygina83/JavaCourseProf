package stopping;

import java.sql.Timestamp;

/**
 * Created by jdev on 07.06.2017.
 */
public class StoppedThread extends Thread {
    public void setNeedStop(boolean needStop) {
        this.needStop = needStop;
    }

    private boolean needStop;

    @Override
    public void run() {
        for(;;) {
            if (needStop) {
                System.out.println(new Timestamp(System.currentTimeMillis()) + ":   needStop == true -> exitting");
                break;
            }
        }
    }
}
