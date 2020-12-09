import Interfaces.VehicleObserver;
import Vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SpeedView extends JFrame implements VehicleObserver
{
    private final int X;
    private final int Y;

    private JLabel label;

    private List<Vehicle> vehicles;

    private StringBuilder sb;

    public SpeedView(String title, List<Vehicle> vehicles, int width, int height)
    {
        X = width;
        Y = height;

        this.vehicles = vehicles;

        sb = new StringBuilder();

        initComponents(title);
    }

    private void initComponents(String title) {

        this.setTitle(title);
        this.getContentPane().setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        label = new JLabel();
        update();

        this.add(label);

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2 + 3 * this.getSize().width / 2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void update()
    {
        sb.setLength(0); // clears stringbuilder

        sb.append("<html>");

        for(Vehicle vehicle : vehicles)
        {
            double currentSpeed = (double)((int)(vehicle.getCurrentSpeed() * 10)) / 10;

            sb.append("<p style=\"text-align:right;\">" + vehicle.getModelName() + " : " + currentSpeed + "</p>");
        }

        sb.append("</html>");

        label.setText(sb.toString());
    }
}
