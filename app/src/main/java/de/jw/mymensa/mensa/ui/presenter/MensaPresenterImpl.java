package de.jw.mymensa.mensa.ui.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import de.jw.mymensa.mensa.data.entity.AdviceModel;
import de.jw.mymensa.mensa.data.service.AdviceAPI;
import de.jw.mymensa.mensa.ui.view.mensa.MensaActivity;
import de.jw.mymensa.mensa.ui.view.mensa.MensaView;
import de.jw.mymensa.mosby.BaseRxPresenter;
import rx.Subscriber;

/**
 * Created by JW on 19.05.2016.
 */

public class MensaPresenterImpl
        extends BaseRxPresenter<MensaView>
        implements MensaPresenter {

    AdviceAPI adviceAPI;

    long start = 0;

    @Inject
    public MensaPresenterImpl(AdviceAPI adviceAPI) {
        this.adviceAPI = adviceAPI;
    }

    @Override
    public void loadDishesList() {

        new RxIOSubscription<AdviceModel>().add(
                adviceAPI.getAdvicesRx("hamburg", "hh5888", "hwg", "WMS4", "10a"),
                new Subscriber<AdviceModel>() {
                    @Override
                    public final void onStart() {
                        start = System.nanoTime();
                    }
                    @Override
                    public void onNext(AdviceModel advices) {
                        try {
                            getView().showRepos(advices);
                        } catch (Exception e) {
                            Log.e("MensaPresenterImpl.java", e.toString());
                        }
                    }

                    @Override
                    public void onCompleted() {
                        getView().hideLoadingIndicator();
                        long elapsedTime = System.nanoTime() - start;
                        System.out.println(String.valueOf(elapsedTime / 1000000000.0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showMessage("Etwas ist schief gelaufen. Bitte überprüfe Projekt und Einrichtung und versuche es noch ein Mal.");
                        //getView().showMessage(e.getMessage());
                        getView().hideLoadingIndicator();
                    }
                }
        );
    }
    @Override
    public void clearRecyclerView() {
        getView().clearRecyclerView();
    }
}
