package json_deserializer;



public class Jewel {

    private String type;

    private String ability;

    public Jewel(String type, String ability) {
        this.type = type;
        this.ability = ability;
    }

    public Jewel(){}

    @Override
    public String toString() {
        return "Jewel{" +
                "type='" + type + '\'' +
                ", ability='" + ability + '\'' +
                '}';
    }
}
