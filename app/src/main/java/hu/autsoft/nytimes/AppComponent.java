package hu.autsoft.nytimes;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import hu.autsoft.nytimes.network.NetworkModule;
import hu.autsoft.nytimes.ui.UIModule;
import hu.autsoft.nytimes.ui.main.MainActivity;

@Singleton
@Component(modules = {
        NetworkModule.class,
        UIModule.class
})

public interface AppComponent {
    void inject(final MainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(final Application application);

        @BindsInstance
        Builder serviceEndpointAddress(@NetworkModule.ServiceEndpointAddress final String serviceEndpointAddress);

        AppComponent build();
    }
}
