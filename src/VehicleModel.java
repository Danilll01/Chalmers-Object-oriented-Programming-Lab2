import Vehicles.Cars.*;
import Vehicles.Trucks.Scania;
import Vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;

import Interfaces.VehicleObserver;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class VehicleModel {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer;

    // The frame that represents this instance View of the MVC pattern
    VehicleView frame;
    // A list of cars, modify if needed
    private List<Vehicle> vehicles;

    private List<VehicleObserver> observers;

    private int areaWidth;
    private int areaHeight;

    //methods:

    public VehicleModel(int areaWidth, int areaHeight)
    {
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;

        timer = new Timer(delay, new TimerListener());

        vehicles = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void startModel()
    {
        timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();


                int imageWidth = VehicleImages.getImageWidth(vehicle.getModelName());
                int imageHeight = VehicleImages.getImageHeight(vehicle.getModelName());
                if (outOfBounds(vehicle.getPos(), 0, areaWidth, 0, areaHeight, imageWidth, imageHeight)) {
                    bounceOffWall(vehicle);
                }
            }
            for(VehicleObserver observer : observers){
                observer.update();
            }
        }

        private void bounceOffWall(Vehicle vehicle) {
            vehicle.turnLeft();
            vehicle.turnLeft();
            vehicle.move();
            vehicle.stopEngine();
            vehicle.startEngine();
        }
    }

    public void addVehicle(Vehicle vehicle)
    {
        if(vehicles.size() >= 10) return;
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) { vehicles.remove(vehicle); }

    public void addObserver(VehicleObserver observer)
    {
        observers.add(observer);
    }

    public List<Vehicle> getVehicles()
    {
        return vehicles;
    }

    private boolean outOfBounds(Point2D topLeft, double left, double right, double top, double bottom, double width, double height)
    {
        boolean outXLeft = topLeft.getX() < left;
        boolean outXRight = topLeft.getX() + width > right;
        boolean outYTop = topLeft.getY() < top;
        boolean outYBottom = topLeft.getY() + height > bottom;

        return outXLeft || outXRight || outYTop || outYBottom;
    }

    public Vehicle findVehicle(Point point) {

        Vehicle vehicle;

        for(int i = vehicles.size() - 1; i >= 0; i--)
        {
            vehicle = vehicles.get(i);
            Point2D vehiclePoint = vehicle.getPos();
            int imageWidth = VehicleImages.getImageWidth(vehicle.getModelName());
            int imageHeight = VehicleImages.getImageHeight(vehicle.getModelName());
            if(!outOfBounds(point, vehiclePoint.getX(), vehiclePoint.getX() + imageWidth, vehiclePoint.getY(),
                            vehiclePoint.getY() + imageHeight, 0, 0))
            {
                return vehicle;
            }
        }

        return null;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : vehicles) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double)amount) / 100;
        for(Vehicle car : vehicles){
            car.brake(brake);
        }
    }

    void setTurbo(boolean state) {
        for(Vehicle car : vehicles){
            if (car instanceof Saab95) {
                ((Saab95) car).setTurbo(state);
            }
        }
    }

    void liftScaniaBed(int amount) {
        for(Vehicle car : vehicles){
            if (car instanceof Scania) {
                ((Scania) car).raisePlatform(amount);
            }
        }
    }

    void lowerScaniaBed(int amount) {
        for(Vehicle car : vehicles){
            if (car instanceof Scania) {
                ((Scania) car).lowerPlatform(amount);
            }
        }
    }

    void startAllVehicles() {
        for(Vehicle car : vehicles){
            car.startEngine();
        }
    }

    void stopAllVehicles() {
        for(Vehicle car : vehicles){
            car.stopEngine();
        }
    }

}
