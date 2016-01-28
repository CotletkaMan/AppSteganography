package logic.bitUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

/**
 * Created by cotletkaman on 22.01.16.
 */
public class BufferedBitInput extends BufferedInputStream {
    private final int MAX_BUFFERED_SIZE = 8192;

    private BitSet bitSet;
    private int pos;
    private int lastIndex;


    public BufferedBitInput(InputStream stream) throws IOException{
        super(stream);
        bitSet = new BitSet(MAX_BUFFERED_SIZE);
        fill();
    }

    private void fill() throws IOException{
        byte[] array = new byte[bitSet.size()];
        int count = read(array);
        bitSet = BitSet.valueOf(array);
        lastIndex = count * 8;
        pos = 0;
    }

    public int read(BitSet bitSets , int length) throws IOException{
        int bitReaded = 0;
        while(bitReaded < length && lastIndex > 0){
            if(pos < lastIndex) {
                bitReaded += (lastIndex - pos) > length ? length : lastIndex - pos;
                bitSets.or(bitSet.get(pos, pos + bitReaded));
                pos += bitReaded;
            }
            else
                fill();
        }
        return bitReaded;
    }


}
