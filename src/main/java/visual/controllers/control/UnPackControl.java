package visual.controllers.control;

import logic.algorithms.Algorithm;
import logic.conteiner.fabricConteiner;
import visual.tabbedPanels.Producer;

import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class UnPackControl extends Control {
    public void action(Component component , Algorithm algorithm){
        Producer producer = (Producer)component;
        String[] attrs = producer.getAttributes();
        algorithm.setConteiner(fabricConteiner.getConteiner(attrs[0])).setDestinationPath(attrs[1]).unhide();
    }
}
