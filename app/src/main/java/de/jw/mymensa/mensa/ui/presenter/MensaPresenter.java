package de.jw.mymensa.mensa.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import de.jw.mymensa.mensa.ui.view.mensa.MensaView;

/**
 * Created by JW on 19.05.2016.
 */

public interface MensaPresenter
        extends MvpPresenter<MensaView> {
    void loadDishesList();
    void clearRecyclerView();
}
