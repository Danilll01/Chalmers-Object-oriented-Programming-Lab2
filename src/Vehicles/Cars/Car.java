package Vehicles.Cars;

import Interfaces.ILoadable;
import Vehicles.Trucks.CarTransport;
import Vehicles.Vehicle;


import java.awt.*;
import java.awt.geom.Point2D;


/**
 * Collection of attributes and functionality of a car, with a position in a 2D space and a direction (angle).
 *
 * Attributes consist of amount of doors, color, engine power, current speed, model name, x position, y, position and direction angle in radians.
 */
public abstract class Car extends Vehicle implements ILoadable
{
    /**
     * Transporter on which the car is loaded onto.
     */
    private CarTransport transport;

    /**
     * If the car is loaded on another object.
     */
    private boolean isLoaded;

    /**
     * Passes constructor parameters to super.
     * @param color color of vehicle
     * @param modelName model name of vehicle
     * @param nDoors nr of doors on the vehicle
     * @param enginePower engine power of vehicle
     * @param posX position in x-axis
     * @param posY position in y-axis
     */
    public Car(Color color, String modelName, int nDoors, double enginePower, double posX, double posY) {
        super(color, modelName, nDoors, enginePower, posX, posY);
        isLoaded = false;
    }

    /**
     * Returns the factor by which the speed should be modified when it accelerates/decelerates.
     * @return factor to modify currentSpeed with during acceleration/deceleration
     */
    protected abstract double speedFactor();

    /**
     * Sets the position, direction and currentSpeed of the car to the transporters position, direction and currentSpeed.
     */
    public void loadedMove(){
        Point2D transportPosition = transport.getPos();
        setPosition(transportPosition.getX(), transportPosition.getY());

        setCurrentSpeed(transport.getCurrentSpeed());
        setDirection(transport.getDirection());
    }

    /**
     * Returns isLoaded state
     * @return if car is loaded onto something
     */
    public boolean isLoaded(){
        return isLoaded;
    }

    /**
     * Method that is called when the car gets loaded onto a car transporter. Sets relevant attributes to specified values
     * to keep track of the transporter's speed and other attributes in the future.
     * @param transport car transporter that the car is loaded onto
     */
    public void load(CarTransport transport){
        this.transport = transport;

        Point2D.Double currentPos = transport.getPos();

        setPosition(currentPos.getX(), currentPos.getY());
        setCurrentSpeed(0);
        setDirection(transport.getDirection());

        isLoaded = true;

        movable = false;
    }

    /**
     * Overload of load method, in case the object onto which the car will be loaded can't move/is not a car transport.
     * Takes a position instead of a car transport, where the car  will be located until it is unloaded.
     * @param loadPos position where car will be loaded
     */
    public void load(Point2D.Double loadPos){
        setPosition(loadPos.getX(), loadPos.getY());
        setCurrentSpeed(0);
        movable = false;
        isLoaded = true;
    }

    /**
     * Method to be called when the car gets unloaded from a car transporter.
     */
    public void unload(){
        this.transport = null;

        Point2D.Double currentPos = getPos();

        setPosition(currentPos.getX() + 5, currentPos.getY() + 5);

        movable = true;
        isLoaded = false;
    }

}
