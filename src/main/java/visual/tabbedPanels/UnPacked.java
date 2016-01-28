package visual.tabbedPanels;

import javax.swing.*;

/**
 * Created by cotletkaman on 26.01.16.
 */
public class UnPacked extends JPanel implements Producer{
    private OpeningFile conteiner;
    private OpeningFile destination;

    public UnPacked(){
        initialize();
        configuration();
    }

    private void initialize(){
        conteiner = new OpeningFile("Контейнер");
        destination = new OpeningFile("Место наначения");
    }

    private void configuration(){
        this.setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.add(conteiner);
        this.add(destination);
    }

    public String[] getAttributes(){
        return new String[]{conteiner.getText() , destination.getText()};
    }
}
