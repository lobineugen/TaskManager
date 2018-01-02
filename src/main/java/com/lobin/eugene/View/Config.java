package com.lobin.eugene.View;

import com.lobin.eugene.Controller.TaskConstant;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JSpinner;

/**
 * Class with config methods
 *
 * @author Eugene Lobin
 * @version 1.0 02 Jan 2018
 */
class Config implements TaskConstant {

    /**
     * @param panel for creating group layout
     * @return group layout with with configuration
     */
    static GroupLayout createGroupLayout(JPanel panel) {
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        return layout;
    }

    /**
     * Set date editor with special format
     *
     * @param spinner for which will be installed
     */
    static void setDateEditor(JSpinner spinner) {
        spinner.setEditor(new JSpinner.DateEditor(spinner,
                DATE_FORMAT.toPattern()));
    }
}
