package Vehicles;

import java.awt.*;
import java.awt.geom.Point2D;
import Interfaces.IMovable;

/**
 * Object capable of moving with attributes and functionality that a real life vehicle would be expected to have.
 */
public abstract class Vehicle implements IMovable
{
    /**
     * Number of doors on the vehicle.
     */
    private int nrDoors;

    /**
     * Color of the vehicle.
     */
    private Color color;

    /**
     * Decides the max speed of the vehicle as well as its rate of acceleration.
     */
    private double enginePower;

    /**
     * Current total velocity of the vehicle.
     */
    private double currentSpeed;

    /**
     * Name of the vehicle's model.
     */
    private String modelName;

    /**
     * The vehicles position described with a Point2D.Double object.
     */
    private final Point2D.Double position;

    /**
     * The direction of the vehicles velocity in radians
     */
    private double direction;

    /**
     * If the vehicle can be moved.
     */
    protected boolean movable;

    /**
     * Constructor which sets attributes to given values.
     * @param color color of vehicle
     * @param modelName model name of vehicle
     * @param nDoors nr of doors on the vehicle
     * @param enginePower engine power of vehicle
     * @param posX position in x-axis
     * @param posY position in y-axis
     */
    public Vehicle(Color color, String modelName, int nDoors, double enginePower, double posX, double posY) {
        this.color = color;
        this.modelName = modelName;
        this.nrDoors = nDoors;
        this.enginePower = enginePower;
        this.position = new Point2D.Double(posX, posY);
        stopEngine();
        movable = true;
    }

    /**
     * Sets position
     * @param x position in x
     * @param y position in y
     */
    protected void setPosition(double x, double y){
        position.setLocation(x, y);
    }

    /**
     * Sets Current speed
     * @param speed speed
     */
    protected void setCurrentSpeed(double speed){
        currentSpeed = speed;
    }

    /**
     * Sets direction
     * @param angle direction in angles
     */
    protected void setDirection(double angle){
        direction = angle;
    }

    /**
     * Returns the number of doors on the vehicle.
     * @return the number of doors on the vehicle
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * Returns the engine power of the vehicle.
     * @return the engine power of the vehicle
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * Returns the current total velocity of the vehicle.
     * @return current velocity of the vehicle
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }


    /**
     * Sets the currentSpeed to 0.1. Used to change the currentSpeed from 0.
     */
    public void startEngine(){
        if(movable) currentSpeed = 0.1;
    }

    /**
     * Sets the current speed of the vehicle to 0. Makes the vehicle stop moving.
     */
    public void stopEngine(){
        if(movable) currentSpeed = 0;
    }

    /**
     * Returns the position of the vehicle.
     * @return a Point with x and y coordinates
     */
    public Point2D.Double getPos() {
        return position;
    }

    /**
     * Returns the current direction of the vehicle's velocity in radians.
     * @return the vehicle's velocity's direction in radians
     */
    public double getDirection() {
        return direction;
    }

    /**
     * Returns the factor by which the speed should be modified when it accelerates/decelerates.
     * @return factor to modify currentSpeed with during acceleration/deceleration
     */
    protected abstract double speedFactor();

    /**
     * Increases the vehicle's total velocity. Velocity cannot be increased beyond enginePower.
     * @param amount amount to increase the vehicle's velocity by
     */
    private void incrementSpeed(double amount){
        if(movable) currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    }

    /**
     * Decreases the vehicle's total velocity. Velocity cannot be decreased to below zero.
     * @param amount amount to decrease the vehicle's velocity by
     */
    private void decrementSpeed(double amount) {
        if(movable) currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    /**
     * Updates the vehicle's position in the 2D plane according to it's currentSpeed and direction.
     */
    public void move(){
        if(!movable) return;
        double x = getCurrentSpeed() * Math.cos(direction);
        double y = getCurrentSpeed() * Math.sin(direction);
        position.setLocation(position.getX() + x, position.getY() + y);
    }

    /**
     * Makes the vehicle turn 90 degrees to the left. Increases the vehicle's direction by PI/2 radians.
     */
    public void turnLeft(){
        if(movable) direction += Math.PI / 2;
    }

    /**
     * Makes the vehicle turn 90 degrees to the right. Decreases the vehicle's direction by PI/2 radians.
     */
    public void turnRight(){
        if(movable) direction -= Math.PI / 2;
    }

    /**
     * Makes the vehicle accelerate, increases its current speed. Only accepts
     * @param amount amount to increase the vehicle's speed by (between 0 and 1)
     * @throws IllegalArgumentException if amount is not between 0 and 1
     */
    public void gas(double amount){
        if(amount > 1 || amount < 0) throw new IllegalArgumentException("gas amount has to be between 0 and 1");

        incrementSpeed(amount);
    }

    /**
     * Makes the vehicle decelerate, decreases its current speed.
     * @param amount amount to decrease the vehicle's speed by (between 0 and 1)
     * @throws IllegalArgumentException if amount is not between 0 and 1
     */
    public void brake(double amount){
        if(amount > 1 || amount < 0) throw new IllegalArgumentException("break amount has to be between 0 and 1");

        decrementSpeed(amount);
    }
}
