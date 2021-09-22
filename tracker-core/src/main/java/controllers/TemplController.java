package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TemplController {

    //@Autowired
    private RestTemplate restTemplate;
    Logger log = LoggerFactory.getLogger(TemplController.class);

    public TemplController(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TemplController() {}

    @RequestMapping(value = "/relay")
    public Response relay(@RequestParam(value="location", required=false, defaultValue="0;0") String location){
        //System.out.println(location);
        Response response;
        String [] locSplit = location.split(";");
        if (locSplit.length == 2) {
            response = new Response("{\"lat\":"+locSplit[0]+",\"lon\":"+locSplit[1]+"}", true);
        } else {
            response = new Response("{ERROR}", false);
        }
        log.info("response "+response);
        return response;
    }

    @RequestMapping(value = "/json")
    public String relayjson(@RequestParam(value="json", required=false, defaultValue="ERROR") String json){
        return "{"+json+"}";
    }

//    @RequestMapping(value = "/posts")
//    public Point[] getProductList() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//        return restTemplate.exchange("http://localhost:8080/json", HttpMethod.GET, entity, Point[].class).getBody();
//    }

}
