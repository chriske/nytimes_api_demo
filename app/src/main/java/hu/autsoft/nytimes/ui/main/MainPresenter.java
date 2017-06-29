package hu.autsoft.nytimes.ui.main;


import android.util.Log;

import javax.inject.Inject;

import hu.autsoft.nytimes.NYTApplication;
import hu.autsoft.nytimes.network.api.MostViewedApi;
import hu.autsoft.nytimes.network.response.MostViewedResponse;
import hu.autsoft.nytimes.ui.Presenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends Presenter<MainScreen> {

    public static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    MostViewedApi mostViewedApi;

    @Inject
    public MainPresenter() {
        NYTApplication.injector.inject(this);
    }

    public void getArticles() {
        mostViewedApi.getMostViewedArticles("all-sections", 7).enqueue(new Callback<MostViewedResponse>() {
            @Override
            public void onResponse(Call<MostViewedResponse> call, Response<MostViewedResponse> response) {
                Log.d(TAG, response.body().getArticles().toString());
            }

            @Override
            public void onFailure(Call<MostViewedResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
