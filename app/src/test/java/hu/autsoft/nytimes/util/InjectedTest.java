package hu.autsoft.nytimes.util;


import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import hu.autsoft.nytimes.AppTestComponent;
import hu.autsoft.nytimes.BuildConfig;
import hu.autsoft.nytimes.NYTApplication;
import hu.autsoft.nytimes.TestNYTApplication;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, packageName = "hu.autsoft.nytimes", application = TestNYTApplication.class)
public abstract class InjectedTest {
    public InjectedTest() {
        injectDependencies((AppTestComponent) NYTApplication.injector);
    }

    public abstract void injectDependencies(final AppTestComponent appTestComponent);
}
