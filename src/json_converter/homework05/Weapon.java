package json_converter.homework05;


@JsonEntity
public class Weapon {

    @JsonField
    private String type;

    @JsonField
    private int power;

    @JsonField
    private Modification[] mods;

    public Weapon(String type, int power, Modification[] mods) {
        this.type = type;
        this.power = power;
        this.mods = mods;
    }
}
