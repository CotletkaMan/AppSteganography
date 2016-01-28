package logic.conteiner;

import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cotletkaman on 16.01.16.
 */
public abstract class Conteiner implements Iterable<Pair<TypeBlocks , byte[]>>{
    protected TypesFiles typesFiles;
    protected File file;
    protected InputStream inputStream;
    protected boolean openingFlag;


    protected Conteiner(File file) throws IOException{
        this.file = file;
        inputStream = new FileInputStream(file);
        openingFlag = true;
    }

    public File getFile(){
        return file;
    }

    public TypesFiles getTypesFiles(){
        return typesFiles;
    }

    public void close() throws IOException{
        inputStream.close();
    }

    public String getName(){
        return file.getName();
    }

}
