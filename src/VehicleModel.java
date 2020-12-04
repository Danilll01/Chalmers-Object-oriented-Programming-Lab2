import Vehicles.Cars.*;
import Vehicles.Trucks.Scania;
import Vehicles.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

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
            for (Vehicle car : vehicles) {
                car.move();
                if (outOfBounds(car)) {
                    bounceOffWall(car);
                }
            }
            for(VehicleObserver observer : observers){
                observer.update();
            }
        }

        private void bounceOffWall(Vehicle car) {
            car.turnLeft();
            car.turnLeft();
            car.move();
            car.stopEngine();
            car.startEngine();
        }
    }

    public void addVehicle(Vehicle vehicle)
    {
        vehicles.add(vehicle);
    }

    public void addObserver(VehicleObserver observer)
    {
        observers.add(observer);
    }

    public List<Vehicle> getVehicles()
    {
        return vehicles;
    }

    private boolean outOfBounds(Vehicle car)
    {
        Point2D.Double pos = car.getPos();
        boolean outXLeft = pos.getX() < 0;
        boolean outXRight = pos.getX() + VehicleImages.getImageWidth(car.getModelName()) > areaWidth;
        boolean outYTop = pos.getY() < 0;
        boolean outYBottom = pos.getY() + VehicleImages.getImageHeight(car.getModelName()) > areaHeight - 240;

        return outXLeft || outXRight || outYTop || outYBottom;
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
