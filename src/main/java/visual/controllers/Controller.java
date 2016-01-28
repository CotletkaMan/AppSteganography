package visual.controllers;

import visual.BottomPanel;
import visual.controllers.control.ConfigControl;
import visual.controllers.control.PackControl;
import visual.controllers.control.UnPackControl;

import javax.swing.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class Controller implements ButtonActioner {

    private JTabbedPane tabbedPane;
    private BottomPanel bottomPanel;

    public Controller(JTabbedPane tabbedPane , BottomPanel bottomPanel){
        this.tabbedPane = tabbedPane;
        this.bottomPanel = bottomPanel;
    }

    public void run(){
        switch (tabbedPane.getSelectedIndex()){
            case 0:
                new PackControl().action(tabbedPane.getSelectedComponent() , bottomPanel.getSelected());
                break;
            case 1:
                new UnPackControl().action(tabbedPane.getSelectedComponent() , bottomPanel.getSelected());
                break;
            case 2:
                new ConfigControl().action(tabbedPane.getSelectedComponent() , bottomPanel.getSelected());
                break;
        }
    }

}
