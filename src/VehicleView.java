import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import Interfaces.VehicleObserver;
import Vehicles.Vehicle;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class VehicleView extends JFrame implements VehicleObserver{
    private final int X;
    private final int Y;

    DrawPanel drawPanel;

    // Constructor
    public VehicleView(String framename, List<Vehicle> vehicles, int width, int height){
        X = width;
        Y = height;
        initComponents(framename, vehicles);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title, List<Vehicle> vehicles) {

        this.setTitle(title);
        this.getContentPane().setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        drawPanel = new DrawPanel(X, Y-240, vehicles);

        this.add(drawPanel);

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2 - 5 * this.getSize().width / 8, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addController(JPanel controller)
    {
        this.add(controller);
        this.pack();
    }

    @Override
    public void update() {
        drawPanel.repaint();
    }
}