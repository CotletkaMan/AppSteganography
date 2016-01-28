package visual.controllers.control;

import logic.algorithms.Algorithm;
import logic.conteiner.fabricConteiner;
import visual.tabbedPanels.Producer;
import java.io.File;
import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class PackControl extends Control {
    public void action(Component component , Algorithm algorithm){
        Producer producer = (Producer)component;
        String[] attrs = producer.getAttributes();
        algorithm.setConteiner(fabricConteiner.getConteiner(attrs[0])).setDestinationPath(attrs[2]).setHidingFile(new File(attrs[1])).hide();
    }
}
