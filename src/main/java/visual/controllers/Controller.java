package visual.controllers;

import visual.BottomPanel;
import visual.controllers.control.ConfigActionControl;
import visual.controllers.control.ActionControl;
import visual.controllers.control.PackActionControl;
import visual.controllers.control.UnPackActionControl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class Controller extends JPanel implements ButtonActioner {

    private JTabbedPane tabbedPane;
    private BottomPanel bottomPanel;
    private ActionControl actionControl;

    public Controller(JTabbedPane tabbedPane , BottomPanel bottomPanel){
        this.tabbedPane = tabbedPane;
        this.bottomPanel = bottomPanel;
        this.setLayout(new BorderLayout());
    }

    public void run(){
        switch (tabbedPane.getSelectedIndex()){
            case 0:
                actionControl = new PackActionControl();
                break;
            case 1:
                actionControl = new UnPackActionControl();
                break;
            case 2:
                actionControl = new ConfigActionControl();
                break;
        }
        this.add(actionControl , BorderLayout.CENTER);
        this.revalidate();
        actionControl.runAlgorithm(tabbedPane.getSelectedComponent() , bottomPanel.getSelected());
    }

}
