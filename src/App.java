import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeometryBoard geometryBoard = new GeometryBoard();
            Controller controller = new Controller(geometryBoard);

            JFrame frame = new JFrame("Geometry Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(geometryBoard);
            frame.pack();
            frame.setLocationRelativeTo(null); 
            frame.setVisible(true);
        });
    }
}
