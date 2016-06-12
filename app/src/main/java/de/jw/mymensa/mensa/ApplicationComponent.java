package de.jw.mymensa.mensa;

import android.content.Context;

import dagger.Component;
import de.jw.mymensa.mensa.data.service.AdviceAPI;

@ApplicationScoped
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context context();

    AdviceAPI adviceAPI();
}
