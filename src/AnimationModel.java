import java.util.ArrayList;
import java.util.Random;
import java.awt.Image;

public class AnimationModel implements Runnable {

    private AnimationStatus status;
    private ArrayList<AnimatedObject> objectsList;

    private AnimationController animationController;

    private Random random;

    private Integer minObjects;
    private Integer maxObjects;

    // * Constructor

    public AnimationModel(Integer minObjects, Integer maxObjects) {
        this.status = AnimationStatus.STOPPED;
        this.minObjects = minObjects;
        this.maxObjects = maxObjects;

        this.objectsList = new ArrayList<>();

        this.random = new Random();

        AnimatedObject.setObjectImages(new Image[4]);
        AnimatedObject.loadObjectImages();
    }

    // * Getters & Setters

    /**
     * If the animation is paused, wait until it is resumed
     * 
     * @return The status of the animation.
     */
    public synchronized AnimationStatus getStatus() {
        if (status == AnimationStatus.PAUSED) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return status;
    }

    public synchronized void setStatus(AnimationStatus status) {
        this.status = status;
    }

    public ArrayList<AnimatedObject> getObjectsList() {
        return objectsList;
    }

    public AnimationController getAnimationController() {
        return animationController;
    }

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
    }

    // * Methods

    /**
     * `return
     * clazz.getEnumConstants()[random.nextInt(clazz.getEnumConstants().length)];`
     * 
     * This function returns a random enum value from the enum class passed in
     * 
     * @param clazz The class of the enum to be returned.
     * @return A random enum value from the given enum class.
     */
    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        return clazz.getEnumConstants()[random.nextInt(clazz.getEnumConstants().length)];
    }

    /**
     * It creates three objects of different types, adds them to a list, and starts them
     */
    public void addNewObject() {
        Rebound rebound = new Rebound(randomEnum(ObjectType.class),
                new CoordinatesDTO(random.nextInt(animationController.getAnimationView().getViewer().getWidth() - 70),
                        random.nextInt(animationController.getAnimationView().getViewer().getHeight() - 70)),
                this, 70);
        objectsList.add(rebound);
        startObject(rebound);

        Gravity gravity = new Gravity(randomEnum(ObjectType.class),
                new CoordinatesDTO(random.nextInt(animationController.getAnimationView().getViewer().getWidth() - 70),
                        random.nextInt(animationController.getAnimationView().getViewer().getHeight() - 70)),
                this, 70);
        objectsList.add(gravity);
        startObject(gravity);

        Anarchic anarchic = new Anarchic(randomEnum(ObjectType.class),
                new CoordinatesDTO(random.nextInt(animationController.getAnimationView().getViewer().getWidth() - 70),
                        random.nextInt(animationController.getAnimationView().getViewer().getHeight() - 70)),
                this, 70);
        objectsList.add(anarchic);
        startObject(anarchic);
    }

    /**
     * For each object in the list, set the status of the object to the status passed in.
     * 
     * @param animatedObjectStatus The status of the object.
     */
    public void setObjectStatus(AnimatedObjectStatus animatedObjectStatus) {
        for (AnimatedObject animatedObject : objectsList) {
            animatedObject.setStatus(animatedObjectStatus);
        }
    }

    public void startObject(AnimatedObject animatedObject) {
        Thread t = new Thread(animatedObject);
        t.start();
    }

    public void startModel() {
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * If the animation is stopped, start it. If it's paused, resume it
     */
    public synchronized void play() {
        if (status == AnimationStatus.STOPPED) {
            startModel();
        } else if (status == AnimationStatus.PAUSED) { 
            notifyAll();
        }
        setObjectStatus(AnimatedObjectStatus.RUNNING);
        setStatus(AnimationStatus.RUNNING);
    }

    /**
     * This function sets the status of the object to paused and the status of the animation to paused
     */
    public synchronized void pause() {
        setObjectStatus(AnimatedObjectStatus.PAUSED);
        setStatus(AnimationStatus.PAUSED);
    }

    /**
     * This function stops the animation by setting the status of the animation to stopped and removing
     * all objects from the list of objects
     */
    public synchronized void stop() {
        setObjectStatus(AnimatedObjectStatus.STOPPED);
        setStatus(AnimationStatus.STOPPED);
        objectsList.removeAll(objectsList);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (getStatus() != AnimationStatus.STOPPED) {
            if (objectsList.size() < minObjects) {
                addNewObject();
            }
            try {
                // pause();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
