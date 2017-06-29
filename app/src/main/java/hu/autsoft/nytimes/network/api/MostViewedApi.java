package hu.autsoft.nytimes.network.api;


import hu.autsoft.nytimes.network.response.MostViewedResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MostViewedApi {
    @GET("/mostviewed/{section}/{time-period}.json")
    Call<MostViewedResponse> getMostViewedArticles(@Path("section") String section, @Path("time-period") int timePeriod);
}
