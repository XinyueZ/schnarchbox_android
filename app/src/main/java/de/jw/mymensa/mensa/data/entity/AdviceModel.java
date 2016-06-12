
package de.jw.mymensa.mensa.data.entity;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AdviceModel {

    @SerializedName("weather")
    @Expose
    private Weather weather;
    @SerializedName("untis")
    @Expose
    private Untis untis;

    /**
     * 
     * @return
     *     The weather
     */
    public Weather getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The untis
     */
    public Untis getUntis() {
        return untis;
    }

    /**
     * 
     * @param untis
     *     The untis
     */
    public void setUntis(Untis untis) {
        this.untis = untis;
    }

}
