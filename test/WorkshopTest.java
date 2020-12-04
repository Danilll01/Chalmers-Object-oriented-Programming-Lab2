import Vehicles.Cars.*;
import Workshops.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WorkshopTest
{
    Workshop<Car> carWorkshop;
    Workshop<Volvo240> volvoWorkshop;

    Volvo240 volvo;
    Saab95 saab;
    Car car;

    @Before
    public void before(){
        carWorkshop = new Workshop<>(2, 0, 0);
        volvoWorkshop = new Workshop<>(2, 0, 0);

        volvo = new Volvo240();
        saab = new Saab95();
        car = new Volvo240();
    }

    @Test
    public void testLoad(){
        volvoWorkshop.loadCar(volvo);
        volvo.startEngine();
        for (int i = 0; i < 10; i++) {
            volvo.gas(1);
        }
        for (int i = 0; i < 10; i++) {
            volvo.move();
        }

        assertEquals(0, volvo.getPos().getX(), 0.0001);
    }

    @Test
    public void testMaxLoad(){
        carWorkshop.loadCar(volvo);
        carWorkshop.loadCar(saab);

        try{
            carWorkshop.loadCar(car);
            fail();
        }
        catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUnload(){
        carWorkshop.loadCar(car);
        carWorkshop.unloadCar(car);
        for (int i = 0; i < 10; i++) {
            car.gas(1);
        }
        for (int i = 0; i < 10; i++) {
            car.move();
        }

        assertTrue(car.getPos().getX() > 0.1);
    }

    @Test
    public void testRange(){
        for (int i = 0; i < 20; i++) {
            car.gas(1);
        }
        for (int i = 0; i < 10; i++) {
            car.move();
        }

        try {
            carWorkshop.loadCar(car);
            fail();
        }
        catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testEmptyUnload(){
        try {
            carWorkshop.unloadCar(car);
            fail();
        }
        catch(Exception e){
            assertTrue(true);
        }
    }
}
