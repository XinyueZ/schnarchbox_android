
package de.jw.mymensa.mensa.data.entity;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Advices {

    @SerializedName("cansleeplonger")
    @Expose
    private Boolean cansleeplonger;

    /**
     * 
     * @return
     *     The cansleeplonger
     */
    public Boolean getCansleeplonger() {
        return cansleeplonger;
    }

    /**
     * 
     * @param cansleeplonger
     *     The cansleeplonger
     */
    public void setCansleeplonger(Boolean cansleeplonger) {
        this.cansleeplonger = cansleeplonger;
    }

}
