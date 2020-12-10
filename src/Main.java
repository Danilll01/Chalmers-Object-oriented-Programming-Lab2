import Vehicles.VehicleFactory;

import java.awt.image.ImageObserver;

public class Main
{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 910;

    public static void main(String[] args)
    {

        VehicleModel model = new VehicleModel(800, 560);

        model.addVehicle(VehicleFactory.createVolvo(0, 0));
        model.addVehicle(VehicleFactory.createSaab(0, 100));
        model.addVehicle(VehicleFactory.createScania(0, 200));
        model.addVehicle(VehicleFactory.createCarTransport(0, 300));

        VehicleView view = new VehicleView("Vehicle simulator",  model.getVehicles(), WIDTH, HEIGHT);
        SpeedView speedView = new SpeedView("Speed view", model.getVehicles(), WIDTH/4, HEIGHT/4);

        VehicleController controller = new VehicleController(model, WIDTH,230);
        view.addController(controller);

        VehicleAddController addController = new VehicleAddController(model, WIDTH, 100);
        view.addController(addController);

        model.addObserver(view);
        model.addObserver(speedView);


        model.startModel();

    }
}
