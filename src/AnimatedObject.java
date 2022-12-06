import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public abstract class AnimatedObject extends VisualObject implements Runnable {

    private static Image[] objectImages;
    private static Integer[][] statistics;

    private AnimatedObjectStatus status;
    private ObjectType objectType;
    private Integer sizeImage;

    private AnimationModel animationModel;

    // * Constructor

    public AnimatedObject(ObjectType objectType, CoordinatesDTO coordinates, AnimationModel animationModel,
            Integer sizeImage) {
        super(coordinates);

        this.objectType = objectType;
        this.status = AnimatedObjectStatus.RUNNING;
        this.animationModel = animationModel;
        this.sizeImage = sizeImage;

        statistics = new Integer[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                statistics[i][j] = 0;
            }
        }
    }

    // * Getters & Setters

    public AnimatedObjectStatus getStatus() {
        return status;
    }

    public void setStatus(AnimatedObjectStatus status) {
        this.status = status;
    }

    public static Image[] getObjectImages() {
        return objectImages;
    }

    public static void setObjectImages(Image[] objectImages) {
        AnimatedObject.objectImages = objectImages;
    }

    public AnimationModel getAnimationModel() {
        return animationModel;
    }

    public Integer getSizeImage() {
        return sizeImage;
    }

    // * Methods

    /*
     * private void addToStatistics() {
     * 
     * }
     */

    public void updateStatistics() {

        int i = 0;
        boolean stop = false;
        while (!stop && i < 4) {
            if (this.objectType.ordinal() == i) {
                int j = 0;
                boolean stop2 = false;
        while (!stop2 && i < 4) {
        }
            } else {
                i++;
            }
        }
    }

    /**
     * It reads in 4 images from the file system and stores them in an array
     */
    public static void loadObjectImages() {

        for (int i = 0; i < objectImages.length; i++) {
            BufferedImage img = null;
            try {
                // File f = new File("../abstract_pro" + i + ".png");
                File f = new File("../icon" + i + ".png");
                img = ImageIO.read(f);
                System.out.println("File " + f.toString());
            } catch (Exception e) {
                System.out.println("Cannot read file: " + e);
            }
            if (i == 0) {
                objectImages[ObjectType.ALIEN.ordinal()] = img;
            } else if (i == 1) {
                objectImages[ObjectType.SOLDIER.ordinal()] = img;
            } else if (i == 2) {
                objectImages[ObjectType.ZOMBIE.ordinal()] = img;
            } else if (i == 3) {
                objectImages[ObjectType.DOG.ordinal()] = img;
            }
        }
    }

    @Override
    public synchronized void drawObject(Graphics g) {
        g.drawImage(AnimatedObject.objectImages[this.objectType.ordinal()], coordinates.getX(), coordinates.getY(),
                sizeImage,
                sizeImage, null);
    }

    protected abstract void update() throws InterruptedException;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while (getAnimationModel().getStatus() != AnimationStatus.STOPPED) {
                update();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
