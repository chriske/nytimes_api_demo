package hu.autsoft.nytimes.mock.network.api;

import javax.inject.Inject;

import hu.autsoft.nytimes.network.api.MostViewedApi;
import hu.autsoft.nytimes.network.response.MostViewedResponse;
import retrofit2.Call;

public class MockMostViewedApi implements MostViewedApi {
  @Inject
  MockMostViewedApi() {
  }

  @Override
  public Call<MostViewedResponse> getMostViewedArticles(String section, int timePeriod) {
    return MostViewedApiGetMostViewedArticles.call(section, timePeriod);
  }

  public static void clear() {
    MostViewedApiGetMostViewedArticles.clear();
  }
}
