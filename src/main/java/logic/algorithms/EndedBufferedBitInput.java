package logic.algorithms;

import logic.bitUtils.BufferedBitInput;

import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

/**
 * Created by cotletkaman on 28.01.16.
 */
public class EndedBufferedBitInput extends BufferedBitInput{
    private BitSet endCode;
    private int pos;

    public EndedBufferedBitInput(InputStream input , byte endCode) throws IOException{
        super(input);
        this.endCode = BitSet.valueOf(new byte[]{endCode});
        pos = 0;
    }

    public int read(BitSet set , int count) throws IOException{
        int length = super.read(set , count);
        if(length == 0 && pos < 8){
            length = (8 - pos) > count ? count : 8 - pos;
            set.or(endCode.get(pos , pos + length));
            pos += length;
        }
        return length;
    }


}
