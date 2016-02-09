package logic.algorithms;

import logic.bitUtils.BufferedBitOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;

/**
 * Created by cotletkaman on 28.01.16.
 */
public class EndedBufferedBitOutput extends BufferedBitOutput {
    private byte endCode;
    private int countWritten = 0;

    public EndedBufferedBitOutput(OutputStream bitOutput , byte endCode) throws IOException{
        super(bitOutput);
        this.endCode = endCode;
    }

    public int write(BitSet set , int count) throws IOException{
        int length = super.write(set , count);
        if(countWritten != pos / 8){
            int lastPos = (countWritten + 1) * 8;
            byte[] syms = bitSet.get(lastPos - 8 , lastPos).toByteArray();
            if(syms.length > 0 && syms[0] == endCode) {
                BitSet afterSplitSym = bitSet.get(lastPos , pos);
                bitSet = bitSet.get(0, lastPos - 8);
                super.write(afterSplitSym , pos - lastPos);
                flush();
                return 0;
            }
            countWritten++;
        }
        return length;
    }
}
