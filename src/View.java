import java.awt.*;
import javax.swing.*;

public class View extends JFrame {
    private GeometryBoard geometryBoard;
    public View(){
        // super("Geomeetriliste kujundite arvutused");
        geometryBoard = new GeometryBoard();

        JPanel container = new JPanel(new BorderLayout());
        container.add(geometryBoard, BorderLayout.CENTER);
        this.add(container); // JFrame peale
    }
}
