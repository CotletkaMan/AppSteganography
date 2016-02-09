package visual.tabbedPanels;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class LogView extends JTextArea {
    private String pathToLog;
    private long lengthReaded;
    private int countStrings = 10;
    private Queue<String> queueStrings;

    public LogView(String pathToLog){
        this.pathToLog = pathToLog;
        lengthReaded = 0;
        queueStrings = new ArrayDeque<String>(countStrings);
        addAction();
    }

    private void addAction(){
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) {
                read();
            }

            public void ancestorRemoved(AncestorEvent event) {

            }

            public void ancestorMoved(AncestorEvent event) {

            }
        });
    }

    private void read(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToLog));
            reader.skip(lengthReaded);
            String text;
            while((text = reader.readLine()) != null){
                queueStrings.add(text);
                if(queueStrings.size() > countStrings)
                    queueStrings.poll();
                lengthReaded += text.length();
            }
            for(String line : queueStrings)
                text += line + "\n";
            this.setText(text);
            reader.close();
        }
        catch(Exception e){
            throw new RuntimeException("Не найден файл логирования");
        }
    }
}
