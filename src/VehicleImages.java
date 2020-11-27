import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class VehicleImages
{
    private static HashMap<String, BufferedImage> images;

    public static BufferedImage getImage(String modelName) throws NullPointerException
    {
        if(images == null){
            images = new HashMap<>();
            addImage(modelName);
        }
        else if(!images.containsKey(modelName)){
            addImage(modelName);
        }

        return images.get(modelName);
    }

    public static int getImageWidth(String modelName)
    {
        try {
            return getImage(modelName).getWidth();
        } catch (Exception ex)
        {
            return 0;
        }
    }

    public static int getImageHeight(String modelName)
    {
        try {
            return getImage(modelName).getHeight();
        } catch (Exception ex ) {
            return 0;
        }
    }

    private static void addImage(String modelName) {
        try {
            images.put(modelName, readImage(modelName));
        } catch (Exception ignored) {}
    }


    private static BufferedImage readImage(String modelName) throws java.io.IOException
    {
        return ImageIO.read(VehicleImages.class.getResourceAsStream("pics/" + modelName + ".jpg"));
    }
}
