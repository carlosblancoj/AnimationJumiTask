public class Gravity extends AnimatedObject{
    private float dirX, dirY;

    private float speedX = 10;
    private float speedY = 10;

    // * Constructor

    public Gravity(ObjectType objectType, CoordinatesDTO coordinates, AnimationModel animationModel, Integer sizeImage) {
        super(objectType, coordinates, animationModel, sizeImage);

        this.dirX = speedX;
        this.dirY = speedY;
    }

    // * Methods

    @Override
    protected void update() throws InterruptedException {

        dirY += 0.1f;

        if (this.getCoordinates().getX() <= 0) {
            dirX = speedX;
        } else if(this.getCoordinates().getX() >= super.getAnimationModel().getAnimationController().getAnimationView().getViewer().getWidth() - super.getSizeImage()){
            dirX = -speedX;
        } 

        if(this.getCoordinates().getY() <= 0){
            dirY = speedY;
        } else if(this.getCoordinates().getY() >= super.getAnimationModel().getAnimationController().getAnimationView().getViewer().getHeight() - super.getSizeImage()){
            speedY *= 0.8f;
            dirY = -speedY; 
        } 
        
        this.getCoordinates().setX(this.getCoordinates().getX() + Math.round(dirX));
        this.getCoordinates().setY(this.getCoordinates().getY() + Math.round(dirY));
        Thread.sleep(15);
    }
}
