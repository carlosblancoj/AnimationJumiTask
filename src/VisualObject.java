import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

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

    // ! Synchronyzed? meterle la lógica aqui para q se dibuje
    public synchronized void drawObject(Graphics g) {
        //g.drawImage(avatar, coordinates.getX(), coordinates.getY(), 50, 50, null);
    }
}
