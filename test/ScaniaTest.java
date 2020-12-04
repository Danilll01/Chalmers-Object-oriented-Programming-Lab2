import Vehicles.Trucks.Scania;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.awt.*;


public class ScaniaTest {
    Scania scania;

    @Before
    public void before(){
        scania = new Scania();
        scania = new Scania(Color.BLUE, "Vehicles.Trucks.Scania-R", 2, 200, 0, 0);
    }

    @Test
    public void raise(){
        scania.raisePlatform(85);
        assertEquals(70, scania.getplatformAngle());
    }

    @Test
    public void raiseMove(){
        scania.gas(1);
        for (int i = 0; i < 10; i++) {
            scania.move();
        }
        scania.raisePlatform(85);
        assertEquals(0, scania.getplatformAngle());
    }
    @Test
    public void lower() {
        scania.lowerPlatform(85);
        assertEquals(0, scania.getplatformAngle());
    }
}

