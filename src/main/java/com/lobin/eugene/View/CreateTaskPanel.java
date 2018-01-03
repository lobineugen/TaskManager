package com.lobin.eugene.View;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Date;

import static com.lobin.eugene.View.Config.createGroupLayout;

/**
 * Class with create task panel for jFrame.
 *
 * @author Eugene Lobin
 * @version 1.0 28 Dec 2017
 */

public class CreateTaskPanel {
    private JPanel createTaskPanel = new JPanel();
    private JButton bCreateTask = new JButton("Create task");
    private JButton bBack = new JButton("Back");
    private JRadioButton rbCreateNonReTask =
            new JRadioButton("Create non-repetitive task", true);
    private JRadioButton rbCreateReTask =
            new  JRadioButton("Create repetitive task");
    private ButtonGroup bgSelectTaskType = new ButtonGroup();
    private JPanel pRepTask;
    private JPanel pNonRepTask;
    private RepTaskPanel repTaskPanel = new RepTaskPanel();
    private NonRepTaskPanel nonRepTaskPanel = new NonRepTaskPanel();


    CreateTaskPanel() {
        GroupLayout glCreateTask = createGroupLayout(createTaskPanel);

        pRepTask = repTaskPanel.getPanel();
        pNonRepTask = nonRepTaskPanel.getPanel();

        rbCreateReTask.setActionCommand("Repeatable");
        rbCreateNonReTask.setActionCommand("NonRepeatable");

        pRepTask.setVisible(false);

        bgSelectTaskType.add(rbCreateNonReTask);
        bgSelectTaskType.add(rbCreateReTask);

        glCreateTask.setHorizontalGroup(glCreateTask.createSequentialGroup()
                .addGroup(glCreateTask.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                        .addComponent(rbCreateNonReTask)
                        .addComponent(rbCreateReTask)
                        .addComponent(pNonRepTask)
                        .addComponent(pRepTask))
                .addGroup(glCreateTask.createParallelGroup()
                        .addComponent(bCreateTask)
                        .addComponent(bBack))

        );
        glCreateTask.setVerticalGroup(glCreateTask.createSequentialGroup()
                .addGroup(glCreateTask.createParallelGroup()
                        .addGroup(glCreateTask.createSequentialGroup()
                                .addComponent(rbCreateNonReTask)
                                .addComponent(rbCreateReTask)
                                .addComponent(pNonRepTask)
                                .addComponent(pRepTask))
                        .addGroup(glCreateTask.createSequentialGroup()
                                .addComponent(bCreateTask)
                                .addComponent(bBack)))
        );

        glCreateTask.linkSize(SwingConstants.HORIZONTAL, bCreateTask, bBack);
    }

    /**
     * @return create task panel
     */
    public JPanel getCreateTaskPanel() {
        return createTaskPanel;
    }

    /**
     * @return repetitive task panel
     */
    public JPanel getRepTask() {
        return pRepTask;
    }

    /**
     * @return non-repetitive task panel
     */
    public JPanel getNonRepTask() {
        return pNonRepTask;
    }

    /**
     * @return task title from non-repetitive task panel
     */
    public String getTaskTitleOne() {
        return nonRepTaskPanel.getTitle();
    }

    /**
     * @return task title from repetitive task panel
     */
    public String getTaskTitleTwo() {
        return repTaskPanel.getTitle();
    }

    /**
     * @return task type(non-repetitive or repetitive)
     */
    public Boolean getTaskType() {
        return bgSelectTaskType.getSelection()
                .getActionCommand().equals("Repeatable");
    }

    /**
     * @return date
     */
    public Date getDateTime() {
        return nonRepTaskPanel.getDate();
    }

    /**
     * @return start date
     */
    public Date getStartTime() {
        return repTaskPanel.getStartTime();
    }

    /**
     * @return end date
     */
    public Date getEndTime() {
        return repTaskPanel.getEndTime();
    }

    /**
     * @return interval
     */
    public int getInterval() {
        return repTaskPanel.getInterval();
    }

    /**
     * @return active from non-repetitive panel
     */
    public boolean getActiveOne() {
        return nonRepTaskPanel.getActive();
    }

    /**
     * @return active from repetitive panel
     */
    public boolean getActiveTwo() {
        return repTaskPanel.getActive();
    }


    /**
     * @param listener for create repetitive task button
     */
    public void addCreateReTaskListener(ItemListener listener) {
        rbCreateReTask.addItemListener(listener);
    }

    /**
     * @param listener for create non-repetitive task button
     */
    public void addCreateNonReTaskListener(ItemListener listener) {
        rbCreateNonReTask.addItemListener(listener);
    }

    /**
     * @param listener for back button
     */
    public void addButtonBackListener(ActionListener listener) {
        bBack.addActionListener(listener);
    }


    /**
     * @return create task button
     */
    public JButton getCreateTask() {
        return bCreateTask;
    }

    /**
     * @return repetitive task panel
     */
    public RepTaskPanel getRepTaskPanel() {
        return repTaskPanel;
    }

    /**
     * @return non-repetitive task panel
     */
    public NonRepTaskPanel getNonRepTaskPanel() {
        return nonRepTaskPanel;
    }
}
