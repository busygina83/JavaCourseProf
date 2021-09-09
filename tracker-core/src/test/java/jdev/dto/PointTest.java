package jdev.dto;


import jdev.tracker.EmulatorGPS;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by jdev on 06.03.2017.
 */
public class PointTest {

    @Test
    public void toJson() {
        Point point = new Point();
        EmulatorGPS gps = new EmulatorGPS();
        gps.setBegin(point,"123");
//        gps.setEnd(point, "123");

    }
}