package logic.algorithms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by cotletkaman on 25.01.16.
 */
public abstract class ConfigurableAlgorithm extends Algorithm implements Configurable{

    public String getPath(){
        return  "classes/configuration/" + nameAlgorithm + ".properties";
    }

    public void write(){
        try {
            Path source = Paths.get("classes/configuration/" + nameAlgorithm + ".properties");
            Path destination = Paths.get(destinationPath + "/" + hidingFile.getName() + ".properties");
            Files.copy(source, destination);
        }
        catch (IOException e){
            LOG.info("Error in copy configuration file");
        }
    }

}
