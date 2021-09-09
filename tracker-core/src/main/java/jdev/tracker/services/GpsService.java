package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.Point;
import jdev.tracker.EmulatorGPS;
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
    EmulatorGPS gps = new EmulatorGPS();

    @Scheduled(fixedDelay = 2000)
    public void put() throws InterruptedException, JsonProcessingException {
        Logger log = LoggerFactory.getLogger(GpsService.class);
        String toJson = gps.setEnd(point, "123",2000);
        log.info("ScheduledService.put " + toJson);
        queue.put(toJson);
    }
}
