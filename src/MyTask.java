public class MyTask {
    public static void main(String[] args) {
        AnimationModel animationModel = new AnimationModel(4, 8);
        AnimationView animationView = new AnimationView(30); 
        AnimationController animationController = new AnimationController(animationModel, animationView);
        animationView.setAnimationController(animationController);
        animationModel.setAnimationController(animationController);

        //animationModel.start();
        Thread t = new Thread(animationView);
        t.start();
        /* Thread t2 = new Thread(animationModel);
        t2.start(); */
    }
}
