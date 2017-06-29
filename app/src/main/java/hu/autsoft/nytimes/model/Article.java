package hu.autsoft.nytimes.model;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Article {
    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;
    @SerializedName("published_date")
    private Date date;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }
}
