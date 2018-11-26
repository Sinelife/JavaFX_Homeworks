package json_serializer_deserializer;

import json_converter.homework05.Character;
import json_converter.homework05.Jewel;
import json_converter.homework05.Rune;
import json_converter.homework05.Weapon;

public class DataContainer {
    static int[] numbers = {1,2,37,8};
    static int[] numbers2 = {10,20,30,40,50};
    static Rune[] runes1 = {new Rune("Эйн"),new Rune("Элледин")};
    static Rune[] runes2 = {new Rune("Вулканис"),new Rune("Астартес")};
    static Rune[] runes3 = {new Rune("Гольд"),new Rune("Серентин")};
    static Jewel jewel = new Jewel("кольцо", "защита",numbers);
    static Jewel jewel2 = new Jewel("амулет", "регенерация",numbers2);
    static Weapon[] weapons = {new Weapon("нож", 30, runes1), new Weapon("кинжал", 50, runes3), new Weapon("лук", 30, runes1)};
    static Weapon[] weapons2 = {new Weapon("меч", 200, runes2), new Weapon("перчатка", 150, runes3)};
    static String[] psevdonims1 = {"принцесса леса","владычица леса"};
    static String[] psevdonims2 = {"кровавый монстр","смертоносный"};
    public static Character[] characters = {new Character("Элейн","ельф",1000, true, "Элрис", weapons, jewel, psevdonims1),
            new Character("Кахат", "демон", 600, false, "Крайтос", weapons2, jewel2, psevdonims2)};

}
