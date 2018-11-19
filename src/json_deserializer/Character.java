package json_deserializer;


import java.util.Arrays;

public class Character {

    private String name;

    private String race;

    private int age;

    private boolean isAlive;

    private String clanName;

    private Weapon[] weapons;

    private Jewel jewel;

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

    public Character() {

    }

    @Override
    public String toString() {
        return "Character{" + "\n" +
                "name='" + name + '\'' + "\n" +
                ", race='" + race + '\'' + "\n" +
                ", age=" + age + "\n" +
                ", isAlive=" + isAlive + "\n" +
                ", clanName='" + clanName + '\'' + "\n" +
                ", weapons=" + Arrays.toString(weapons) + "\n" +
                ", jewel=" + jewel + "\n" +
                ", psevdonims=" + Arrays.toString(psevdonims) + "\n" +
                '}';
    }
}