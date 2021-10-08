package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

//@RestController
@Controller
public class CoodrsController {

    @Autowired
    GpsService gpsService;

    //@Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/postl")
    public String getLocation(@RequestParam(value="name", required=false, defaultValue="Noname") String name, Model model
    ) throws InterruptedException {
        //Object request=queue.poll(500, TimeUnit.MILLISECONDS);
        String request=gpsService.queue.take();
        model.addAttribute("name", name);
        model.addAttribute("locat", request);
        return "postlocation";
    }

    @RequestMapping(value = "/postc")
    public String getCoords(@RequestParam(value="name", required=false, defaultValue="Noname") String name, Model model
    ) throws InterruptedException {
        //Object request=queue.poll(500, TimeUnit.MILLISECONDS);
        String request=gpsService.queue.take();
        JSONObject obj = new JSONObject(request);
        model.addAttribute("name", name);
        model.addAttribute("latitude", obj.get("lat"));
        model.addAttribute("longitude", obj.get("lon"));
        return "postcoords";
    }

}

