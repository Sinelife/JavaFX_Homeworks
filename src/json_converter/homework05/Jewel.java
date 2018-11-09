package json_converter.homework05;



@JsonEntity
public class Jewel {

    @JsonField(name = "jewel_type")
    private String type;

    @JsonField(name = "jewel_ability")
    private String ability;

    public Jewel(String type, String ability) {
        this.type = type;
        this.ability = ability;
    }
}
