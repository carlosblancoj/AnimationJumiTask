import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ControlPanel extends JPanel {

    private JButton bPlay;
    private JButton bPause;
    private JButton bStop;

    private AnimationView animationView;

    // * Constructor

    public ControlPanel(GridBagConstraints gbc, AnimationView animationView) {
        this.animationView = animationView;

        this.setLayout(new GridBagLayout());
        addButtons(gbc);
    }

    // * Getters & Setters

    public JButton getbPlay() {
        return bPlay;
    }

    public JButton getbPause() {
        return bPause;
    }

    public JButton getbStop() {
        return bStop;
    }

    // * Methods

    public void addButtons(GridBagConstraints gbc) {

        bPlay = new JButton("PLAY");
        bPlay.addActionListener(animationView);
        gbc.gridx = 0;
        gbc.gridy = 0;
        /*
         * gbc.weightx = 1;
         * gbc.weighty = .5;
         */
        // gbc.fill = GridBagConstraints.VERTICAL;
        this.add(bPlay, gbc);

        bPause = new JButton("PAUSE");
        bPause.addActionListener(animationView);

        gbc.gridx = 1;
        gbc.gridy = 0;
        /*
         * gbc.weightx = 1;
         * gbc.weighty = .5;
         */
        // gbc.fill = GridBagConstraints.VERTICAL;
        this.add(bPause, gbc);

        bStop = new JButton("STOP");
        bStop.addActionListener(animationView);

        gbc.gridx = 2;
        gbc.gridy = 0;
        /*
         * gbc.weightx = 1;
         * gbc.weighty = .5;
         */
        // gbc.fill = GridBagConstraints.VERTICAL;
        this.add(bStop, gbc);
    }
}
