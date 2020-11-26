package Workshops;

import Vehicles.Cars.*;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

/**
 * Implements the idea of a car repairshop, can only load cars of type specified when created.
 * @param <T> car type
 */
public class Workshop<T extends Car>
{
    /**
     * Max amount of cars that can be stored in workshop.
     */
    private final int maxCars;

    /**
     * Car currently stored in workshop.
     */
    private List<T> cars;

    /**
     * Where the workshop is located.
     */
    private Point2D.Double position;

    /**
     * Creates new workshop with given attributes.
     * @param maxCars max amount of cars allowed in workshop
     * @param posX x-axis position of workshop
     * @param posY y-axis position of workshop
     */
    public Workshop(int maxCars, double posX, double posY){
        this.maxCars = maxCars;
        cars = new ArrayList<>(maxCars);
        position = new Point2D.Double(posX, posY);
    }

    /**
     * Loads given car into the workshop. Throws exception if the workshop is full, car attempted to be loaded is moving to fast or too far away from workshop, needs to be caught during implementation
     * @param car car to be loaded.
     * @throws IllegalArgumentException if workshop is full
     * @throws IllegalArgumentException if car is moving too fast
     * @throws IllegalArgumentException if car is too far away
     */
    public void loadCar(T car){
        if (cars.size() >= maxCars) throw new IllegalArgumentException("Workshop full");
        if (car.getCurrentSpeed() > 0.01) throw new IllegalArgumentException("Car speed too high");

        double distance = position.distance(car.getPos());

        if(distance > 5) throw new IllegalArgumentException("Too far away");

        car.load(position);
        cars.add(car);
    }

    /**
     * Unloads/removes specified car from the workshop. Throws an exception if the workshop is empty, needs to be caught in implementation.
     * @param car car to be unloaded
     * @throws IllegalArgumentException if workshop is empty
     */
    public void unloadCar(T car){
        if(cars.contains(car)){
            cars.remove(car);
            car.unload();
        }
        else{
            throw new IllegalArgumentException("No such car in list");
        }
    }
}
