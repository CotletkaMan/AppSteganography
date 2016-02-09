import java.util.BitSet;

/**
 * Created by cotletkaman on 28.01.16.
 */
public class test {
    public static void main(String[] argv){
        byte a = -1;
        BitSet set = BitSet.valueOf(new byte[]{(byte)(a)});
        for(int i = 0 ; i < set.length() ; i++)
            System.out.print(set.get(i) ? 1 : 0);
    }
}
