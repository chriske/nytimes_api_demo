package hu.autsoft.nytimes.network;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.autsoft.nytimes.R;
import hu.autsoft.nytimes.model.MediaArrayList;

@Module
public class BaseNetworkModule {
    @Provides
    @Singleton
    @GsonDateFormatString
    static String provideGsonDateFormatString(final Context context) {
        return context.getString(R.string.config_gson_date_format);
    }

    @Provides
    @Singleton
    static GsonBuilder provideGsonBuilder() {
        return new GsonBuilder();
    }

    @Provides
    @Singleton
    static Gson provideGson(final GsonBuilder gsonBuilder, @GsonDateFormatString final String dateFormatString) {
        return gsonBuilder.setDateFormat(dateFormatString).registerTypeAdapter(MediaArrayList.class, new JsonDeserializer<MediaArrayList>() {
            @Override
            public MediaArrayList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (json.isJsonArray()) {
                    Gson g = new Gson();
                    return g.fromJson(json, MediaArrayList.class);
                }

                return null;
            }
        }).create();
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface GsonDateFormatString {
    }
}
