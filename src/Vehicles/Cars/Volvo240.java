package Vehicles.Cars;

import Vehicles.Cars.Car;

import java.awt.*;

/**
 * Implemented model of the Car superclass. Implements a trim feature.
 */
public class Volvo240 extends Car {

    /**
     * Modifies the rate at which the car accelerates/decelerates.
     */
    private final static double trimFactor = 1.25;

    /**
     * Sets this cars color to black, model name to "Volvo 240", number of doors to 4, engine power to 100, stops the engine and sets start position to 0,0.
     */
    public Volvo240(){
        super(Color.BLACK, "Volvo240", 4, 100, 0, 0);
    }

    /**
     * Passes constructor parameters to super.
     * @param color color of vehicle
     * @param modelName model name of vehicle
     * @param nDoors nr of doors on the vehicle
     * @param enginePower engine power of vehicle
     * @param posX position in x-axis
     * @param posY position in y-axis
     */
    public Volvo240(Color color, String modelName, int nDoors, double enginePower, double posX, double posY){
        super(color, modelName, nDoors, enginePower, posX, posY);
    }

    /**
     * Returns the car trim factor of the car.
     * @return volvo's trim factor
     */
    public static double getTrimFactor() {
        return trimFactor;
    }

    /**
     * Returns the factor by which the speed should be modified when it accelerates/decelerates. Affected by the car's
     * trim factor.
     * @return factor to modify currentSpeed with during acceleration/deceleration
     */
    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}
