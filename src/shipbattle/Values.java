package shipbattle;

import java.util.LinkedList;
import java.util.List;

/**
 * Клас необходимый для хранения координат кораблей и игрока и компьютера, а
 * также количество кораблей обоих игроков
 */

public class Values {

    public static List<String> listOfYourShipsCoordinates = new LinkedList();
    public static List<String> listOfEnemyShipsCoordinates = new LinkedList();
    public static int playerShipNum = MainApp.SHIP_NUM;
    public static int compShipNum = MainApp.SHIP_NUM;
}
