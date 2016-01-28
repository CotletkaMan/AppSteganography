package logic.conteiner;

/**
 * Created by cotletkaman on 16.01.16.
 */
public enum TypesFiles {
    ANOTHER{
        @Override
        public String getType() {
            return "";
        }
    },WAV{
        @Override
        public String getType() {
            return "wav";
        }
    };

    abstract public String getType();
}
