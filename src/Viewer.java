import javax.imageio.ImageIO;
import javax.swing.JPanel;
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
            // File f = new File("../abstract_pro" + i + ".png");
            File f = new File("../abstract_pro4.png");
            img = ImageIO.read(f);
            System.out.println("File " + f.toString());
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e);
        }
        background = img;
    }

    /* @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // g.drawImage(background, 0, 0, null); // image full size
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null); // image scaled
    } */

    public synchronized void drawObject(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null); // image scaled
    }
}
