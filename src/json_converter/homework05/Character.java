package json_converter.homework05;

@JsonEntity()
public class Character {
    @JsonField
    private String name;

    @JsonField
    private String race;

    @JsonField
    private int age;

    @JsonField
    private boolean isAlive;

    private String clanName;

    @JsonField
    private Weapon[] weapons;

    @JsonField
    private Jewel jewel;

    @JsonField
    private String[] psevdonims;


    public Character(String name, String race, int age, boolean isAlive, String clanName, Weapon[] weapons, Jewel jewel, String[] psevdonims) {
        this.name = name;
        this.race = race;
        this.age = age;
        this.isAlive = isAlive;
        this.clanName = clanName;
        this.weapons = weapons;
        this.jewel = jewel;
        this.psevdonims = psevdonims;
    }
}