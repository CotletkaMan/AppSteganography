package logic.bitUtils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;

/**
 * Created by cotletkaman on 23.01.16.
 */
public class BufferedBitOutput extends BufferedOutputStream {
    protected final int MAX_BUFFERED_SIZE = 8192;

    protected BitSet bitSet;
    protected int pos;


    public BufferedBitOutput(OutputStream stream){
        super(stream);
        bitSet = new BitSet(MAX_BUFFERED_SIZE);
        pos = 0;
    }

    private void flushBitBuffer() throws IOException {
        byte[] array = bitSet.get(0 , pos).toByteArray();
        super.write(array);
        pos = 0;
    }

    public int write(BitSet bitSets , int count) throws IOException{
        int bitWritten = 0;
        for(; bitWritten < count; bitWritten++){
            if(pos < bitSet.size()) {
                bitSet.set(pos, bitSets.get(bitWritten));
                pos++;
            }
            else
                flushBitBuffer();
        }
        return bitWritten;
    }

    public synchronized void flush() throws IOException {
        flushBitBuffer();
        super.flush();
    }
}

