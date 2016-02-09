package visual.controllers.control;

import logic.algorithms.Algorithm;
import logic.conteiner.fabricConteiner;
import visual.tabbedPanels.Producer;

import java.io.File;
import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class PackActionControl extends ActionControl {
    public void runAlgorithm(Component component , final Algorithm algorithm){
        Producer producer = (Producer)component;
        String[] attrs = producer.getAttributes();
        algorithm.setConteiner(fabricConteiner.getConteiner(attrs[0])).setDestinationPath(attrs[2]).setHidingFile(new File(attrs[1]));
        this.setMinimum(0);
        int maxSize = (int)algorithm.getConteiner().getFile().length();
        this.setMaximum(maxSize);
        for(int i = 0 ; algorithm.hide() > 0 ; i++){
            if((i + 1) % this.freqency == 0)
                update(algorithm.getCountBitRead());
        }
    }
    private void update(int value){
        this.setValue(value);
    }
}
