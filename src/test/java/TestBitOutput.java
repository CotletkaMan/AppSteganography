import logic.bitUtils.BufferedBitOutput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.BitSet;

/**
 * Created by cotletkaman on 25.01.16.
 */
public class TestBitOutput {
    private String path = "target/test-classes/testBitOutput";
    private BitSet bitSet;
    private byte[] testData = new byte[] {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};

    @Before
    public void beginTestBitOutput() throws IOException{
        bitSet = BitSet.valueOf(testData);
    }

    @Test
    public void testBitOuput() throws IOException{
        BufferedBitOutput output = new BufferedBitOutput(new FileOutputStream(path));
        output.write(bitSet , bitSet.length());
        output.close();

        FileInputStream input = new FileInputStream(path);
        byte[] array = new byte[testData.length];
        input.read(array);
        Assert.assertArrayEquals(array , testData);
        input.close();
    }
}
