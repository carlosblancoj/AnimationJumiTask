import java.util.Random;

public class Anarchic extends AnimatedObject {
    
    private Integer dirX, dirY;

    private Random random;

    // * Constructor

    public Anarchic(ObjectType objectType, CoordinatesDTO coordinates, AnimationModel animationModel,
            Integer sizeImage) {
        super(objectType, coordinates, animationModel, sizeImage);

        this.random = new Random();
    }

    // * Methods

    @Override
    protected void update() throws InterruptedException {

        dirX = random.nextInt(5) - 1;
        dirY = random.nextInt(5) - 1;

        if (this.getCoordinates().getX() <= 0) {
            this.getCoordinates().setX(0);
        } else if (this.getCoordinates().getX() >= super.getAnimationModel().getAnimationController().getAnimationView()
                .getViewer().getWidth() - super.getSizeImage()) {
            this.getCoordinates().setX(super.getAnimationModel().getAnimationController().getAnimationView()
                    .getViewer().getWidth() - super.getSizeImage());
        }

        if (this.getCoordinates().getY() <= 0) {
            this.getCoordinates().setY(0);
        } else if (this.getCoordinates().getY() >= super.getAnimationModel().getAnimationController().getAnimationView()
                .getViewer().getHeight() - super.getSizeImage()) {
            this.getCoordinates().setY(super.getAnimationModel().getAnimationController().getAnimationView()
                    .getViewer().getHeight() - super.getSizeImage());
        }

        this.getCoordinates().setX(this.getCoordinates().getX() + dirX);
        this.getCoordinates().setY(this.getCoordinates().getY() + dirY);
        Thread.sleep(30);
    }
}
