/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automenta.netention;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

public class NowPanel extends JPanel {

    public final Memory memory;

    public class InputPanel extends JPanel {

        private final JTextArea inputArea;
        //TODO add drag-and-drop support

        public InputPanel() {
            super(new BorderLayout());

            inputArea = new JTextArea();
            inputArea.setFont(inputArea.getFont().deriveFont((float) (inputArea.getFont().getSize() * 3)));
            add(inputArea, BorderLayout.CENTER);

        }
    }

    public class ThingTreePanel extends JPanel {

        private final JTree tree;
        private final JComboBox modeSelect;

        public ThingTreePanel() {
            super(new BorderLayout());

            JPanel buttonPanel = new JPanel(new FlowLayout());

            DefaultComboBoxModel modeModel = new DefaultComboBoxModel();
            modeModel.addElement("In");
            modeModel.addElement("Frequent");
            modeModel.addElement("Recent");
            modeModel.addElement("What"); //Types
            modeModel.addElement("Who");  //by agents
            modeModel.addElement("Where");  //locations
            modeModel.addElement("When");  //time frames (tomorrow, 5 mins ago, 10 mins ago, yesterday, etc..)
            modeModel.addElement("Why"); //categories = tags. the tag itself answers 'why' something was tagged / classified

            this.modeSelect = new JComboBox(modeModel);

            JButton newButton = new JButton("+");
            buttonPanel.add(newButton, BorderLayout.WEST);
            
            add(modeSelect, BorderLayout.NORTH);

            add(buttonPanel, BorderLayout.SOUTH);

            this.tree = new JTree((TreeNode)new GraphTreeProjectionModel(memory, modeSelect));
            tree.setRootVisible(false);
            add(tree, BorderLayout.CENTER);


            //TODO add widgets that show the current size of the memory
        }
    }

    public class BrowserPanel extends JPanel {

        private final JSplitPane split;

        public BrowserPanel() {
            super(new BorderLayout());
            

            split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            add(split, BorderLayout.CENTER);

            split.setLeftComponent(new ThingTreePanel());

            split.setRightComponent(new BrowserContentPanel());

        }
        
    }

    public class BrowserContentPanel extends JPanel {

        public BrowserContentPanel() {
            super(new BorderLayout());

            JMenuBar menu = new JMenuBar();

            JButton backButton = new JButton("<-");
            menu.add(backButton); //backward

            menu.add(new JButton("<*")); //incoming
            menu.add(new JMenu("View"));
            menu.add(new JButton("*>")); //outgoing
            menu.add(new JButton("->")); //forward
            
            menu.add(new JMenu("Actions1"));
            menu.add(new JMenu("MoreActions"));
            add(menu, BorderLayout.NORTH);
        }


    }

    public NowPanel(Memory m) {
        super(new BorderLayout());
        this.memory = m;
//        split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//        add(split, BorderLayout.CENTER);
//

        //split.setTopComponent(new InputPanel());
        add(new BrowserPanel(), BorderLayout.CENTER);

        updateUI();
    }
}
