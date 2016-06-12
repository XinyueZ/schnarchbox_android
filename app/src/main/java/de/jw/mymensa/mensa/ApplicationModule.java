package de.jw.mymensa.mensa;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.jw.mymensa.mensa.data.service.AdviceAPI;
import retrofit.RestAdapter;

@Module
public class ApplicationModule {
    final Application application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }

    @Provides
    public AdviceAPI provideAdviceAPI() {
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AdviceAPI.URI).build();
        return adapter.create(AdviceAPI.class);
    }
}
