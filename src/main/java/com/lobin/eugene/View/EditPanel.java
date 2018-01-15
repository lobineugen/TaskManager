package com.lobin.eugene.View;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;

import static com.lobin.eugene.View.Config.createGroupLayout;

/**
 * Class with edit panel for jFrame.
 *
 * @author Eugene Lobin
 * @version 1.0 28 Dec 2017
 */

public class EditPanel {
    private JPanel editPanel = new JPanel();
    private JPanel pEditRepTask;
    private JPanel pEditNonRepTask;
    private RepTaskPanel repTaskPanel = new RepTaskPanel();
    private NonRepTaskPanel nonRepTaskPanel = new NonRepTaskPanel();
    private JButton bSave = new JButton("Save");
    private JButton bCancel = new JButton("Back ");

    public EditPanel() {
        GroupLayout glEdit = createGroupLayout(editPanel);

        pEditRepTask = repTaskPanel.getPanel();
        pEditNonRepTask = nonRepTaskPanel.getPanel();

        glEdit.setVerticalGroup(glEdit.createSequentialGroup()
                .addGroup(glEdit.createParallelGroup()
                        .addGroup(glEdit.createSequentialGroup()
                                .addComponent(pEditNonRepTask)
                                .addComponent(pEditRepTask))
                        .addGroup(glEdit.createSequentialGroup()
                                .addComponent(bSave)
                                .addComponent(bCancel))));

        glEdit.setHorizontalGroup(glEdit.createSequentialGroup()
                .addGroup(glEdit.createParallelGroup()
                        .addComponent(pEditNonRepTask)
                        .addComponent(pEditRepTask))
                .addGroup(glEdit.createParallelGroup()
                        .addComponent(bSave)
                        .addComponent(bCancel)));
        glEdit.linkSize(SwingConstants.HORIZONTAL, bSave, bCancel);

    }

    /**
     * @return edit panel
     */
    public JPanel getEditPanel() {
        return editPanel;
    }

    /**
     * @return edit non-repetitive task panel
     */
    public JPanel getEditNonRepTask() {
        return pEditNonRepTask;
    }

    /**
     * @return edit repetitive task panel
     */
    public JPanel getEditRepTask() {
        return pEditRepTask;
    }

    /**
     * @param listener for cancel button
     */
    public void addCancelButtonListener(ActionListener listener) {
        bCancel.addActionListener(listener);
    }

    /**
     * @return non-repetitive task panel
     */
    public NonRepTaskPanel getNonRepTaskPanel() {
        return nonRepTaskPanel;
    }

    /**
     * @return repetitive task panel
     */
    public RepTaskPanel getRepTaskPanel() {
        return repTaskPanel;
    }

    /**
     * @return save button
     */
    public JButton getSave() {
        return bSave;
    }
}
