import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class AnimationView extends JFrame implements Runnable, ActionListener {

    private StatsPanel statsPanel;
    private ControlPanel controlPanel;
    private Viewer viewer;

    private Integer refreshMilis;

    private AnimationController animationController;

    // * Constructor

    public AnimationView(Integer refreshMilis) {
        this.refreshMilis = refreshMilis;

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.viewer = new Viewer();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = .6;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.getContentPane().add(viewer, gbc);
        gbc.weightx = 0;
        gbc.weighty = 0;

        this.controlPanel = new ControlPanel(gbc, this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = .4;
        gbc.weighty = .3;
        gbc.fill = GridBagConstraints.BOTH;
        this.getContentPane().add(controlPanel, gbc);
        gbc.weightx = 0;
        gbc.weighty = 0;

        this.statsPanel = new StatsPanel(gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weightx = .4;
        gbc.weighty = .7;
        gbc.fill = GridBagConstraints.BOTH;
        this.getContentPane().add(statsPanel, gbc);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setLocation(0, 0);
        this.setVisible(true);
    }

    // * Getters & Setters

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
    }

    public AnimationController getAnimationController() {
        return animationController;
    }

    public Viewer getViewer() {
        return viewer;
    }

    // * Methods

    /**
     * If there are objects in the list, draw them
     * 
     * @param g Graphics object
     */
    public void refresh(Graphics g) {
        if (animationController.getAnimationModel().getObjectsList().size() > 0) {
            for (AnimatedObject animatedObject : animationController.getAnimationModel().getObjectsList()) {
                animatedObject.drawObject(g);
            }
        } else {
            return;
        }
    }

    // * Interface Methods

    /**
     * The function is called when a button is pressed. It checks which button was pressed and calls
     * the appropriate function in the model
     * 
     * @param e the event that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == controlPanel.getbPlay()) {
            animationController.getAnimationModel().play();
        } else if (e.getSource() == controlPanel.getbPause()) {
            animationController.getAnimationModel().pause();
        } else if (e.getSource() == controlPanel.getbStop()) {
            animationController.getAnimationModel().stop();
        }
    }

    @Override
    public void run() {

        viewer.createBufferStrategy(2);

        while (true) {
            // The main loop of the program. It is called every `refreshMilis` milliseconds. It draws
            // the objects on the screen using a double-buffer technique
            BufferStrategy bs = viewer.getBufferStrategy();
            Graphics gg = bs.getDrawGraphics();

            if (bs == null) {
                return;
            } else {
                viewer.drawObject(gg);
                refresh(gg);
                bs.show();
                gg.dispose();
                try {
                    Thread.sleep(refreshMilis);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // Updating the statistics panel.
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    statsPanel.setStatistics();
                }
            });
        }
    }
}
