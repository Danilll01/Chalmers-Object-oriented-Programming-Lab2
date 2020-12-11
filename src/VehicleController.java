import javax.swing.*;
import java.awt.*;
import java.util.EventListener;
import java.util.List;
import java.util.ArrayList;

public class VehicleController extends Controller {

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
        addGasSpinner();

        addControlPanel(width, height);

        addStartButton(width, height);

        addStopButton(width, height);

        setEventListeners(model);
    }

    private void setEventListeners(VehicleModel model) {
        gasButton.addActionListener(e -> model.gas(gasAmount));

        brakeButton.addActionListener(e -> model.brake(gasAmount));

        turboOnButton.addActionListener(e -> model.setTurbo(true));

        turboOffButton.addActionListener(e -> model.setTurbo(false));

        liftBedButton.addActionListener(e -> model.liftScaniaBed(10));
        lowerBedButton.addActionListener(e -> model.lowerScaniaBed(10));

        stopButton.addActionListener(e -> model.stopAllVehicles());
        startButton.addActionListener(e -> model.startAllVehicles());
    }

    private void addStopButton(int width, int height) {
        stopButton.setBackground(Color.RED);
        stopButton.setForeground(Color.BLACK);
        stopButton.setPreferredSize(new Dimension((width/5)-15, height));
        this.add(stopButton);
    }

    private void addStartButton(int width, int height) {
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.GREEN);
        startButton.setPreferredSize(new Dimension((width/5)-15, height));
        this.add(startButton);
    }

    private void addControlPanel(int width, int height) {
        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((width /2)+4, height));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);
    }

    private void addGasSpinner() {
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
    }

    @Override
    public List<EventListener> getListeners() {
        return new ArrayList<>();
    }
}
