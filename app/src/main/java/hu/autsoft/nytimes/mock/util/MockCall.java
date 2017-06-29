package hu.autsoft.nytimes.mock.util;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Response;

public class MockCall<T> implements Call<T> {
    public static final int TIMEOUT_ERROR_CODE = -1;
    public static <T> MockCall<T> createSuccessResponse(String jsonAsset, Type type) {
        T responseObject = JavaResourceUtil.getObjectFromJsonResource(jsonAsset, type);
        return createSuccessResponse(responseObject);
    }
    public static <T> MockCall<T> createSuccessResponse(String jsonAsset, Class<T> responseClass) {
        T responseObject = JavaResourceUtil.getObjectFromJsonResource(jsonAsset, responseClass);
        return createSuccessResponse(responseObject);
    }
    public static <T> MockCall<T> createSuccessResponse(T responseObject) {
        return new MockCall<>(true, responseObject, 0, null);
    }
    public static <T> MockCall<T> createErrorResponse(int errorCode) {
        return new MockCall<>(false, null, errorCode, null);
    }
    public static <T> MockCall<T> createErrorResponse(int errorCode, String errorBody) {
        return new MockCall<>(false, null, errorCode, errorBody);
    }
    public static <T> MockCall<T> createTimeoutResponse() {
        return new MockCall<>(false, null, TIMEOUT_ERROR_CODE, null);
    }
    private boolean success;
    private T responseObject;
    private int errorCode;
    private String errorBody;
    private Map<String, String> headersMap;
    private MockCall(boolean success, T responseObject, int errorCode, String errorBody) {
        this.success = success;
        this.responseObject = responseObject;
        this.errorCode = errorCode;
        this.errorBody = errorBody;
    }
    @Override
    public Response<T> execute() throws IOException {
        if (success) {
            Headers headers = getHeaders();
            if (headers != null) {
                return Response.success(responseObject, headers);
            } else {
                return Response.success(responseObject);
            }
        } else {
            if (errorCode == TIMEOUT_ERROR_CODE) {
                throw new IOException();
            } else {
                return getErrorResponse(errorCode);
            }
        }
    }
    private Headers getHeaders() {
        if (headersMap == null || headersMap.size() == 0) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> header : headersMap.entrySet()) {
            builder.add(header.getKey(), header.getValue());
        }
        return builder.build();
    }
    @Override
    public void enqueue(retrofit2.Callback<T> callback) {
        if (success) {
            Headers headers = getHeaders();
            if (headers != null) {
                callback.onResponse(this, Response.success(responseObject, headers));
            } else {
                callback.onResponse(this, Response.success(responseObject));
            }
        } else {
            if (errorCode == TIMEOUT_ERROR_CODE) {
                callback.onFailure(this, new IOException());
            } else {
                callback.onResponse(this, getErrorResponse(errorCode));
            }
        }
    }
    @Override
    public boolean isExecuted() {
        return false;
    }
    @Override
    public void cancel() {
    }
    @Override
    public boolean isCanceled() {
        return false;
    }
    @Override
    public Call<T> clone() {
        return null;
    }
    @Override
    public Request request() {
        return null;
    }
    private Response<T> getErrorResponse(int code) {
        return Response.error(code, new ResponseBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("application/json");
            }
            @Override
            public long contentLength() {
                return 0;
            }
            @Override
            public BufferedSource source() {
                return null;
            }
        });
    }
    public MockCall<T> addHeader(String key, String value) {
        if (headersMap == null) {
            headersMap = new HashMap<>();
        }
        headersMap.put(key, value);
        return this;
    }
}
