package visual.controllers.control;

import logic.algorithms.Algorithm;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cotletkaman on 27.01.16.
 */
public abstract class ActionControl extends JProgressBar {
    protected int freqency = 10;

    public ActionControl(){
        setOrientation(VERTICAL);
    }
    public abstract void runAlgorithm(Component component , Algorithm algorithm);
}
