import Vehicles.VehicleFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import Vehicles.VehicleFactory;

public class VehicleAddController extends JPanel
{
    JButton addCar = new JButton("Add car");
    JButton removeCar = new JButton("Remove car");

    JComboBox<String> vehicles;

    public VehicleAddController(VehicleModel model, int width, int height)
    {
        String[] vehicleStrings = { "Random", "Volvo 240", "Saab 95", "Scania" };

        vehicles = new JComboBox<>(vehicleStrings);

        addCar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int index = vehicles.getSelectedIndex();

                Random rand = new Random();
                if (index == 0) index = rand.nextInt(vehicleStrings.length - 1) + 1;

                switch (index) {
                    case 1 -> model.addVehicle(VehicleFactory.createVolvo(0, 0));
                    case 2 -> model.addVehicle(VehicleFactory.createSaab(0, 0));
                    case 3 -> model.addVehicle(VehicleFactory.createScania(0, 0));
                }
            }
        });
    }


}
