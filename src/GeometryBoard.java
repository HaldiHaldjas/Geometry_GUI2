import java.awt.*;
import javax.swing.*;

public class GeometryBoard extends JPanel {
    private JComboBox<String> cmbShapes;
    private JButton btnCalculate;
    private JTextArea textArea;
    private JLabel radiusLabel, heightLabel, shapeLabel, lblFilePath;
    private JTextField userInputRadius, userInputHeight;
    private String selectedShape;

    public GeometryBoard() {
        setPreferredSize(new Dimension(700, 500));
        setLayout(new BorderLayout());
        createComponents();
        selectedShape = "Kera"; 
    }

    private void createComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel resultPanel = new JPanel(new BorderLayout()); 

        // Taustavärvid
        topPanel.setBackground(new Color(196, 232, 030));
        inputPanel.setBackground(new Color(156, 202, 035));
        resultPanel.setBackground(new Color(206, 203, 013));

        // Äärised
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));

        // Komponendid
        topPanel.add(new JLabel("See rakendus arvutab kera ja silindri ümbermõõte, pindala ja ruumala."));
        shapeLabel = new JLabel("Vali kujund");
        cmbShapes = new JComboBox<>(new String[]{"Kera", "Silinder"});
        cmbShapes.setSelectedItem("Kera"); 
        radiusLabel = new JLabel("Raadius");
        heightLabel = new JLabel("Kõrgus");
        userInputRadius = new JTextField(10);
        userInputHeight = new JTextField(10);
        lblFilePath = new JLabel("Faili asukoht"); 
        btnCalculate = new JButton("Arvuta");
        textArea = new JTextArea(10,20);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
        textArea.setEditable(false);
        resultPanel.add(new JScrollPane(textArea), BorderLayout.CENTER); 
        resultPanel.add(lblFilePath, BorderLayout.SOUTH); 

        inputPanel.add(shapeLabel);
        inputPanel.add(cmbShapes);
        inputPanel.add(radiusLabel);
        inputPanel.add(userInputRadius);
        inputPanel.add(heightLabel);
        inputPanel.add(userInputHeight);
        inputPanel.add(btnCalculate);

        // sisestuskasti peitmine
        userInputHeight.setVisible(false);
        heightLabel.setVisible(false);

        cmbShapes.addActionListener(e -> {
            selectedShape = (String) cmbShapes.getSelectedItem();
            System.out.println("Valitud kujund: " + selectedShape); // kontrolliks

            // sisestuskasti nähtavus sõltub valitud kujundist
            if (selectedShape.equals("Kera")) {
                userInputHeight.setVisible(false);
                heightLabel.setVisible(false);
                userInputRadius.setText("");
            } else if (selectedShape.equals("Silinder")) {
                userInputRadius.setText("");
                userInputHeight.setVisible(true);
                heightLabel.setVisible(true);
            }

            // värskendab akent
            revalidate();
            repaint();

        });

        // Attach the button listener
        btnCalculate.addActionListener(new ButtonCalculateListener(this));

        // Lisab paneelid 
        add(topPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH); 
    }

    // Getterid

    public String getSelectedShape() {
        return selectedShape;
    }

    public JButton getBtnCalculate() {
        return btnCalculate;
    }

    public JComboBox<String> getCmbShapes() {
        return cmbShapes;
    }

    public JTextField getUserInputRadius() {
        return userInputRadius;
    }

    public JTextField getUserInputHeight() {
        return userInputHeight;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

}
