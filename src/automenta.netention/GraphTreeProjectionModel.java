/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automenta.netention;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author seh
 */
public class GraphTreeProjectionModel extends DefaultMutableTreeNode implements ActionListener {
    private final JComboBox mode;


    GraphTreeProjectionModel(Memory memory, JComboBox modeSelect) {
        super("Root");

        this.mode = modeSelect;
        mode.addActionListener(this);

        refresh();

    }

    public void actionPerformed(ActionEvent e) {
        refresh();
    }

    protected void refresh() {
        removeAllChildren();
        
        add(new DefaultMutableTreeNode("ABC"));
        add(new DefaultMutableTreeNode("Xyz"));
        add(new DefaultMutableTreeNode("111"));

    }


}
