package at.dkepr.cinemaservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "film", description = "Film", discriminator = "loB")
public class Movie {

    public Movie() {
        reservations = Maps.newHashMap();
        reservations.put(WeekDayEnum.Mo, Lists.newArrayList());
        reservations.put(WeekDayEnum.Di, Lists.newArrayList());
        reservations.put(WeekDayEnum.Mi, Lists.newArrayList());
        reservations.put(WeekDayEnum.Do, Lists.newArrayList());
        reservations.put(WeekDayEnum.Fr, Lists.newArrayList());
        reservations.put(WeekDayEnum.Sa, Lists.newArrayList());
        reservations.put(WeekDayEnum.So, Lists.newArrayList());

    }

    @ApiModelProperty(required = false, value = "Filmtitel")
    @JsonProperty("titel")
    private String title;

    @ApiModelProperty(required = false, value = "Altersfreigabe")
    @JsonProperty("altersfreigabe")
    private String ageRating;

    @ApiModelProperty(required = false, value = "Sprache des Films")
    @JsonProperty("sprache")
    private String language;

    @ApiModelProperty(required = false, value = "Dauer des Films")
    @JsonProperty("spieldauer")
    private String movieLength;

    @ApiModelProperty(required = false, value = "Ort und Erscheinungsjahr")
    @JsonProperty("country")
    private String country;

    @ApiModelProperty(required = false, value = "Genre")
    @JsonProperty("genre")
    private String genre;

    @ApiModelProperty(required = false, value = "Tage, an denen der Film gespielt wird")
    @JsonProperty("spieltage")
    private List<WeekDayEnum> daysPlayed;

    @ApiModelProperty(required = false, value = "Reservierungen pro Tag")
    @JsonProperty("reservierungen")
    private Map<WeekDayEnum, List<String>> reservations;

    @ApiModelProperty(required = false, value = "Schauspieler")
    @JsonProperty("schauspieler")
    private List<String> actors;

    @ApiModelProperty(required = false, value = "Filmproduzent")
    @JsonProperty("filmproduzent")
    private List<String> producer;

    public List<WeekDayEnum> getDaysPlayed() {
        if (daysPlayed == null) {
            daysPlayed = Lists.newArrayList();
        }
        return daysPlayed;
    }

    public Map<WeekDayEnum, List<String>> getReservations() {
        return reservations;
    }

    public List<String> getActors() {
        if (actors == null) {
            actors = Lists.newArrayList();
        }
        return actors;
    }

    public List<String> getProducer() {
        if (producer == null) {
            producer = Lists.newArrayList();
        }
        return producer;
    }
}
