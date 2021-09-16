package controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import services.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.BlockingDeque;

//@RestController
@Controller
public class CoodrsController {

    @Autowired
    private GpsService gpsService;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/postl")
    public String getLocation(@RequestParam(value="name", required=false, defaultValue="Noname") String name, Model model
    ) throws InterruptedException {
        BlockingDeque<String> queue = gpsService.queue;
        //Object request=queue.poll(500, TimeUnit.MILLISECONDS);
        String request=queue.take();
        model.addAttribute("name", name);
        model.addAttribute("locat", request);
        return "postlocation";
    }

    @RequestMapping(value = "/postc")
    public String getCoords(@RequestParam(value="name", required=false, defaultValue="Noname") String name, Model model
    ) throws InterruptedException, ParseException {
        BlockingDeque<String> queue = gpsService.queue;
        //Object request=queue.poll(500, TimeUnit.MILLISECONDS);
        String request=queue.take();
        Object obj = new JSONParser().parse(request);
        JSONObject jo = (JSONObject) obj;
        model.addAttribute("name", name);
        model.addAttribute("latitude", jo.get("lat"));
        model.addAttribute("longitude", jo.get("lon"));
        return "postcoords";
    }

}

