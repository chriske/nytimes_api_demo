package hu.autsoft.nytimes.network.interceptor;


import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;

import hu.autsoft.nytimes.NYTApplication;
import hu.autsoft.nytimes.R;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyProviderInterceptor implements Interceptor {
    @Inject
    Context context;

    public ApiKeyProviderInterceptor() {
        NYTApplication.injector.inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api-key", context.getString(R.string.api_key))
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
