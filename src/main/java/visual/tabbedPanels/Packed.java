package visual.tabbedPanels;

import javax.swing.*;

/**
 * Created by cotletkaman on 26.01.16.
 */
public class Packed extends JPanel implements Producer {
    private OpeningFile conteiner;
    private OpeningFile hidingFile;
    private OpeningFile destination;

    public Packed(){
        initialize();
        configuration();
    }

    private void initialize(){
        conteiner = new OpeningFile("Контейнер");
        hidingFile = new OpeningFile("Документ");
        destination = new OpeningFile("Место назначения");
    }

    private void configuration(){
        this.setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.add(conteiner);
        this.add(hidingFile);
        this.add(destination);
    }

    public String[] getAttributes(){
        return new String[]{conteiner.getText() , hidingFile.getText() , destination.getText()};
    }
}
