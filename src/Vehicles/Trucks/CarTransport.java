package Vehicles.Trucks;

import Vehicles.Cars.Car;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implements the idea of a Car transport that can transport cars on itself
 * Has a ramp that can be raised and lowered when transport is stationary, cars can be loaded and unloaded when the ramp is lowered
 * extends Vehicles.Trucks.Truck
 */

public class CarTransport extends Truck
{
    /**
     * Cars stored on the car transporter.
     */
    private final Deque<Car> storedCars;
    /**
     * True if the ramp of the car transporter is lowered
     */
    private boolean rampLowered;

    /**
     * Sets attributes to their default values.
     */
    public CarTransport(){
        super(Color.PINK, "Car transporter", 2, 220, 0, 0);

        storedCars = new ArrayDeque<>(3);
        rampLowered = false;
    }

    /**
     * Passes constructor parameters to super, creates a stack and set rampLowered to false.
     * @param color color of vehicle
     * @param modelName model name of vehicle
     * @param nDoors nr of doors on the vehicle
     * @param enginePower engine power of vehicle
     * @param posX position in x-axis
     * @param posY position in y-axis
     */
    public CarTransport(Color color, String modelName, int nDoors, double enginePower, double posX, double posY) {
        super(color, modelName, nDoors, enginePower, posX, posY);

        storedCars = new ArrayDeque<>(3);
        rampLowered = false;
    }

    /**
     * Overrides Vehicle class' move method. Now checks if the ramp is up before moving, and updates the position of every loaded car,
     * as the cannot move by themselves.
     */
    @Override
    public void move(){
        if(rampLowered || !movable) return;
        super.move();
        for(Car car : storedCars){
            car.loadedMove();
        }
    }

    /**
     * Raises the ramp
     */
    public void raiseRamp(){
        rampLowered = false;
    }

    /**
     * Lowers the ramp. Throws an error if the transport is moving, needs to be caught during implementation.
     * @throws IllegalStateException if transport is moving.
     */
    public void lowerRamp(){
        if(getCurrentSpeed() > 0.01) throw new IllegalStateException("Transport is moving");
        rampLowered = true;
    }

    /**
     * Loads given car onto the car transporter. Will not load if the transporter is full, or if the car is not within a
     * distance of 5 l.u. Creates an error if the transport is FULL, needs to be caught during implementation.
     * @param car car to be loaded
     * @throws IllegalArgumentException if transporter is full.
     * @throws IllegalArgumentException if car is too far away.
     */
    public void loadCar(Car car){
        if(storedCars.size() >= 3 || !rampLowered || car.getCurrentSpeed() > 0.01) throw new IllegalArgumentException("Transport is full");
        if (car.isLoaded()) throw new IllegalArgumentException("Car is already loaded");

        double distance = getPos().distance(car.getPos());

        if(distance > 5) throw new IllegalArgumentException("Too far away");

        car.load(this);
        storedCars.add(car);
    }

    /**
     * Unloads last car from transport unless transport is empty. Creates an error if the transport is EMPTY, needs to be caught during implementation..
     * @throws IllegalArgumentException if transporter is empty.
     */
    public void unloadCar(){
        if(storedCars.isEmpty() || !rampLowered) throw new IllegalArgumentException("Transport is empty");

        storedCars.pop().unload();
    }

    /**
     * Returns the factor by which the speed should be modified when it accelerates/decelerates.
     *
     * @return factor to modify currentSpeed with during acceleration/deceleration
     */
    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
