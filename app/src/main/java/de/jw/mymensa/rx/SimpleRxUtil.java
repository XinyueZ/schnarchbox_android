package de.jw.mymensa.rx;

/**
 * Created by JW on 19.05.2016.
 */
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by janvancoppenolle on 25/08/15.
 */
public class SimpleRxUtil {
    public static class IOTransformer<T> implements Observable.Transformer<T, T> {
        @Override public Observable<T> call(Observable<T> observable) {
            return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }
}
