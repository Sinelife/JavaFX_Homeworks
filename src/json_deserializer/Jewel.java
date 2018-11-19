package json_deserializer;


import java.util.Arrays;

public class Jewel {

    private String type;
    private String ability;
    private int[] numbers;

    public Jewel(String type, String ability, int[] numbers) {
        this.type = type;
        this.ability = ability;
        this.numbers = numbers;
    }

    public Jewel() {}

    @Override
    public String toString() {
        return "Jewel{" +
                "type='" + type + '\'' +
                ", ability='" + ability + '\'' +
                ", numbers=" + Arrays.toString(numbers) +
                '}';
    }
}
