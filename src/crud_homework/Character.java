package crud_homework;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Класс-модель для адресата (Person).
 *
 * @author Marco Jakob
 */
public class Character {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty race;
    private final StringProperty specialization;
    private final StringProperty rank;
    private final IntegerProperty power;

    /**
     * Конструктор по умолчанию.
     */
    public Character() {
        this(null, null, null, null, null, 0);
    }


    /**
     * Конструктор для создание обьекта с заполненными полями
     */
    public Character(String firstName, String lastName, String race, String specialization, String rank, int power) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.race = new SimpleStringProperty(race);
        this.specialization = new SimpleStringProperty(specialization);
        this.rank = new SimpleStringProperty(rank);
        this.power = new SimpleIntegerProperty(power);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getRace() {
        return race.get();
    }

    public StringProperty raceProperty() {
        return race;
    }

    public void setRace(String race) {
        this.race.set(race);
    }

    public String getSpecialization() {
        return specialization.get();
    }

    public StringProperty specializationProperty() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization.set(specialization);
    }

    public String getRank() {
        return rank.get();
    }

    public StringProperty rankProperty() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank.set(rank);
    }

    public int getPower() {
        return power.get();
    }

    public IntegerProperty powerProperty() {
        return power;
    }

    public void setPower(int power) {
        this.power.set(power);
    }
}