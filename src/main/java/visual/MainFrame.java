package visual;

import logic.algorithmStore.LSB;
import logic.algorithms.Algorithm;
import visual.controllers.Controller;
import visual.tabbedPanels.ConfigView;
import visual.tabbedPanels.LogView;
import visual.tabbedPanels.Packed;
import visual.tabbedPanels.UnPacked;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cotletkaman on 26.01.16.
 */
public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private BottomPanel bottomPanel;


    public MainFrame(int width , int height){
        initialize();
        configuration();

        this.setSize(width , height);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initialize() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Pack", new Packed());
        tabbedPane.addTab("UnPack", new UnPacked());
        tabbedPane.addTab("Config" , new ConfigView());
        tabbedPane.addTab("Log" , new LogView("logs/app.log"));

        ArrayList<Algorithm> list = new ArrayList<Algorithm>();
        list.add(new LSB());

        bottomPanel = new BottomPanel(list);
        bottomPanel.setAction(new Controller(tabbedPane , bottomPanel));
    }

    private void configuration(){
        add(tabbedPane , BorderLayout.CENTER);
        add(bottomPanel , BorderLayout.SOUTH);
    }
}
