package json_converter.homework05;



@JsonEntity
public class Jewel {

    @JsonField
    private String type;

    @JsonField
    private String ability;

    @JsonField
    private int[] numbers;

    public Jewel(String type, String ability, int[] numbers) {
        this.type = type;
        this.ability = ability;
        this.numbers = numbers;
    }
}
