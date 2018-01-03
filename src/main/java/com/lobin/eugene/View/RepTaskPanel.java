package com.lobin.eugene.View;

import com.lobin.eugene.Controller.TaskConstant;

import javax.swing.GroupLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.Date;

import static com.lobin.eugene.View.Config.createGroupLayout;
import static com.lobin.eugene.View.Config.setDateEditor;

/**
 * Class with repetitive panel for jFrame.
 *
 * @author Eugene Lobin
 * @version 1.0 28 Dec 2017
 */
public class RepTaskPanel implements TaskConstant {
    private JPanel panel = new JPanel();
    private JTextField title = new JTextField();
    private SpinnerDateModel modelStart = new SpinnerDateModel();
    private SpinnerDateModel modelEnd = new SpinnerDateModel();
    private JSpinner startTime = new JSpinner(modelStart);
    private JSpinner endTime = new JSpinner(modelEnd);
    private JFormattedTextField interval;
    private JRadioButton active = new JRadioButton();

    RepTaskPanel() {
        GroupLayout groupLayout =  createGroupLayout(panel);

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        interval = new JFormattedTextField(formatter);
        interval.setText("1");

        setDateEditor(startTime);
        setDateEditor(endTime);

        JLabel lTitle = new JLabel("Title");
        lTitle.setPreferredSize(new Dimension(TITLE_WIDTH, TITLE_HEIGHT));
        JLabel lStartTime = new JLabel("Start date");
        JLabel lEndTime = new JLabel("End date");
        JLabel lInterval = new JLabel("Interval(sec)");
        JLabel lActive = new JLabel("Active");

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(lTitle)
                        .addComponent(lStartTime)
                        .addComponent(lEndTime)
                        .addComponent(lInterval)
                        .addComponent(lActive))
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(this.title)
                        .addComponent(this.startTime)
                        .addComponent(this.endTime)
                        .addComponent(this.interval)
                        .addComponent(this.active)));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lTitle)
                        .addComponent(this.title))
                .addGroup(groupLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lStartTime)
                        .addComponent(this.startTime))
                .addGroup(groupLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lEndTime)
                        .addComponent(this.endTime))
                .addGroup(groupLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lInterval)
                        .addComponent(this.interval))
                .addGroup(groupLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lActive)
                        .addComponent(this.active)));

        groupLayout.linkSize(SwingConstants.HORIZONTAL, lTitle,
                lStartTime, lEndTime, lInterval, lActive);
    }

    /**
     * @return repetitive panel
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
     * @return task start time
     */
    public Date getStartTime() {
        return (Date) startTime.getValue();
    }

    /**
     * @return task end time
     */
    public Date getEndTime() {
        return (Date) endTime.getValue();
    }

    /**
     * @return task interval
     */
    public int getInterval() {
        return Integer.parseInt(interval.getText());
    }

    /**
     * @return task active
     */
    public boolean getActive() {
        return active.isSelected();
    }

    /**
     * @param title text for task
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }

    /**
     * @param startTime start date for task
     */
    public void setStartTime(Object startTime) {
        this.startTime.setValue(startTime);
    }

    /**
     * @param endTime end date for task
     */
    public void setEndTime(Object endTime) {
        this.endTime.setValue(endTime);
    }

    /**
     * @param interval interval for task
     */
    public void setInterval(int interval) {
        this.interval.setValue(interval);
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
        startTime.setValue(new Date());
        endTime.setValue(new Date());
        interval.setValue(1);
        active.setSelected(false);
    }
}
