package de.jw.mymensa.mensa.ui.view.mensa;

import dagger.Component;
import de.jw.mymensa.mensa.ActivityScoped;
import de.jw.mymensa.mensa.ApplicationComponent;
import de.jw.mymensa.mensa.ui.adapter.MensaListAdapter;
import de.jw.mymensa.mensa.ui.presenter.MensaPresenterImpl;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class)
public interface MensaComponent {
    MensaPresenterImpl presenter();

    MensaListAdapter adapter();
}
