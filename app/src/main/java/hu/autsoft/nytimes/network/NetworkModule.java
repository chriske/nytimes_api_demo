package hu.autsoft.nytimes.network;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.autsoft.nytimes.exception.OkHttpException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {
        BaseNetworkModule.class
})
public abstract class NetworkModule {
    public static final String TAG = NetworkModule.class.getSimpleName();

    @Provides
    @Singleton
    static Retrofit provideRetrofit(@ServiceEndpointAddress final String serviceEndpoint, final OkHttpClient client, final Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(serviceEndpoint)
                .client(client)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(final OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    @InterceptorHttpLogging
    static Interceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return logging;
    }

    @Provides
    @Singleton
    @InterceptorStetho
    static Interceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    static OkHttpClient.Builder provideOkHttpClientBuilder(@InterceptorStetho final Interceptor stethoInterceptor,
                                                           @InterceptorHttpLogging final Interceptor httpLoggingInterceptor) {
        OkHttpClient.Builder clientBuilder;

        try {
            clientBuilder = new OkHttpClient().newBuilder();
        } catch (Exception e) {
            Log.d(TAG, "OkHTTP exception", e);
            throw new OkHttpException(e);
        }

        clientBuilder.addNetworkInterceptor(stethoInterceptor);
        clientBuilder.interceptors().add(httpLoggingInterceptor);
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);

        return clientBuilder;
    }

    @Provides
    @Singleton
    static Converter.Factory provideGsonConverterFactory(final Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InterceptorStetho {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InterceptorHttpLogging {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ServiceEndpointAddress {
    }
}
