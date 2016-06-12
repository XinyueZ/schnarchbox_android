
package de.jw.mymensa.mensa.data.entity;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Untis {

    @SerializedName("advices")
    @Expose
    private Advices advices;

    /**
     * 
     * @return
     *     The advices
     */
    public Advices getAdvices() {
        return advices;
    }

    /**
     * 
     * @param advices
     *     The advices
     */
    public void setAdvices(Advices advices) {
        this.advices = advices;
    }

}
