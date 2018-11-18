package json_deserializer;


public class Character {

    private String name;

    private String race;

    private int age;

    private boolean isAlive;

    private String clanName;

    private Weapon weapons;

    private Jewel jewel;


    public Character(String name, String race, int age, boolean isAlive, String clanName, Weapon weapons, Jewel jewel) {
        this.name = name;
        this.race = race;
        this.age = age;
        this.isAlive = isAlive;
        this.clanName = clanName;
        this.weapons = weapons;
        this.jewel = jewel;
    }

    public Character() {

    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", age=" + age +
                ", isAlive=" + isAlive +
                ", clanName='" + clanName + '\'' +
                ", weapons=" + weapons +
                ", jewel=" + jewel +
                '}';
    }
}