package visual.controllers.control;

import logic.algorithms.Algorithm;
import logic.conteiner.fabricConteiner;
import visual.tabbedPanels.Producer;

import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class UnPackActionControl extends ActionControl {
    public void runAlgorithm(Component component , Algorithm algorithm){
        Producer producer = (Producer)component;
        String[] attrs = producer.getAttributes();
        algorithm.setConteiner(fabricConteiner.getConteiner(attrs[0])).setDestinationPath(attrs[1]);
        this.setMinimum(0);
        this.setMaximum((int)algorithm.getConteiner().getFile().length());
        for(int length , countByte = 0 ; (length = algorithm.unhide()) > 0 ; countByte += length)
            this.setValue(countByte);
    }
}
