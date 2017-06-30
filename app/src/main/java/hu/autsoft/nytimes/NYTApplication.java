package hu.autsoft.nytimes;


import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

public class NYTApplication extends Application {
    public static AppComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        setupInjector();

        initApplicationDependentComponents();
    }

    protected void initApplicationDependentComponents() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }

    protected void setupInjector() {
        injector = DaggerAppComponent.builder()
                .application(this)
                .serviceEndpointAddress(getServiceEndpointAddress())
                .build();
    }

    protected String getServiceEndpointAddress() {
        Context context = getApplicationContext();
        return context.getString(R.string.config_service_endpoint_address) + context.getString(R.string.config_service_endpoint_path);
    }

    protected void injectDependencies(final AppComponent appComponent) {
        appComponent.inject(this);
    }
}
