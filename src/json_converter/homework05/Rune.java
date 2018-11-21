package json_converter.homework05;

@JsonEntity
public class Rune {

    @JsonField
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
