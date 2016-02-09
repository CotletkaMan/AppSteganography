package logic.conteiner;

import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by cotletkaman on 16.01.16.
 */
public class WavConteiner extends Conteiner{
    private int bitPerSample = 0;
    private boolean readHeader = true;

    public WavConteiner(File file) throws IOException{
        super(file);
        typesFiles = TypesFiles.WAV;
    }

    private Pair<TypeBlocks , byte[]> readFile(){
        try {
            byte[] data;
            if(readHeader) {
                data = new byte[64];
                inputStream.read(data);
                bitPerSample = (((int)data[35]) << 8) | (int)data[34];
                readHeader = false;
                return new Pair<TypeBlocks, byte[]>(TypeBlocks.COMMON, data);
            }
            else {
                data = new byte[bitPerSample / 8];
                if(inputStream.read(data) > 0)
                    return new Pair<TypeBlocks, byte[]>(TypeBlocks.MAIN, data);
                else {
                    openingFlag = false;
                    close();
                    return new Pair<TypeBlocks, byte[]>(TypeBlocks.COMMON , new byte[0]);
                }
            }
        }
        catch(IOException e){
            return new Pair<TypeBlocks, byte[]>(TypeBlocks.COMMON , new byte[0]);
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
