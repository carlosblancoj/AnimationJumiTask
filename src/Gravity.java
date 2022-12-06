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

        this.dirY += 0.1f;

        if (this.getCoordinates().getX() <= 0) {
            this.dirX = speedX;
        } else if(this.getCoordinates().getX() >= super.getAnimationModel().getAnimationController().getAnimationView().getViewer().getWidth() - super.getSizeImage()){
            this.dirX = -speedX;
        } 

        if(this.getCoordinates().getY() <= 0){
            this.dirY = speedY;
        } else if(this.getCoordinates().getY() >= super.getAnimationModel().getAnimationController().getAnimationView().getViewer().getHeight() - super.getSizeImage()){
            this.speedY *= 0.8f;
            this.dirY = -speedY; 
        } 
        
        this.getCoordinates().setX(this.getCoordinates().getX() + Math.round(this.dirX));
        this.getCoordinates().setY(this.getCoordinates().getY() + Math.round(this.dirY));
        Thread.sleep(30);
    }
}
