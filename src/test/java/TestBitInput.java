import logic.bitUtils.BufferedBitInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;

/**
 * Created by cotletkaman on 23.01.16.
 */

public class TestBitInput {
    private String pathTestFile = "target/test-classes/testBitInput";
    private BufferedBitInput bitInput;

    @Before
    public void beginBitInput() throws IOException{
        bitInput = new BufferedBitInput(new FileInputStream(pathTestFile));
    }

    @Test
    public void testBitInput() throws IOException {
        BitSet set = new BitSet(8 * 9);
        bitInput.read(set , 8 * 9);
        org.junit.Assert.assertArrayEquals(set.toByteArray() , new byte[]{'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'});
    }

    @After
    public void afterBitInput() throws IOException {
        bitInput.close();
    }
}
