import java.awt.Graphics;

public abstract class VisualObject {

    protected CoordinatesDTO coordinates;

    // * Constructor

    public VisualObject(CoordinatesDTO coordinates) {
        this.coordinates = coordinates;
    }

    // * Getters & Setters

    public CoordinatesDTO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDTO coordinates) {
        this.coordinates = coordinates;
    }

    // * Methods

    public synchronized void drawObject(Graphics g) {}
}
