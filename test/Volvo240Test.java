import Vehicles.Cars.Volvo240;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class Volvo240Test {

    Volvo240 volvo;

    @Before
    public void before(){
        volvo = new Volvo240();
    }

    @Test
    public void createSaabWithParams() {
        Volvo240 volvo2 = new Volvo240(Color.BLACK, "Vehicles.Cars.Volvo240", 4, 100, 0, 0);
    }

    @Test
    public void doors(){
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    public void enginePower() {
        assertEquals(100, volvo.getEnginePower(), 0.001);
    }

    @Test
    public void gas(){
        double resultSpeed = volvo.getCurrentSpeed() + getSpeedFactor() * 0.5;

        volvo.gas(0.5);

        assertEquals(resultSpeed, volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void brake(){
        volvo.brake(0.5);

        assertEquals(0, volvo.getCurrentSpeed(), 0.001);
    }

    private double getSpeedFactor() {
        return Volvo240.getTrimFactor() * 0.01 * volvo.getEnginePower();
    }

    @Test
    public void moveX(){
        volvo.gas(0.5);
        double firstX = volvo.getPos().getX();
        volvo.move();

        double result = firstX + volvo.getCurrentSpeed();

        assertEquals(result, volvo.getPos().getX(), 0.001);
    }

    @Test
    public void moveY(){
        volvo.gas(0.5);
        double firstY = volvo.getPos().getY();
        volvo.turnLeft();
        volvo.move();

        double result = firstY + volvo.getCurrentSpeed();

        assertEquals(result, volvo.getPos().getY(), 0.001);
    }


}
