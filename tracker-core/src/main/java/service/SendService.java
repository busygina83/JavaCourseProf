package service;

import java.util.concurrent.BlockingDeque;

import controller.TemplController;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendService {

    //@Autowired
    public RestTemplate restTemplate;

    @Autowired
    public GpsService gpsService;

    @Scheduled(fixedDelay = 3000)
    public ResponseEntity<TemplController> take() throws InterruptedException {
        BlockingDeque<String> queue = gpsService.queue;
        //String fromQueue = queue.poll(500, TimeUnit.MILLISECONDS);
        String fromQueue = queue.take();
        System.out.println("fromQueue: "+fromQueue);
        JSONObject obj = new JSONObject(fromQueue);
        double objLat = obj.getDouble("lat");
        double objLon = obj.getDouble("lon");
        String createPostUrl= "http://localhost:8080/relay?location="+objLat+";"+objLon;
        ResponseEntity<TemplController> result = restTemplate.postForEntity(createPostUrl, gpsService, TemplController.class);
        Logger log = LoggerFactory.getLogger(SendService.class);
        log.info("result: "+result.getStatusCodeValue());
        System.out.println("result: "+result.getStatusCode());
        return result;
    }

}
