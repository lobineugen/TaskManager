package com.lobin.eugene.View;

import com.lobin.eugene.Controller.TaskConstant;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Date;

import static com.lobin.eugene.View.Config.createGroupLayout;
import static com.lobin.eugene.View.Config.setDateEditor;

/**
 * Class with calendar panel for jFrame.
 *
 * @author Eugene Lobin
 * @version 1.0 28 Dec 2017
 */
public class CalendarPanel implements TaskConstant {
    private JPanel calendarPanel = new JPanel();
    private JButton bBack = new JButton("Back");
    private JTextArea jTextArea = new JTextArea(ROWS, COLUMNS);
    private SpinnerDateModel modelStart = new SpinnerDateModel();
    private SpinnerDateModel modelEnd = new SpinnerDateModel();
    private JSpinner startTime = new JSpinner(modelStart);
    private JSpinner endTime = new JSpinner(modelEnd);
    private JButton bShow = new JButton("Show");

    /**
     * Constructor created calendar panel
     */
    public CalendarPanel() {
        GroupLayout glCalendar = createGroupLayout(calendarPanel);

        JScrollPane textArea = new JScrollPane(jTextArea);
        JLabel lStart = new JLabel("Start date");
        JLabel lEnd = new JLabel("End date");

        setDateEditor(startTime);
        setDateEditor(endTime);

        glCalendar.setHorizontalGroup(glCalendar.createSequentialGroup()
                .addGroup(glCalendar.createParallelGroup()
                        .addGroup(glCalendar.createSequentialGroup()
                                .addComponent(lStart)
                                .addComponent(startTime)
                                .addComponent(bShow)
                        )
                        .addGroup(glCalendar.createSequentialGroup()
                                .addComponent(lEnd)
                                .addComponent(endTime)
                                .addComponent(bBack)
                        )
                        .addComponent(textArea)
                )
        );
        glCalendar.setVerticalGroup(glCalendar.createSequentialGroup()
                .addGroup(glCalendar.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lStart)
                        .addComponent(startTime)
                        .addComponent(bShow)
                )
                .addGroup(glCalendar.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lEnd)
                        .addComponent(endTime)
                        .addComponent(bBack)
                )
                .addComponent(textArea)
        );
        glCalendar.linkSize(SwingConstants.HORIZONTAL, bBack,
                bShow, startTime, endTime);
        glCalendar.linkSize(SwingConstants.HORIZONTAL, lEnd,
                lStart);
    }

    /**
     * @return calendar panel
     */
    public JPanel getCalendarPanel() {
        return calendarPanel;
    }

    /**
     * @param listener for back button
     */
    public void addBackActionListener(ActionListener listener) {
        bBack.addActionListener(listener);
    }

    /**
     * @return start date
     */
    public Date getStartDate() {
        return (Date) startTime.getValue();
    }

    /**
     * @return show button
     */
    public JButton getShow() {
        return bShow;
    }

    /**
     * @return end date
     */
    public Date getEndDate() {
        return (Date) endTime.getValue();
    }

    /**
     * @return text area
     */
    public JTextArea getTextArea() {
        return jTextArea;
    }
}
