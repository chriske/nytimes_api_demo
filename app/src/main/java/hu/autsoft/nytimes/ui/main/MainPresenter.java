package hu.autsoft.nytimes.ui.main;


import javax.inject.Inject;

import hu.autsoft.nytimes.network.api.MostViewedApi;
import hu.autsoft.nytimes.network.response.MostViewedResponse;
import hu.autsoft.nytimes.ui.Presenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends Presenter<MainScreen> {

    public static final String TAG = MainPresenter.class.getSimpleName();

    public static final int NETWORK_ERROR = 0;
    public static final int RESPONSE_ERROR = 1;

    private MostViewedApi mostViewedApi;

    @Inject
    public MainPresenter(MostViewedApi mostViewedApi) {
        this.mostViewedApi = mostViewedApi;
    }

    public void getArticles() {
        mostViewedApi.getMostViewedArticles("all-sections", 7).enqueue(new Callback<MostViewedResponse>() {
            @Override
            public void onResponse(Call<MostViewedResponse> call, Response<MostViewedResponse> response) {
                if (response.isSuccessful()) {
                    if (screen != null) {
                        screen.onArticlesArrived(response.body().getArticles());
                    }
                } else {
                    if (screen != null) {
                        screen.onArticleRequestError(RESPONSE_ERROR);
                    }
                }
            }

            @Override
            public void onFailure(Call<MostViewedResponse> call, Throwable t) {
                if (screen != null) {
                    screen.onArticleRequestError(NETWORK_ERROR);
                }
            }
        });
    }
}
