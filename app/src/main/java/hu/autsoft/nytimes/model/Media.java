package hu.autsoft.nytimes.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Media {
    @SerializedName("media-metadata")
    private ArrayList<MediaMetaData> metaDatas;

    public ArrayList<MediaMetaData> getMetaDatas() {
        return metaDatas;
    }
}
