package visual.tabbedPanels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by cotletkaman on 26.01.16.
 */
public class OpeningFile extends JPanel {
    private JFileChooser chooser;
    private JTextField textField;

    private JButton findFile;
    private JButton openButton;

    private TitledBorder titledBorder;

    public OpeningFile(String name){
        initialize(name);
        configuration();
        addActions();
    }

    private void initialize(String name){
        titledBorder = BorderFactory.createTitledBorder(name);
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        textField = new JTextField();
        findFile = new JButton("...");
        openButton = new JButton("Open");
    }

    private void configuration(){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(findFile);
        this.add(textField);
        this.add(openButton);
        this.setBorder(titledBorder);
    }

    private void addActions(){
        findFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(chooser.showDialog(null , "Открытие файла") == JFileChooser.APPROVE_OPTION);
                    textField.setText(chooser.getSelectedFile().getPath());
            }
        });

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = new File(textField.getText());
                if(file.exists()){
                    Desktop desktop = Desktop.getDesktop();
                    try{
                        desktop.open(file);
                    }
                    catch(IOException f){
                        System.out.println(f.getMessage());
                    }
                }
            }
        });
    }

    public File getFile(){
        return new File(textField.getText());
    }
    public String getText() { return textField.getText();}
}
