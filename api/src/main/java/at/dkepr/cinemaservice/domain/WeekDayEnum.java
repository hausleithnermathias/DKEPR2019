package at.dkepr.cinemaservice.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum WeekDayEnum {

    Mo("Montag"),
    Di("Dienstag"),
    Mi("Mittwoch"),
    Do("Donnerstag"),
    Fr("Freitag"),
    Sa("Samstag"),
    So("Sonntag");

    private String key;
    private String value;


    WeekDayEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
