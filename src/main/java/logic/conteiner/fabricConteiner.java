package logic.conteiner;



import org.apache.logging.log4j.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by cotletkaman on 16.01.16.
 */
public class fabricConteiner {
    public static final Logger LOG= LogManager.getLogger();

    public static Conteiner getConteiner(String path){
        File file = new File(path);
        String[] splitName = file.getName().split("[.]");

        TypesFiles type = TypesFiles.ANOTHER;
        for(TypesFiles typesFiles : TypesFiles.values()){
            if(typesFiles.getType().equals(splitName[splitName.length - 1])){
                type = typesFiles;
                break;
            }
        }

        try {
            switch (type) {
                case WAV:
                    return new WavConteiner(file);
                default:
                    return new UnknownConteiner(file);
            }
        }
        catch (IOException e){
            LOG.info("Unsupported this extension or file not exist");
            return null;
        }
    }
}
