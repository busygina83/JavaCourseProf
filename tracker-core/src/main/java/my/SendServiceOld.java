package my;

import service.GpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import service.SendService;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

@Service
public class SendServiceOld {
    @Autowired
    private GpsService gpsService;

    @Scheduled(fixedDelay = 1000)
    public void take() throws InterruptedException {
        Logger log = LoggerFactory.getLogger(SendService.class);
        BlockingDeque<String> queue = gpsService.queue;
        log.info("ScheduledService.take " + queue.poll(500, TimeUnit.MILLISECONDS));
//        log.info("ScheduledService.take " + queue.take());
    }
}
