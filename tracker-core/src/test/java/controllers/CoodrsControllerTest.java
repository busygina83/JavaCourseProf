package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import services.GpsService;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CoodrsControllerTest {

    @Mock
    GpsService gpsService;

    @Mock
    Model model;

    @InjectMocks
    CoodrsController coodrsController = new CoodrsController();

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this); //without this you will get NPE
    }

    @Test
    public void getLocation() throws Exception {
        String json = "{\"duration\":0,\"autoId\":\"123\",\"speedUp\":0,\"azimuth\":282.869336251209,\"lon\":68.24685853800247,\"time\":1632334381569,\"azimuthDelta\":0,\"lat\":-58.537113010188605,\"speed\":20.074085676751817}\"";
        when (gpsService.queue.take()).thenReturn(json);
        String result = coodrsController.getLocation("name", model);
        Assertions.assertEquals("postlocation", result);
    }

    @Test
    public void getCoords() throws Exception {
        String json = "{\"duration\":0,\"autoId\":\"123\",\"speedUp\":0,\"azimuth\":282.869336251209,\"lon\":68.24685853800247,\"time\":1632334381569,\"azimuthDelta\":0,\"lat\":-58.537113010188605,\"speed\":20.074085676751817}\"";
        when (gpsService.queue.take()).thenReturn(json);
        String result = coodrsController.getCoords("name", model);
        Assertions.assertEquals("postcoords", result);
    }
}