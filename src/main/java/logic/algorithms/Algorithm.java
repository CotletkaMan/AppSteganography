package logic.algorithms;

import logic.conteiner.Conteiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
/**
 * Created by cotletkaman on 25.01.16.
 */
public abstract class Algorithm {
    protected static Logger LOG = LogManager.getLogger();
    protected Conteiner conteiner = null;
    protected File hidingFile = null;
    protected String destinationPath = null;
    protected byte codeSplit = 1;

    private int countBitRead = 0;

    protected String nameAlgorithm = null;

    public Algorithm setConteiner(Conteiner conteiner){
        this.conteiner = conteiner;
        return this;
    }

    public Conteiner getConteiner(){
        return conteiner;
    }

    public Algorithm setHidingFile(File file){
        hidingFile = file;
        return this;
    }

    public File getHidingFile(){
        return hidingFile;
    }

    public String getDestinationPath(){
        return destinationPath;
    }

    public Algorithm setDestinationPath(String destinationPath){
        this.destinationPath = destinationPath;
        return this;
    }

    public String getNameAlgorithm(){
        return nameAlgorithm;
    }

    private boolean checkConditions(){
        return (conteiner != null) && (hidingFile != null) && (destinationPath != null) && (hidingFile.exists());
    }

    abstract protected int directTransform();
    abstract protected int backTransform();

    public int hide(){
        if(checkConditions()) {
            int length = directTransform();
            countBitRead += length;
            return length;
        }
        else
            LOG.info("For this method not set begin conditions");
        return 0;
    }

    public int unhide(){
       return backTransform();
    }

    public int getCountBitRead(){
        return countBitRead;
    }

}
