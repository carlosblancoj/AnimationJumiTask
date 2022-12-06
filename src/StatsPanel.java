import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class StatsPanel extends JPanel {

    private JTable jt;
    private DefaultTableModel tableModel;

    public StatsPanel(GridBagConstraints gbc) {
        this.setLayout(new GridBagLayout());

        this.jt = new JTable();
        setStatistics();
    
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

    /**
     * It takes the statistics from the AnimatedObject class and puts it into a table
     */
    public void setStatistics() {
        String column[] = { "RUNNING", "PAUSED", "STOPPED", "DEAD" };
        tableModel = new DefaultTableModel(AnimatedObject.statistics, column);
        jt.setModel(tableModel);
    }
}
