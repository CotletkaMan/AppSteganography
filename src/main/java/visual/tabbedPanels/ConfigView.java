package visual.tabbedPanels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class ConfigView extends JTextArea implements Consumer{
    private static Logger log = LogManager.getLogger();
    private int capacity = 1024;
    private String path = "";

    public ConfigView(){
        this.setEditable(true);
        this.setEnabled(true);
    }

    public void takeAttributes(String[] attrs){
        if(!path.isEmpty())
            write();
        read(attrs[0]);
        path = attrs[0];
    }

    private void read(String path){
        try {
            setText("");
            StringBuffer buffer = new StringBuffer();
            char[] text = new char[capacity];

            BufferedReader reader = new BufferedReader(new FileReader(path));
            while (reader.read(text) > 0)
                buffer.append(text);

            reader.close();
            this.setText(buffer.toString());
        }
        catch(Exception e){
            log.info("Ошибка чтения лога");
        }
    }

    private void write(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(getText().trim());
            writer.close();
        }
        catch(Exception e){
            log.info("Ошибка записи лога");
        }
    }
}
