package jdev.dto;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointTest {

    private final String autoId = "o567gfd";
    private final String json = "{\"lat\":56.0,\"lon\":74.0,\"autoId\":\"o567gfd\",\"time\":1489900897458}";

    @Test
    public void encodeDto() throws Exception {
        Point point = new Point();
        point.setLat(56);
        point.setLon(74);
        point.setAutoId(autoId);
        point.setTime(System.currentTimeMillis());
        String pointJson = point.toJson();
        Assertions.assertTrue(pointJson.contains("\"lat\":56"));
        Assertions.assertTrue(pointJson.contains("\"time\":"));
        System.out.println("toJson:   "+ pointJson);
        System.out.println("toObject:   "+point.toObject());
    }

    @Test
    public void decodeDto() throws Exception {
        //ObjectMapper mapper = new ObjectMapper();
        //JSONObject mapper = new JSONObject();
        JSONObject mapper = new JSONObject(json);
        //Point dto = mapper.readValue(json, Point.class);
        Assertions.assertEquals(autoId, mapper.getString("autoId"));
        Assertions.assertEquals(1489900897458L, mapper.getLong("time"));
    }

}