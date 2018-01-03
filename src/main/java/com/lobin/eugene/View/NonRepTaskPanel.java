package com.lobin.eugene.View;

import com.lobin.eugene.Controller.TaskConstant;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.util.Date;

import static com.lobin.eugene.View.Config.createGroupLayout;
import static com.lobin.eugene.View.Config.setDateEditor;

/**
 * Class with non-repetitive panel for jFrame.
 *
 * @author Eugene Lobin
 * @version 1.0 28 Dec 2017
 */
public class NonRepTaskPanel implements TaskConstant {
    private JPanel panel = new JPanel();
    private JTextField title = new JTextField();
    private SpinnerDateModel model = new SpinnerDateModel();
    private JSpinner date = new JSpinner(model);
    private JRadioButton active = new JRadioButton();


    NonRepTaskPanel() {
        GroupLayout groupLayout = createGroupLayout(panel);

        setDateEditor(date);

        JLabel lTitle = new JLabel("Title");
        lTitle.setPreferredSize(new Dimension(TITLE_WIDTH, TITLE_HEIGHT));
        JLabel lDate = new JLabel("Date");
        JLabel lActive = new JLabel("Active");


        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(lTitle)
                        .addComponent(lDate)
                        .addComponent(lActive))
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(this.title)
                        .addComponent(this.date)
                        .addComponent(this.active)));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lTitle)
                        .addComponent(this.title))
                .addGroup(groupLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lDate)
                        .addComponent(this.date))
                .addGroup(groupLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lActive)
                        .addComponent(this.active)));

        groupLayout.linkSize(SwingConstants.HORIZONTAL, lTitle, lDate, lActive);
    }

    /**
     * @return non-repetitive panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * @return task title
     */
    public String getTitle() {
        return title.getText();
    }

    /**
     * @return task date
     */
    public Date getDate() {
        return (Date) date.getValue();
    }

    /**
     * @return task active
     */
    public boolean getActive() {
        return active.isSelected();
    }


    /**
     * @param title text for title
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }

    /**
     * @param date date for task
     */
    public void setDate(Object date) {
        this.date.setValue(date);
    }

    /**
     * @param active for task
     */
    public void setActive(boolean active) {
        this.active.setSelected(active);
    }

    /**
     * Clear all field
     */
    public void clearField() {
        title.setText("");
        date.setValue(new Date());
        active.setSelected(false);
    }
}
