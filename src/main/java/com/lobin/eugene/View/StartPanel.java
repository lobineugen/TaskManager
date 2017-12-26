package com.lobin.eugene.View;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPanel {
    private JPanel startPanel = new JPanel();
    private JButton bCreateNewTask = new JButton("Create new task");
    private JButton bEdit = new JButton("Edit");
    private JButton bCalendar = new JButton("Calendar");
    private Object[] oNonRep = new Object[]{"Title", "Date", "Active"};
    private Object[] oRep = new Object[]{"Title", "Start time", "End time", "Interval", "Active"};
    private JTable jTableRep = createTable(oRep);
    private JTable jTableNonRep = createTable(oNonRep);
    private JButton bRemove = new JButton("Remove");

    StartPanel() {
        jTableRep.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableNonRep.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        GroupLayout glStart = new GroupLayout(startPanel);
        JScrollPane spNonRep = new JScrollPane(jTableNonRep, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane spRep = new JScrollPane(jTableRep, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        startPanel.setLayout(glStart);
        glStart.setAutoCreateGaps(true);
        glStart.setAutoCreateContainerGaps(true);
        glStart.setHorizontalGroup(glStart.createSequentialGroup()
                .addGroup(glStart.createParallelGroup()
                        .addGroup(glStart.createSequentialGroup()
                                .addComponent(spNonRep)
                                .addGroup(glStart.createParallelGroup()
                                        .addComponent(bCreateNewTask)
                                        .addComponent(bEdit)
                                        .addComponent(bRemove)
                                        .addComponent(bCalendar)))
                        .addComponent(spRep)));

        glStart.setVerticalGroup(glStart.createSequentialGroup()
                .addGroup(glStart.createParallelGroup()
                        .addComponent(spNonRep)
                        .addGroup(glStart.createSequentialGroup()
                                .addComponent(bCreateNewTask)
                                .addComponent(bEdit)
                                .addComponent(bRemove)
                                .addComponent(bCalendar)))
                .addComponent(spRep)
        );
        glStart.linkSize(SwingConstants.HORIZONTAL, bCreateNewTask, bEdit, bRemove, bCalendar);

    }

    public JPanel getStartPanel() {
        return startPanel;
    }

    public JTable getTableRep() {
        return jTableRep;
    }

    public JTable getTableNonRep() {
        return jTableNonRep;
    }

    public void addRemoveTaskListener(ActionListener listener) {
        bRemove.addActionListener(listener);
    }

    public void addEditActionListener(ActionListener listener) {
        bEdit.addActionListener(listener);
    }

    public void addCreateTaskActionListener(ActionListener listener) {
        bCreateNewTask.addActionListener(listener);
    }

    public void addCalendarActionListened(ActionListener listener) {
        bCalendar.addActionListener(listener);
    }

    private JTable createTable(Object[] objects) {
        JTable table = new JTable() {
            // переопределенный метод устанавливает ширину ячеек по содержимому
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        DefaultTableModel defaultTableModel = new DefaultTableModel(objects, 0) {
            // переопределенный метод запрещает редактирования ячеек
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //запрет перемещения названий столбцов
        table.getTableHeader().setReorderingAllowed(false);
        // запрет изменений размером названий столбцов
        table.getTableHeader().setResizingAllowed(false);
        table.setModel(defaultTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return table;
    }
}
