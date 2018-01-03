package com.lobin.eugene.View;

import com.lobin.eugene.Controller.TaskConstant;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.event.ActionListener;

import static com.lobin.eugene.View.Config.createGroupLayout;

public class StartPanel implements TaskConstant {
    private JComboBox<String> taskList = new JComboBox<>();
    private JButton bAddNewList = new JButton("Add list");
    private JButton bDeleteList = new JButton("Delete list");
    private JPanel startPanel = new JPanel();
    private JButton bCreateNewTask = new JButton("Create new task");
    private JButton bEdit = new JButton("Edit task");
    private JButton bCalendar = new JButton("Calendar");
    private JButton bRemove = new JButton("Remove task");
    private JButton bOpen = new JButton("Open list");
    private JTable jTableRep = createTable(new Object[]{"Title",
            "Start time", "End time", "Interval(sec)", "Active"});
    private JTable jTableNonRep = createTable(new Object[]{"Title",
            "Date", "Active"});

    StartPanel() {
        JLabel lTaskList = new JLabel("Task list");
        JLabel lRepTask = new JLabel("Repetitive tasks");
        JLabel lNonRepTask = new JLabel("Non-repetitive tasks");

        jTableRep.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableNonRep.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GroupLayout glStart = createGroupLayout(startPanel);

        JScrollPane spNonRep = new JScrollPane(jTableNonRep,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane spRep = new JScrollPane(jTableRep,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        glStart.setHorizontalGroup(glStart.createSequentialGroup()
                .addGroup(glStart.createParallelGroup()
                        .addGroup(glStart.createSequentialGroup()
                                .addComponent(lTaskList)
                                .addComponent(taskList)
                                .addComponent(bAddNewList)
                                .addComponent(bDeleteList)
                                .addComponent(bOpen))
                        .addGroup(glStart.createSequentialGroup()
                                .addGroup(glStart.createParallelGroup()
                                        .addComponent(lNonRepTask)
                                        .addComponent(spNonRep))
                                .addGroup(glStart.createParallelGroup()
                                        .addComponent(bCreateNewTask)
                                        .addComponent(bEdit)
                                        .addComponent(bRemove)
                                        .addComponent(bCalendar)))
                        .addGroup(glStart.createParallelGroup()
                                .addComponent(lRepTask)
                                .addComponent(spRep))));

        glStart.setVerticalGroup(glStart.createSequentialGroup()
                .addGroup(glStart.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addComponent(lTaskList)
                        .addComponent(taskList)
                        .addComponent(bAddNewList)
                        .addComponent(bDeleteList)
                        .addComponent(bOpen))
                .addComponent(lNonRepTask)
                .addGroup(glStart.createParallelGroup()
                        .addComponent(spNonRep)
                        .addGroup(glStart.createSequentialGroup()
                                .addComponent(bCreateNewTask)
                                .addComponent(bEdit)
                                .addComponent(bRemove)
                                .addComponent(bCalendar)))
                .addGroup(glStart.createSequentialGroup()
                        .addComponent(lRepTask)
                        .addComponent(spRep)));

        glStart.linkSize(SwingConstants.HORIZONTAL,
                bCreateNewTask, bEdit, bRemove, bCalendar);
        glStart.linkSize(SwingConstants.HORIZONTAL,
                bAddNewList, bDeleteList, bOpen);
    }

    /**
     * @return start panel
     */
    public JPanel getStartPanel() {
        return startPanel;
    }

    /**
     * @return repetitive table
     */
    public JTable getTableRep() {
        return jTableRep;
    }

    /**
     * @return non-repetitive table
     */
    public JTable getTableNonRep() {
        return jTableNonRep;
    }

    /**
     * @param listener for create new task button
     */
    public void addCreateTaskActionListener(ActionListener listener) {
        bCreateNewTask.addActionListener(listener);
    }

    /**
     * @param listener for calendar button
     */
    public void addCalendarActionListener(ActionListener listener) {
        bCalendar.addActionListener(listener);
    }

    /**
     * @param objects array for table header
     * @return new table
     */
    private JTable createTable(Object[] objects) {
        JTable table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                                             int row, int column) {
                Component component = super.prepareRenderer(renderer,
                        row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth
                                + getIntercellSpacing().width + PIXEL,
                        tableColumn.getPreferredWidth()));
                return component;
            }
        };
        DefaultTableModel defaultTableModel =
                new DefaultTableModel(objects, 0) {
                    // переопределенный метод запрещает
                    // редактирования ячеек
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
        //запрет перемещения названий столбцов
        table.getTableHeader().setReorderingAllowed(false);
        // запрет изменений размером
        // названий столбцов
        table.getTableHeader().setResizingAllowed(false);
        table.setModel(defaultTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return table;
    }

    /**
     * @return remove button
     */
    public JButton getRemove() {
        return bRemove;
    }

    /**
     * @return edit button
     */
    public JButton getEdit() {
        return bEdit;
    }

    /**
     * @return add new list button
     */
    public JButton getAddNewList() {
        return bAddNewList;
    }

    /**
     * @return delete list button
     */
    public JButton getDeleteList() {
        return bDeleteList;
    }

    /**
     * @return delete list button
     */
    public JButton getOpen() {
        return bOpen;
    }

    /**
     * @return task list
     */
    public JComboBox<String> getTaskList() {
        return taskList;
    }

    /**
     * @return calendar button
     */
    public JButton getCalendar() {
        return bCalendar;
    }

    /**
     * @return create new task button
     */
    public JButton getCreateNewTask() {
        return bCreateNewTask;
    }
}
