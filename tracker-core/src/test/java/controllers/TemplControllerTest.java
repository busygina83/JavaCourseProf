package controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TemplControllerTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TemplController controller = new TemplController(restTemplate);

    @Test
    public void relay() throws Exception {
        Response result1 = controller.relay("56.4;34.6");
        Assertions.assertEquals("{\"lat\":56.4,\"lon\":34.6}", result1.message);
        Assertions.assertTrue(result1.success);

        Response result2 = controller.relay("56.4");
        Assertions.assertEquals("{ERROR}", result2.message);
        Assertions.assertFalse(result2.success);
    }

    @Test
    public void relayjson() throws Exception {
        String result1 = controller.relayjson("lat56.4,lon34.6");
        Assertions.assertEquals("{lat56.4,lon34.6}", result1);

        String result2 = controller.relayjson("ERROR");
        Assertions.assertEquals("{ERROR}", result2);
    }

    @Test
    public void relayIntegration() throws Exception {
        Response result = new TemplController(restTemplate).relay("56.4;34.6");
        assertNotNull(result);
        System.out.println(result.message);
        assertEquals(result.message, "{\"lat\":56.4,\"lon\":34.6}");
    }
}