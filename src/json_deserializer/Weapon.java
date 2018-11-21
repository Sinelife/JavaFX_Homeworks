package json_deserializer;


import java.util.Arrays;

public class Weapon {

    private String type;
    private int power;
    private Rune[] runes;

    public Weapon(String type, int power, Rune[] runes) {
        this.type = type;
        this.power = power;
        this.runes = runes;
    }

    public Weapon(){}

    @Override
    public String toString() {
        return "Weapon{" +
                "type='" + type + '\'' +
                ", power=" + power +
                ", runes=" + Arrays.toString(runes) +
                '}';
    }
}
