package my;

import application.Point;
import queue.*;
import service.GpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by jdev on 07.03.2017.
 */
public class Main {

    public static void main(String... args) throws Exception {
        Point point=new Point();
        GpsEmulator gps = new GpsEmulator();
        Logger log = LoggerFactory.getLogger(GpsService.class);
        BlockingDeque<String> queue = new LinkedBlockingDeque<>(100);
        for (int i=0;; i++) {
            Thread.sleep(2000);
            String toJson = gps.setEnd(point, "1234",2000);
            //System.out.println(toJson);
            log.info("ScheduledService.put " + toJson);
            queue.put(toJson);
        }
    }

}
