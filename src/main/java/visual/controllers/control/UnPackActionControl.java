package visual.controllers.control;

import logic.algorithms.Algorithm;
import logic.conteiner.fabricConteiner;
import visual.tabbedPanels.Producer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class UnPackActionControl extends ActionControl {
    public void runAlgorithm(Component component , final Algorithm algorithm){
        Producer producer = (Producer)component;
        String[] attrs = producer.getAttributes();
        algorithm.setConteiner(fabricConteiner.getConteiner(attrs[0])).setDestinationPath(attrs[1]);
        this.setMinimum(0);
        int size = (int)algorithm.getConteiner().getFile().length();
        this.setMaximum(size);
        for(int i= 0; algorithm.unhide() != 0 ; i++) {
            if((i + 1) % this.freqency == 0) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        setValue(algorithm.getCountBitRead());
                        repaint();
                    }
                });
            }
        }
        this.setValue(size);
    }
}
