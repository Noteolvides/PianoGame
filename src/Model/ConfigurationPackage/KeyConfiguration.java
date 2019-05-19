package Model.ConfigurationPackage;

/**
 * This class contains the key Configuration of one key
 *
 * @version 1.0
 * @since 2019-05-16
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class KeyConfiguration {
    private String name;
    char key;

    /**
     *
     * @param nameKey The name that this is going to be like firstOctave
     * @param i The key that it is going to be
     */
    public KeyConfiguration(String nameKey, char i) {
        this.name = nameKey;
        key = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }
}
