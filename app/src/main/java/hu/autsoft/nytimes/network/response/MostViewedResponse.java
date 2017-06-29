package hu.autsoft.nytimes.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import hu.autsoft.nytimes.model.Article;

public class MostViewedResponse {
    @SerializedName("results")
    private ArrayList<Article> articles;

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
