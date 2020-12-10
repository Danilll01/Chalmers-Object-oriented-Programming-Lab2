import Vehicles.VehicleFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Random;
import Vehicles.VehicleFactory;


public class VehicleAddController extends Controller
{
    JButton addCar = new JButton("Add car");

    JComboBox<String> vehicles;

    RemovalListener listener;

    public VehicleAddController(VehicleModel model, int width, int height)
    {
        this.setPreferredSize(new Dimension(width, height));

        String[] vehicleStrings = { "Random", "Volvo 240", "Saab 95", "Scania", "CarTransport"};

        vehicles = new JComboBox<>(vehicleStrings);

        vehicles.setPreferredSize(new Dimension(width / 2 - 10, height - 10));

        addCar.addActionListener(e -> {
            int index = vehicles.getSelectedIndex();

            Random rand = new Random();
            if (index == 0) index = rand.nextInt(vehicleStrings.length - 1) + 1;

            switch (index) {
                case 1 -> model.addVehicle(VehicleFactory.createVolvo(0, 0));
                case 2 -> model.addVehicle(VehicleFactory.createSaab(0, 0));
                case 3 -> model.addVehicle(VehicleFactory.createScania(0, 0));
                case 4 -> model.addVehicle(VehicleFactory.createCarTransport(0, 0));
            }
        });

        addCar.setPreferredSize(new Dimension(width / 2 - 10, height - 10));

        this.add(vehicles);
        this.add(addCar);

        listener = new RemovalListener(model);
    }

    @Override
    public List<EventListener> getListeners() {
        List<EventListener> listeners = new ArrayList<>();
        listeners.add(listener);
        return listeners;
    }

    private class RemovalListener implements MouseListener
    {
        VehicleModel model;

        public RemovalListener(VehicleModel model)
        {
            this.model = model;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Point point = e.getPoint();
            model.removeVehicle(model.findVehicle(point));
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


}
