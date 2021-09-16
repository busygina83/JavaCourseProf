package jdev.dto;

import queues.GpsEmulator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by jdev on 06.03.2017.
 */
public class PointTest {

    @Test
    public void toJson() {
        Point point = new Point();
        GpsEmulator gps = new GpsEmulator();
        gps.setBegin(point,"123");
//        gps.setEnd(point, "123");

    }
}