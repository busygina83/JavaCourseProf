import jdev.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import queues.GpsEmulator;
import services.SendService;

@SpringBootApplication
public class Answer {

    static Point point=new Point();
    static GpsEmulator gps = new GpsEmulator();
    static HttpHeaders headers = new HttpHeaders();
    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String... args) throws Exception {
        while (true) {
            String requestJson = gps.setEnd(point, "321", 2000);
            requestJson = requestJson.replace("{","").replace("}","");
            String url = "http://localhost:8080/json?json="+requestJson;
            //System.out.println("url = " + url);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
            Point answer = restTemplate.postForObject(url, entity, Point.class);
            Logger log = LoggerFactory.getLogger(Answer.class);
            log.info("answer: " + answer);

            log.info("==================================================");
            Thread.sleep(2000);
        }
    }

}
