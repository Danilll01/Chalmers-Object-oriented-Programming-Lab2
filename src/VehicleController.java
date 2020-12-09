import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;

public class VehicleController extends JPanel {

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner;
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JPanel controlPanel = new JPanel();

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Scania Lower Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    public VehicleController(VehicleModel model, int width, int height)
    {
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner)e.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((width/2)+4, height));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension((width/5)-15, height));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension((width/5)-15, height));
        this.add(stopButton);


        gasButton.addActionListener(e -> model.gas(gasAmount));

        brakeButton.addActionListener(e -> model.brake(gasAmount));

        turboOnButton.addActionListener(e -> model.setTurbo(true));

        turboOffButton.addActionListener(e -> model.setTurbo(false));

        liftBedButton.addActionListener(e -> model.liftScaniaBed(10));
        lowerBedButton.addActionListener(e -> model.lowerScaniaBed(10));

        stopButton.addActionListener(e -> model.stopAllVehicles());
        startButton.addActionListener(e -> model.startAllVehicles());
    }
}
