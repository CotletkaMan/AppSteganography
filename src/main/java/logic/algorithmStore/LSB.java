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
import java.util.Properties;

/**
 * Created by cotletkaman on 25.01.16.
 */
public class LSB extends ConfigurableAlgorithm {
    private int countBit = 2;

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

    protected void directTransform(){
        read();
        try {
            BufferedBitInput bitInput = new EndedBufferedBitInput(new FileInputStream(hidingFile) , codeSplit);
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationPath + "/" + conteiner.getName()));
            for(Pair<TypeBlocks, byte[]> block: conteiner){
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
            }
            BitSet bitSet = new BitSet(1);
            if(bitInput.read(bitSet , 1) > 0) {
                LOG.info("Контейнер не достаточной длины. Записана не вся информация");
            }
            bitInput.close();
            outputStream.close();
        }
        catch (IOException e){
            LOG.info("LSB: Error in pack document");
        }
        write();
    }

    protected void backTransform(){
        read();
        try {
            BufferedBitOutput outputStream = new EndedBufferedBitOutput(new FileOutputStream(destinationPath + "/" + conteiner.getName().split("[.]")[0]) , codeSplit);
        End:for (Pair<TypeBlocks, byte[]> block : conteiner) {
                switch (block.getKey()) {
                    case MAIN:
                        BitSet bitSet = BitSet.valueOf(new byte[]{block.getValue()[block.getValue().length - 1]});
                        if(outputStream.write(bitSet , countBit) == 0)
                            break End;
                        break;
                }
            }
            outputStream.close();
        }
        catch(IOException e){
            LOG.info("LSB: Error in unpack document");
        }
    }
}
