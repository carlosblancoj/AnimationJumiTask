import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class StatsPanel extends JPanel {

    public StatsPanel(GridBagConstraints gbc) {
        this.setLayout(new GridBagLayout());

        Integer data[][] = new Integer[4][4];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j] = 0;
            }
        }
        String column[] = { "RUNNING", "PAUSED", "STOPPED", "DEAD" };
        JTable jt = new JTable(data, column);
        JScrollPane sp = new JScrollPane(jt);
        JTable rt = new RowNumberTable(jt);
        sp.setRowHeaderView(rt);
        sp.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rt.getTableHeader());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(sp, gbc);
    }
}
