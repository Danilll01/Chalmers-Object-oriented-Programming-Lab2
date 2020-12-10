import javax.swing.*;
import java.util.EventListener;
import java.util.List;

public abstract class Controller extends JPanel
{
    public abstract List<EventListener> getListeners();
}
