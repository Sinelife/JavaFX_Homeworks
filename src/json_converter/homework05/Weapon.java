package json_converter.homework05;


@JsonEntity
public class Weapon {

    @JsonField
    private String type;

    @JsonField
    private int power;

    @JsonField
    private Rune[] runes;


    public Weapon(String type, int power, Rune[] runes) {
        this.type = type;
        this.power = power;
        this.runes = runes;
    }
}
