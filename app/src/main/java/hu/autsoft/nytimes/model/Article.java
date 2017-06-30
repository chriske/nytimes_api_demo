package hu.autsoft.nytimes.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class Article {
    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;
    @SerializedName("byline")
    private String byLine;
    @SerializedName("published_date")
    private Date date;
    @SerializedName("media")
    private MediaArrayList media;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getByLine() {
        return byLine;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public Date getDate() {
        return date;
    }
}
