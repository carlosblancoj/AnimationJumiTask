public class MyTask {
    public static void main(String[] args) {
        AnimationModel animationModel = new AnimationModel(6, 12);
        AnimationView animationView = new AnimationView(30); 
        AnimationController animationController = new AnimationController(animationModel, animationView);
        animationView.setAnimationController(animationController);
        animationModel.setAnimationController(animationController);

        Thread t = new Thread(animationView);
        t.start();
    }
}
