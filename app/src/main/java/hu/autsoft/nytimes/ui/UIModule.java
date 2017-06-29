package hu.autsoft.nytimes.ui;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class UIModule {
    @Singleton
    @Provides
    static Context provideApplicationContext(final Application application) {
        return application.getApplicationContext();
    }
}
