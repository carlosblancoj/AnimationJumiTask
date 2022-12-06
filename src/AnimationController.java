public class AnimationController {

    private AnimationView animationView;
    private AnimationModel animationModel;

    // * Constructor

    public AnimationController(AnimationModel animationModel, AnimationView animationView) {
        this.animationModel = animationModel;
        this.animationView = animationView;
    }
    
    // * Getters & Setters

    public void setAnimationView(AnimationView animationView) {
        this.animationView = animationView;
    }

    public void setAnimationModel(AnimationModel animationModel) {
        this.animationModel = animationModel;
    }

    public AnimationView getAnimationView() {
        return animationView;
    }

    public AnimationModel getAnimationModel() {
        return animationModel;
    }

}
