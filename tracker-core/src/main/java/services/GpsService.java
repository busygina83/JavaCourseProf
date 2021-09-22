package services;

import org.json.JSONException;
import jdev.dto.Point;
import queues.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class GpsService {

    public BlockingDeque<String> queue = new LinkedBlockingDeque<>(100);
    Point point=new Point();
    GpsEmulator gps = new GpsEmulator();

    @Scheduled(fixedDelay = 2000)
    public void put() throws InterruptedException, JSONException {
        Logger log = LoggerFactory.getLogger(GpsService.class);
        String toJson = gps.setEnd(point, "123",2000);
        System.out.println("toJson: "+toJson);
        queue.put(toJson);
        log.info("ScheduledService.put " + toJson);
    }
}
