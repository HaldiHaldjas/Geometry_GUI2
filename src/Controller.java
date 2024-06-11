public class Controller {
    private GeometryBoard geometryBoard;
    private ButtonCalculateListener buttonCalculateListener;

    public Controller(GeometryBoard geometryBoard) {
        this.geometryBoard = geometryBoard;
        this.buttonCalculateListener = new ButtonCalculateListener(geometryBoard);

        // Attach the button listener to the "Calculate" button
        geometryBoard.getBtnCalculate().addActionListener(buttonCalculateListener);
    }

    // failimajandus
}
