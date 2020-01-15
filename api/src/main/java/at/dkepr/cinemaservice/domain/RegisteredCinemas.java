package at.dkepr.cinemaservice.domain;

public enum RegisteredCinemas {
    STARMOVIE("Starmovie"),
    GEIL("Geil"),
    MEGAPLEX("Megaplex");

    public String value;

    RegisteredCinemas(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
