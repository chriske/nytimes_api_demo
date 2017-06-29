package hu.autsoft.nytimes.network;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.autsoft.nytimes.R;

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
        return gsonBuilder.setDateFormat(dateFormatString).create();
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface GsonDateFormatString {
    }
}
