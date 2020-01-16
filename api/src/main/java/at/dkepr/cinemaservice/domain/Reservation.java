package at.dkepr.cinemaservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "reservierung", description = "Reservierung", discriminator = "loB")
public class Reservation {

    public Reservation(String name, boolean menu, String day) {
        this.menu = menu;
        this.name = name;
        this.day = day;
    }

    @ApiModelProperty(required = false, value = "Reservierungsname")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(required = false, value = "Tag")
    @JsonProperty("tag")
    private String day;

    @ApiModelProperty(required = false, value = "Menü")
    @JsonProperty("menu")
    private boolean menu;

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public boolean getMenu() {
        return menu;
    }

}
