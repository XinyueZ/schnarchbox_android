package de.jw.mymensa.mensa.data.service;

import de.jw.mymensa.mensa.data.entity.AdviceModel;
import de.jw.mymensa.mensa.data.entity.Untis;
import de.jw.mymensa.mensa.data.entity.Weather;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by JW on 11.06.2016.
 */

public interface AdviceAPI {

    String URI =  "http://172.16.108.120:3001/advice/";//"http://mensahome.de";

    @GET("/untis/{school}/{user}/{password}/{klasse}")
    Observable<Untis> getUntisRx(@Path("school") String school, @Path("user") String user, @Path("password") String password, @Path("klasse") String klasse);

    @GET("/weather/{location}")
    Observable<Weather> getWeatherRx();

    @GET("/all/{location}/{school}/{user}/{password}/{klasse}")
    Observable<AdviceModel> getAdvicesRx(@Path("location") String location, @Path("school") String school, @Path("user") String user, @Path("password") String password, @Path("klasse") String klasse);

}
