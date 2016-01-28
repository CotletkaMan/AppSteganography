package visual.controllers.control;

import logic.algorithms.Algorithm;
import logic.algorithms.ConfigurableAlgorithm;
import visual.tabbedPanels.Consumer;

import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class ConfigControl extends Control {
    public void action(Component component , Algorithm algorithm){
        Consumer consumer = (Consumer)component;
        if(algorithm instanceof ConfigurableAlgorithm) {
            ConfigurableAlgorithm configurableAlgorithm = (ConfigurableAlgorithm)algorithm;
            String path = configurableAlgorithm.getPath();
            consumer.takeAttributes(new String[]{configurableAlgorithm.getPath()});
        }
    }
}
