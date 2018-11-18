package json_deserializer;


public class Weapon {

    private String type;

    private int power;

    public Weapon(String type, int power) {
        this.type = type;
        this.power = power;
    }

    public Weapon(){}

    @Override
    public String toString() {
        return "Weapon{" +
                "type='" + type + '\'' +
                ", power=" + power +
                '}';
    }
}
