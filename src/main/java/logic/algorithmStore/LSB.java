package logic.algorithmStore;

import javafx.util.Pair;
import logic.algorithms.ConfigurableAlgorithm;
import logic.algorithms.EndedBufferedBitInput;
import logic.algorithms.EndedBufferedBitOutput;
import logic.bitUtils.BufferedBitInput;
import logic.bitUtils.BufferedBitOutput;
import logic.conteiner.Conteiner;
import logic.conteiner.TypeBlocks;
import logic.conteiner.TypesFiles;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by cotletkaman on 25.01.16.
 */
public class LSB extends ConfigurableAlgorithm {
    private int countBit = 2;
    private BufferedBitInput bitInput;
    private BufferedBitOutput bitOutput;
    private BufferedOutputStream outputStream;
    private Iterator<Pair<TypeBlocks, byte[]>> blocksIterator;
    private boolean initKey = false;

    public LSB(){
        nameAlgorithm = "LSB";
    }

    public LSB setConteiner(Conteiner conteiner){
        if(conteiner != null && conteiner.getTypesFiles() != TypesFiles.WAV) {
            LOG.error("Ошибка в контейнере");
            throw new IllegalArgumentException();
        }
        super.conteiner = conteiner;
        return this;
    }

    public void read(){
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(getPath()));
            countBit = Integer.valueOf(properties.getProperty("COUNT_BIT"));
            codeSplit = Byte.valueOf(properties.getProperty("CODE_SPLIT"));
            if(countBit < 0 && countBit > 8)
                throw new IOException();
        }
        catch (IOException e){
            LOG.info("LSB : Error in property file");
        }
    }

    protected int directTransform(){
        try {
            if(!initKey){
                read();
                bitInput = new EndedBufferedBitInput(new FileInputStream(hidingFile), codeSplit);
                outputStream = new BufferedOutputStream(new FileOutputStream(destinationPath + "/" + conteiner.getName()));
                blocksIterator = conteiner.iterator();
                initKey = true;
            }
           if(blocksIterator.hasNext()){
               Pair<TypeBlocks , byte[]> block = blocksIterator.next();
               switch (block.getKey()){
                   case MAIN:
                       BitSet bitSet = new BitSet(countBit);
                       int length = bitInput.read(bitSet , countBit);
                       block.getValue()[block.getValue().length - 1] &= ((byte)(-1 << length));
                       byte[] data = bitSet.toByteArray();
                       if(data.length != 0) {
                           block.getValue()[block.getValue().length - 1] |= data[0];
                       }
                       break;
               }
               outputStream.write(block.getValue());
               return block.getValue().length;
           }
           else{
               BitSet bitSet = new BitSet(1);
               if(bitInput.read(bitSet , 1) > 0)
                   LOG.info("Контейнер не достаточной длины. Записана не вся информация");
               bitInput.close();
               outputStream.close();
               initKey = false;
               write();
           }
        }
        catch (IOException e){
            LOG.info("LSB: Error in pack document");
        }
        return 0;
    }

    protected int backTransform(){
        try {
            if(!initKey){
                read();
                bitOutput = new EndedBufferedBitOutput(new FileOutputStream(destinationPath + "/" + conteiner.getName().split("[.]")[0]) , codeSplit);
                blocksIterator = conteiner.iterator();
                initKey = true;
            }
            if(blocksIterator.hasNext()){
                Pair<TypeBlocks, byte[]> block = blocksIterator.next();
                switch (block.getKey()) {
                    case MAIN:
                        BitSet bitSet = BitSet.valueOf(new byte[]{block.getValue()[block.getValue().length - 1]});
                        if(bitOutput.write(bitSet , countBit) == 0){
                            bitOutput.close();
                            initKey = false;
                            return 0;
                        }
                        break;
                }
                return block.getValue().length;
            }
            else{
                bitOutput.close();
                initKey = false;
            }
        }
        catch(IOException e){
            LOG.info("LSB: Error in unpack document");
        }
        return 0;
    }
}
