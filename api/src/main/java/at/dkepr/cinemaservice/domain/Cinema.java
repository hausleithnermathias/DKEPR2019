package at.dkepr.cinemaservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "Kino", description = "Kino", discriminator = "loB")
public class Cinema {

    @ApiModelProperty(required = false, value = "Name des Kinos")
    @JsonProperty("Kino")
    private String name;

    @ApiModelProperty(required = false, value = "Kinoprogramm")
    @JsonProperty("Filme")
    private List<Movie> movies;

}
