
package de.jw.mymensa.mensa.data.entity;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Weather {

    @SerializedName("advicetext")
    @Expose
    private String advicetext;
    @SerializedName("code")
    @Expose
    private String code;

    /**
     * 
     * @return
     *     The advicetext
     */
    public String getAdvicetext() {
        return advicetext;
    }

    /**
     * 
     * @param advicetext
     *     The advicetext
     */
    public void setAdvicetext(String advicetext) {
        this.advicetext = advicetext;
    }

    /**
     * 
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(String code) {
        this.code = code;
    }

}
