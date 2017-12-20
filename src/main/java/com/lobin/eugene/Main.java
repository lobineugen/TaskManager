package com.lobin.eugene;

import com.lobin.eugene.Controller.Controller;
import com.lobin.eugene.View.View;
import com.lobin.eugene.model.Model;

public class Main {
    public static void main(String[] args) {
        View theView = new View();
        Model model = new Model();
        Controller controller = new Controller(model, theView);
        theView.setVisible(true);
    }
}
