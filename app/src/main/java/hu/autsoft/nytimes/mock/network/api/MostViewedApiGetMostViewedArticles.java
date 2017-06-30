package hu.autsoft.nytimes.mock.network.api;

import hu.autsoft.nytimes.mock.util.MockCallQueue;
import hu.autsoft.nytimes.network.response.MostViewedResponse;
import retrofit2.Call;

public class MostViewedApiGetMostViewedArticles {
  private static MockCallQueue<GetMostViewedArticlesCall> nextCall = new MockCallQueue<>();

  static void clear() {
    nextCall.clear();
  }

  public static void nextCall(GetMostViewedArticlesCall... calls) {
    for( GetMostViewedArticlesCall call : nextCall) {
      nextCall.push(call);
    }
  }

  public static void nextResponse(final Call<MostViewedResponse>... nextResponses) {
    for( Call<MostViewedResponse> nextResponse : nextResponses) {
      nextCall.push(new SimpleGetMostViewedArticlesCall(nextResponse));;
    }
  }

  static Call<MostViewedResponse> call(String section, int timePeriod) {
    nextCall.errorIfEmpty();
    return nextCall.poll().getMostViewedArticles(section, timePeriod);
  }

  public interface GetMostViewedArticlesCall {
    Call<MostViewedResponse> getMostViewedArticles(String section, int timePeriod);
  }

  static class SimpleGetMostViewedArticlesCall implements GetMostViewedArticlesCall {
    Call<MostViewedResponse> object;

    SimpleGetMostViewedArticlesCall(Call<MostViewedResponse> object) {
      this.object = object;
    }

    @Override
    public Call<MostViewedResponse> getMostViewedArticles(String section, int timePeriod) {
      return object;
    }
  }
}
