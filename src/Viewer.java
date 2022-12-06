import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.awt.Canvas;

public class Viewer extends Canvas {

    private Image background;
    private BufferedImage img;

    public Viewer() {
        try {
            File f = new File("../abstract_pro4.png");
            img = ImageIO.read(f);
            System.out.println("File " + f.toString());
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e);
        }
        background = img;
    }

    /**
     * This function draws the background image to the screen
     * 
     * @param g The Graphics object to draw on.
     */
    public synchronized void drawObject(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null); // imatge escalada
    }
}
