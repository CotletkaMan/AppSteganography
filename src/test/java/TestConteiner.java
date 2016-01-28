import logic.conteiner.Conteiner;
import logic.conteiner.fabricConteiner;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cotletkaman on 27.01.16.
 */

public class TestConteiner {
    private String path = "target/test-classes/Conteiner.wav";


    public void testConteiner(){
        Conteiner conteiner = fabricConteiner.getConteiner(path);
        Assert.assertEquals(conteiner.getName() , "Conteiner.wav");
    }
}
