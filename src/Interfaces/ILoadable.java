package Interfaces;

import Vehicles.Trucks.CarTransport;
import java.awt.geom.Point2D;

/**
 * Defines an object capable of being loaded onto another object.
 */
public interface ILoadable {

    /**
     * To be used as move method if the object is loaded. Provides additional/other functionality.
     */
    void loadedMove();

    /**
     * Loads onto object given as argument.
     * @param transport object to be loaded onto
     */
    void load(CarTransport transport);

    /**
     * Overload of load method, in case the object onto which the this will be loaded can't move/is not a car transport.
     * Takes a position instead of a car transport, where the object will be located until it is unloaded.
     * @param loadPos position where object will be loaded
     */
    void load(Point2D.Double loadPos);

    /**
     * Method to be called when a car gets unloaded from a transporter.
     */
    void unload();

}
