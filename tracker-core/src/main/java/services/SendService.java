package services;

import java.util.concurrent.BlockingDeque;

//import com.fasterxml.jackson.core.JsonParser;
import controllers.TemplController;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GpsService gpsService;
    @Autowired
    private TemplController templController;

    @Scheduled(fixedDelay = 1000)
    public TemplController take() throws InterruptedException, ParseException {
        BlockingDeque<String> queue = gpsService.queue;
        //String fromQueue = queue.poll(500, TimeUnit.MILLISECONDS);
        String fromQueue = queue.take();
        Object obj = new JSONParser().parse(fromQueue);
        JSONObject jo = (JSONObject) obj;
        Double objLat = (Double) jo.get("lat");
        Double objLon = (Double) jo.get("lon");
        String createPostUrl= "http://localhost:8080/relay?location="+objLat+";"+objLon;
        ResponseEntity<TemplController> result = restTemplate.postForEntity(createPostUrl, gpsService, TemplController.class);
        Logger log = LoggerFactory.getLogger(SendService.class);
        log.info("result: "+result.getStatusCodeValue());
        return templController;
    }

}
