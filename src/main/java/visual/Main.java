package visual;

import javax.swing.*;

/**
 * Created by cotletkaman on 26.01.16.
 */
public class Main {
    public static void main(String[] argv){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(600 , 400);
            }
        });
    }
}
