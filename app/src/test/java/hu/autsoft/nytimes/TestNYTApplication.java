package hu.autsoft.nytimes;


import com.google.gson.Gson;

import org.robolectric.shadows.ShadowLog;

import javax.inject.Inject;

import hu.autsoft.nytimes.mock.util.JavaResourceUtil;

public class TestNYTApplication extends NYTApplication {

    @Override
    protected void initApplicationDependentComponents() {
        ShadowLog.stream = System.out;
    }

    @Override
    protected void setupInjector() {
        injector = DaggerAppTestComponent.builder()
                .application(this)
                .serviceEndpointAddress(getServiceEndpointAddress())
                .build();
    }

    @Override
    protected void injectDependencies(final AppComponent appComponent) {
        super.injectDependencies(appComponent);
        ((AppTestComponent) appComponent).inject(this);
    }
}
