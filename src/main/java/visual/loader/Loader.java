package visual.loader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.io.File;
/**
 * Created by cotletkaman on 26.01.16.
 */
public class Loader {
    private static Logger log = LogManager.getLogger();

    public static <T> ArrayList<T> loadClass(String path , Class<T> getClass){
        File dir = new File(path);
        File[] classes = dir.listFiles();

        ArrayList<T> listClasses = new ArrayList<T>();
        for(File localClass : classes) {
            try {
                T rawClass =  (T)Class.forName(localClass.getPath()).newInstance();
                listClasses.add(rawClass);
            }
            catch (Exception e){
                log.info("Not found class " + localClass.getPath() + " or error in loading");
            }
        }
        return listClasses;
    }
}
