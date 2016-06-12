package de.jw.mymensa.mensa.ui.view.mensa;

import de.jw.mymensa.mensa.data.entity.AdviceModel;
import de.jw.mymensa.mosby.BaseMvpView;

public interface MensaView extends BaseMvpView {
    void showRepos(AdviceModel advices);
    void clearRecyclerView();
    void hideLoadingIndicator();
}
