import Vehicles.VehicleFactory;

public class Main
{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    public static void main(String[] args)
    {

        VehicleModel model = new VehicleModel(800, 600);

        model.addVehicle(VehicleFactory.createVolvo(0, 0));
        model.addVehicle(VehicleFactory.createSaab(0, 100));
        model.addVehicle(VehicleFactory.createScania(0, 200));

        VehicleView view = new VehicleView("Vehicle simulator",  model.getVehicles(), WIDTH, HEIGHT);

        VehicleController controller = new VehicleController(model, WIDTH,230);
        view.addController(controller);

        model.addObserver(view);


        model.startModel();

    }
}
