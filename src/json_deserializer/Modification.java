package json_deserializer;

public class Modification {

    String value;

    public Modification(String value) {
        this.value = value;
    }
    public Modification(){}

    @Override
    public String toString() {
        return "Modification{" +
                "value='" + value + '\'' +
                '}';
    }
}
