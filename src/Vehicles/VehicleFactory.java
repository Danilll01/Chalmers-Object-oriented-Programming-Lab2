package Vehicles;

import Vehicles.Cars.*;
import Vehicles.Trucks.*;

import java.awt.*;

public class VehicleFactory {

    public static Vehicle createVolvo(double posX, double posY) {
        return new Volvo240(posX, posY);
    }

    public static Vehicle createVolvo(Color color, String modelName, int nDoors, double enginePower, double posX, double posY) {
        return new Volvo240(color, modelName, nDoors, enginePower, posX, posY);
    }

    public static Vehicle createSaab(double posX, double posY) {
        return new Saab95(posX, posY);
    }

    public static Vehicle createSaab(Color color, String modelName, int nDoors, double enginePower, double posX, double posY) {
        return new Saab95(color, modelName, nDoors, enginePower, posX, posY);
    }

    public static Vehicle createScania(double posX, double posY) {
        return new Scania(posX, posY);
    }

    public static Vehicle createScania(Color color, String modelName, int nDoors, double enginePower, double posX, double posY) {
        return new Scania(color, modelName, nDoors, enginePower, posX, posY);
    }

    public static Vehicle createCarTransport(double posX, double posY) {
        return new CarTransport(posX, posY);
    }

    public static Vehicle createCarTransport(Color color, String modelName, int nDoors, double enginePower, double posX, double posY) {
        return new CarTransport(color, modelName, nDoors, enginePower, posX, posY);
    }
}
