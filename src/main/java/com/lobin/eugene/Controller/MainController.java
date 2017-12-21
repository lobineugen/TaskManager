package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;
import com.lobin.eugene.model.Model;
import org.omg.CORBA.TRANSACTION_MODE;

import javax.jws.soap.SOAPBinding;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {
    private Model model;
    private View view;
    private GUIController guiController;

    private MainController(final Model m, final View v, GUIController g) {
        this.model = m;
        this.view = v;
        this.guiController = g;
        this.view.getCreateTaskPanel().addCreateTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getCreateTaskPanel().getTaskType()) {
                    String title = view.getCreateTaskPanel().getTaskTitleTwo().getText();
                    Date start = (Date) view.getCreateTaskPanel().getStartTime().getValue();
                    Date end = (Date) view.getCreateTaskPanel().getEndTIme().getValue();
                    int interval = Integer.parseInt(view.getCreateTaskPanel().getInterval().getText());
                    boolean active = view.getCreateTaskPanel().getActiveTwo().isSelected();
                    model.createTask(title, start, end, interval, active);
                } else {
                    String title = view.getCreateTaskPanel().getTaskTitleOne().getText();
                    Date date = (Date) view.getCreateTaskPanel().getDateTime().getValue();
                    boolean active = view.getCreateTaskPanel().getActiveOne().isSelected();
                    model.createTask(title, date, active);
                }
            }
        });
    }

    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        GUIController guiController = new GUIController(view);
        MainController controller = new MainController(model, view, guiController);
        view.setVisible(true);
    }
}
