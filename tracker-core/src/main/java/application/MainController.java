package application;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import queue.GpsEmulator;

import java.util.Map;

import static com.mongodb.client.model.Sorts.descending;

@Controller
public class MainController {
//    private static final String[] NAMES = { "Tom", "Jerry", "Donald" };

//    @Autowired
//    public PointRepository pointRepository;
//
//    @Autowired
//    public PointRepositoryCustom pointRepositoryCustom;

    Logger log = LoggerFactory.getLogger(MainController.class);
    Point point=new Point();
    GpsEmulator gps = new GpsEmulator();
    CrudDocument crud = new CrudDocument();

    @ResponseBody
    @RequestMapping("/testHome")
    public String testHome(@RequestParam(value="autoId", required=false, defaultValue="autoId123") String autoId) {
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAll'>Show All Coords</a></li>";
        html += " <li><a href='/showLast'>Show Last Point</a></li>";
        html += "</ul>";
        return html;
    }

    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert(@RequestParam(value="autoId", required=false, defaultValue="autoId123") String autoId) throws InterruptedException {
        for (int i=0; i<20; i++) {
            Thread.sleep(1000);
            String toJson = gps.setEnd(point, autoId, 1000);
            log.info("Point.put " + point);
            //log.info("ScheduledService.put " + toJson);
            //log.info("this.pointRepository " + pointRepository);
            crud.InsertGps(point);
        }
        return "Inserted: " + autoId;
    }

    @ResponseBody
    @RequestMapping("/showAll")
    public String showAllCoords() {

        String html = "";
        MongoCollection<Document> collection = crud.CreateColl("GpsEmulator");
        MongoCursor<Document> cursor = collection.find().projection((new Document("_id", false).append("lat", true).append("lon", true))).iterator();
        //cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                //System.out.println(cursor.next().toJson());
                for (Map.Entry<String, Object> entry : cursor.next().entrySet())
                {
                        html +=entry.getKey() + "/" + entry.getValue() + "<br>";
                        //System.out.println("entry.getKey()="+entry.getKey()+"/entry.getValue()="+entry.getValue());
                }
            }
        } finally {
            cursor.close();
        }
        return html;
    }

    @ResponseBody
    @RequestMapping("/showLast")
    public ResponseEntity showLastJson(@RequestParam(value="lastCnt", required=false, defaultValue="5") int lastCnt) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        MongoCollection<Document> collection = crud.CreateColl("GpsEmulator");
        MongoCursor<Document> cursor = collection.find().sort(descending("_id")).limit(lastCnt).iterator();
        JSONArray jarray = new JSONArray();
        try {
            while (cursor.hasNext()) {
                JSONObject json = new JSONObject(cursor.next().toJson());
                jarray.put(json);
            }
        } finally {
            cursor.close();
        }
        return new ResponseEntity(jarray.toString(1), responseHeaders, HttpStatus.OK);
    }


}
