import Vehicles.Cars.Car;
import Vehicles.Cars.Volvo240;
import Vehicles.Trucks.CarTransport;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CarTransportTest {

    CarTransport transport;

    Car car;

    @Before
    public void before(){
        transport = new CarTransport();
        transport = new CarTransport(Color.PINK, "Car transport", 2, 200, 0, 0);
        car = new Volvo240();
    }

    @Test
    public void loadCar() {
        transport.lowerRamp();
        transport.loadCar(car);

        for (int i = 0; i < 10; i++) {
            car.gas(1);
        }
        car.move();
        assertEquals(0, car.getPos().getX(), 0.0001);
    }

    @Test
    public void loaded(){
        transport.lowerRamp();
        transport.loadCar(car);
        transport.raiseRamp();
        transport.gas(1);
        transport.move();
        assertEquals(car.getPos().getX(), transport.getPos().getX(), 0.0001);
    }

    @Test
    public void unloadCar(){
        transport.lowerRamp();
        transport.loadCar(car);
        transport.raiseRamp();
        transport.gas(1);
        transport.move();
        transport.stopEngine();
        transport.lowerRamp();

        transport.unloadCar();
        transport.raiseRamp();

        for (int i = 0; i < 50; i++) {
            transport.gas(1);
        }

        transport.move();

        assertTrue(car.getPos().getX() < transport.getPos().getX());
    }

    @Test
    public void outOfRange(){
        transport.gas(1);
        transport.gas(1);
        for(int i = 0; i < 4; i++){
            transport.move();
        }

        transport.stopEngine();
        transport.lowerRamp();
        transport.loadCar(car);
        transport.raiseRamp();
        transport.gas(1);
        transport.move();
        assertEquals(0, car.getPos().getX(), 0.0001);
    }

    @Test
    public void transportFilled(){
        transport.lowerRamp();
        transport.loadCar(new Volvo240());
        transport.loadCar(new Volvo240());
        transport.loadCar(new Volvo240());

        try {
            transport.loadCar(car);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        transport.raiseRamp();
        transport.gas(1);
        transport.move();

        assertEquals(0, car.getPos().getX(), 0.0);
    }

}
