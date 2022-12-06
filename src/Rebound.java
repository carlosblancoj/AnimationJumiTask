public class Rebound extends AnimatedObject {

    private Integer dirX, dirY;

    private Integer speedX = 3;
    private Integer speedY = 3;

    // * Constructor

    public Rebound(ObjectType objectType, CoordinatesDTO coordinates, AnimationModel animationModel, Integer sizeImage) {
        super(objectType, coordinates, animationModel, sizeImage);

        this.dirX = speedX;
        this.dirY = speedY;
    }

    // * Methods

    @Override
    protected void update() throws InterruptedException {
        // TODO Auto-generated method stub
        if (this.getCoordinates().getX() <= 0) {
            this.dirX = speedX;
        } else if(this.getCoordinates().getX() >= super.getAnimationModel().getAnimationController().getAnimationView().getViewer().getWidth() - super.getSizeImage()){
            this.dirX = -speedX;
        } else if(this.getCoordinates().getY() <= 0){
            this.dirY = speedY;
        } else if(this.getCoordinates().getY() >= super.getAnimationModel().getAnimationController().getAnimationView().getViewer().getHeight() - super.getSizeImage()){
            this.dirY = -speedY;
        } 

        this.getCoordinates().setX(this.getCoordinates().getX() + this.dirX);
        this.getCoordinates().setY(this.getCoordinates().getY() + this.dirY);
        Thread.sleep(7);
    }
}
