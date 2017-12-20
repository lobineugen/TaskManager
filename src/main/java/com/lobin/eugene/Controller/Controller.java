package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;
import com.lobin.eugene.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View viewStart;

    public Controller(Model model, final View viewStart){
        this.model = model;
        this.viewStart = viewStart;
        this.viewStart.addCreateTaskListener(new AddCreateTaskListener());
        this.viewStart.addButtonBackListener(new AddButtonBackListener());
    }

    class AddCreateTaskListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            viewStart.getCardLayout().show(viewStart.getContainer(), "Create task");
        }
    }

    class AddButtonBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewStart.getCardLayout().show(viewStart.getContainer(), "Start form");
        }
    }
}
