import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ButtonCalculateListener implements ActionListener {
    private GeometryBoard geometryBoard;

    public ButtonCalculateListener(GeometryBoard geometryBoard) {
        this.geometryBoard = geometryBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String radiusText = geometryBoard.getUserInputRadius().getText().trim();
        String heightText = geometryBoard.getUserInputHeight().getText().trim();

        if (radiusText.isEmpty() || !isNumeric(radiusText)) {
            showError("Raadius peab olema number.");
            return;
        }
        double radius = Double.parseDouble(radiusText);

        if (geometryBoard.getSelectedShape().equals("Silinder")) {
            if (heightText.isEmpty() || !isNumeric(heightText)) {
                showError("Kõrgus peab olema number.");
                return;
            }
            double height = Double.parseDouble(heightText);
            calculateCylinder(radius, height);
        } else if (geometryBoard.getSelectedShape().equals("Kera")) {
            calculateSphere(radius);
        } else {
            showError("Palun vali õige kujund.");
        }
    }

    private void calculateSphere(double radius) {
        Sphere sphere = new Sphere(radius);
        double sphereCircumference = sphere.calculateCircumference();
        double sphereSurfaceArea = sphere.calculateSurfaceArea();
        double sphereVolume = sphere.calculateVolume();

        String outputString = getOutputString(radius, 0.0, sphereCircumference, sphereSurfaceArea, sphereVolume);
        displayResults(outputString);
        writeResultsToFile(outputString);
    }

    private void calculateCylinder(double radius, double height) {
        Cylinder cylinder = new Cylinder(radius, height);
        double cylinderSurfaceArea = cylinder.calculateSurfaceArea();
        double cylinderSideSurfaceArea = cylinder.calculateSideSurfaceArea();
        double cylinderVolume = cylinder.calculateVolume();

        String outputString = getOutputString(radius, height, cylinderSurfaceArea, cylinderSideSurfaceArea, cylinderVolume);
        displayResults(outputString);
        writeResultsToFile(outputString);
    }

    private void displayResults(String outputString) {
        geometryBoard.getTextArea().setText(outputString);
    }

    private void writeResultsToFile(String outputString) {
        try {
            // File file = new File("JavaKujundid.txt");
            String relativeFilePath = "JavaKujundid.txt";
            try (FileWriter writer = new FileWriter(relativeFilePath, true)) {
                writer.write(outputString + "\n\n");
            }

            // Lisa faili nimi ja asukoht ja kuva textAreas
            // String filePathMessage = "Tulemused on salvestatud faili: " + file.getAbsolutePath();
            String filePathMessage = "Tulemused on salvestatud faili: " + relativeFilePath;
            geometryBoard.getTextArea().append("\n" + filePathMessage);
        } catch (IOException ex) {
            showError("Faili kirjutamine ebaõnnestus!");
        }
    }

    private String getOutputString(double radius, double height, double surfaceMetric1, double surfaceMetric2, double volume) {
        String shape = geometryBoard.getSelectedShape();
        StringBuilder outputString = new StringBuilder();
        outputString.append(String.format("Valitud kujund: %s%nVastused:%n", shape));
        outputString.append("Raadius: ").append(radius).append("\n");
        if (height > 0) {
            outputString.append("Kõrgus: ").append(height).append("\n");
        }

        if (shape.equals("Silinder")) {
            outputString.append(String.format("Kogupindala: %f%n", surfaceMetric1));
            outputString.append(String.format("Külgpindala: %f%n", surfaceMetric2));
        } else if (shape.equals("Kera")) {
            outputString.append(String.format("Ümbermõõt: %f%n", surfaceMetric1));
            outputString.append(String.format("Pindala: %f%n", surfaceMetric2));
        }

        outputString.append(String.format("Ruumala: %f%n", volume));
        return outputString.toString();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(geometryBoard, message, "Sisestuse viga", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
