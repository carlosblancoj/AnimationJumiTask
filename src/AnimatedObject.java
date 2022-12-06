import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public abstract class AnimatedObject extends VisualObject implements Runnable {

    private static Image[] objectImages;
    protected static Integer[][] statistics;

    private AnimatedObjectStatus status;
    private ObjectType objectType;
    private Integer sizeImage;

    private AnimationModel animationModel;

    // * Constructor

    public AnimatedObject(ObjectType objectType, CoordinatesDTO coordinates, AnimationModel animationModel,
            Integer sizeImage) {
        super(coordinates);

        this.objectType = objectType;
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

    public synchronized AnimatedObjectStatus getStatus() {
        return status;
    }

    /**
     * "This function updates the statistics and then sets the status."
     * 
     * The function is synchronized, so it's thread-safe
     * 
     * @param status The status of the object.
     */
    public synchronized void setStatus(AnimatedObjectStatus status) {
        updateStatistics(status);
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
    
    /**
     * This function adds one to the statistics array at the index of the object type and the status
     * 
     * @param status The status of the object.
     */
    public synchronized void addToStatics(AnimatedObjectStatus status) {
        statistics[objectType.ordinal()][status.ordinal()]++;
    }
    
    /**
     * Decrement the number of objects of a certain type that are in a certain state
     * 
     * @param status The status of the object.
     */
    public synchronized void removeFromStatics(AnimatedObjectStatus status) {
        statistics[objectType.ordinal()][status.ordinal()]--;
    }

    /**
     * If the status is not null, remove it from the statistics, then add the new status to the
     * statistics.
     * 
     * @param status The status of the object.
     */
    public synchronized void updateStatistics(AnimatedObjectStatus status) {
        AnimatedObjectStatus statusActual = getStatus();
        if (statusActual != null) {
            removeFromStatics(statusActual);
        }
        addToStatics(status);
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

    /**
     * It draws an image of the object at the coordinates of the object.
     * </code>
     * 
     * @param g Graphics object
     */
    @Override
    public synchronized void drawObject(Graphics g) {
        g.drawImage(AnimatedObject.objectImages[this.objectType.ordinal()], coordinates.getX(), coordinates.getY(),
                sizeImage,
                sizeImage, null);
    }

    /**
     * This function is called by the thread to update the game state.
     */
    protected abstract void update() throws InterruptedException;

    /**
     * The run() function is called when the thread is started. It will keep running until the
     * animation status is STOPPED
     */
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
