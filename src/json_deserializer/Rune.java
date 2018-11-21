package json_deserializer;

public class Rune {

    String name;

    Rune(){}

    public Rune(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rune{" +
                "name='" + name + '\'' +
                '}';
    }
}
