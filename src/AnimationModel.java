import java.util.ArrayList;
import java.util.Random;
import java.awt.Image;

public class AnimationModel implements Runnable {

    private AnimationStatus status;
    private ArrayList<AnimatedObject> objectsList;
    // private Integer[][] statistics;

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

    // Método para escoger un valor random de la enumeración
    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        return clazz.getEnumConstants()[random.nextInt(clazz.getEnumConstants().length)];
    }

    public void addNewObject() {
        // for (int i = 0; i < numObjects; i++) {
        /* Rebound rebound = new Rebound(randomEnum(ObjectType.class),
                new CoordinatesDTO(random.nextInt(1280), random.nextInt(960)), this, 70); */
        Gravity gravity = new Gravity(randomEnum(ObjectType.class),
        new CoordinatesDTO(random.nextInt(1280), random.nextInt(960)), this, 70);
        objectsList.add(gravity);
        startObject(gravity);
        // }
    }

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

    public synchronized void play() {
        if (status == AnimationStatus.STOPPED) {
            setStatus(AnimationStatus.RUNNING);

            startModel();

        } else if (status == AnimationStatus.PAUSED) {
            setStatus(AnimationStatus.RUNNING);
            setObjectStatus(AnimatedObjectStatus.RUNNING);
            notifyAll();
        }
    }

    public void pause() {
        setObjectStatus(AnimatedObjectStatus.PAUSED);
        setStatus(AnimationStatus.PAUSED);
    }

    public void stop() {
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
        // notifyAll();
    }
}
