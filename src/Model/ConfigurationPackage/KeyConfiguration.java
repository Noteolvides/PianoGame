package Model.ConfigurationPackage;

public class KeyConfiguration {
    private String name;
    char key;

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
