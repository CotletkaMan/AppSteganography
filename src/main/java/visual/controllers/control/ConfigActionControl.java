package visual.controllers.control;

import logic.algorithms.Algorithm;
import logic.algorithms.ConfigurableAlgorithm;
import visual.tabbedPanels.Consumer;

import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public class ConfigActionControl extends ActionControl {
    public void runAlgorithm(Component component , Algorithm algorithm){
        this.setMinimum(0);
        this.setMaximum(100);
        Consumer consumer = (Consumer)component;
        if(algorithm instanceof ConfigurableAlgorithm) {
            ConfigurableAlgorithm configurableAlgorithm = (ConfigurableAlgorithm)algorithm;
            consumer.takeAttributes(new String[]{configurableAlgorithm.getPath()});
        }
        this.setValue(100);
    }
}
