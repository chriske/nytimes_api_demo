package hu.autsoft.nytimes;

import javax.inject.Singleton;

import dagger.Component;
import hu.autsoft.nytimes.mock.network.MockNetworkModule;
import hu.autsoft.nytimes.test.MainPresenterTest;
import hu.autsoft.nytimes.ui.UIModule;

@Singleton
@Component(modules = {
        UIModule.class,
        MockNetworkModule.class
})
public interface AppTestComponent extends AppComponent {
    void inject(TestNYTApplication testNYTApplication);

    void inject(MainPresenterTest mainPresenterTest);

    @Component.Builder
    interface Builder extends AppComponent.Builder {
        AppTestComponent build();
    }
}
