package hu.autsoft.nytimes.test;


import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import hu.autsoft.nytimes.AppTestComponent;
import hu.autsoft.nytimes.mock.network.api.MostViewedApiGetMostViewedArticles;
import hu.autsoft.nytimes.mock.util.JavaResourceUtil;
import hu.autsoft.nytimes.mock.util.MockCall;
import hu.autsoft.nytimes.model.Article;
import hu.autsoft.nytimes.network.response.MostViewedResponse;
import hu.autsoft.nytimes.ui.main.MainPresenter;
import hu.autsoft.nytimes.ui.main.MainScreen;
import hu.autsoft.nytimes.util.InjectedTest;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.internal.bytecode.RobolectricInternals.getClassLoader;

public class MainPresenterTest extends InjectedTest {

    @Inject
    MainPresenter presenter;

    @Inject
    Gson gson;

    @Captor
    private ArgumentCaptor<ArrayList<Article>> captor;

    private MainScreen screen;

    @Override
    public void injectDependencies(AppTestComponent appTestComponent) {
        appTestComponent.inject(this);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JavaResourceUtil.init(gson, getClassLoader());
        screen = mock(MainScreen.class);
        presenter.attachScreen(screen);
    }

    @Test
    public void testIOError() {
        MostViewedApiGetMostViewedArticles.nextResponse(MockCall.<MostViewedResponse>createTimeoutResponse());
        presenter.getArticles();
        verify(screen).onArticleRequestError(MainPresenter.NETWORK_ERROR);
    }

    @Test
    public void testResponseError() {
        MostViewedApiGetMostViewedArticles.nextResponse(MockCall.<MostViewedResponse>createErrorResponse(HttpsURLConnection.HTTP_BAD_REQUEST));
        presenter.getArticles();
        verify(screen).onArticleRequestError(MainPresenter.RESPONSE_ERROR);
    }

    @Test
    public void testResponseOK() {
        MostViewedApiGetMostViewedArticles.nextResponse(MockCall.createSuccessResponse("articlesResponse.json", MostViewedResponse.class));
        presenter.getArticles();
        verify(screen).onArticlesArrived(captor.capture());
        assertEquals(captor.getValue().size(), 20);
    }
}
