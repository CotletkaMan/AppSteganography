package logic.conteiner;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by cotletkaman on 16.01.16.
 */
public class UnknownConteiner extends Conteiner {
    UnknownConteiner(File file) throws IOException{
        super(file);
        typesFiles = TypesFiles.ANOTHER;
        readFile();
    }

    private Pair<TypeBlocks , byte[]> readFile(){
        try {
            byte[] data = new byte[1];
            if(inputStream.read(data) > 0)
                return new Pair<TypeBlocks , byte[]>(TypeBlocks.COMMON, data);
            else {
                openingFlag = false;
                close();
                return new Pair<TypeBlocks , byte[]>(TypeBlocks.COMMON , new byte[0]);
            }
        }
        catch (IOException e){
            return new Pair<TypeBlocks , byte[]>(TypeBlocks.COMMON , new byte[0]);
        }
    }

    public Iterator<Pair<TypeBlocks , byte[]>> iterator(){
        return new Iterator<Pair<TypeBlocks, byte[]>>() {
            public boolean hasNext() {
                return openingFlag;
            }

            public Pair<TypeBlocks, byte[]> next() {
                return readFile();
            }
        };
    }
}
