package json_converter.homework05;

@JsonEntity
public class Modification {
    @JsonField
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
