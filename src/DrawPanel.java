import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import Vehicles.Vehicle;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    // To keep track of a singel cars position
    Point volvoPoint = new Point();

    List<Vehicle> vehicles;

    // TODO: Make this general for all cars
    void moveit(int x, int y){
        volvoPoint.x = x;
        volvoPoint.y = y;
    }

    public void setCars(List<Vehicle> vehicles)
    {
        this.vehicles = vehicles;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        vehicles = new ArrayList<>();
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Vehicle vehicle : vehicles) {
            try {
                BufferedImage image = VehicleImages.getImage(vehicle.getModelName());
                Point2D.Double pos = vehicle.getPos();
                g.drawImage(image, (int) pos.getX(), (int) pos.getY(), null); // see javadoc for more info on the parameters
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    }
}
