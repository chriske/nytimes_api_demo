package hu.autsoft.nytimes.mock.network;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import hu.autsoft.nytimes.mock.network.api.MockMostViewedApi;
import hu.autsoft.nytimes.network.BaseNetworkModule;
import hu.autsoft.nytimes.network.api.MostViewedApi;

@Module(includes = {
        BaseNetworkModule.class
})
public abstract class MockNetworkModule {
    @Binds
    @Singleton
    abstract MostViewedApi provideAlertTypeApi(final MockMostViewedApi mockAlertTypeApi);
}
