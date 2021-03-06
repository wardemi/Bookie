package com.visualstudio.verboben14.bookie;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zsoltdemjan on 2017. 04. 15..
 *
 * https://moly.hu/api/books.json?q=vándorünnep&key=515018eae40f203e53948dc40109ca91
 * https://moly.hu/api/book_by_isbn.json?q=963825419X&key=515018eae40f203e53948dc40109ca91
 *
 * https://moly.hu/api/book/15331.json?key=515018eae40f203e53948dc40109ca91
 */

public class MolyAPI {
    private final static String API_KEY = "515018eae40f203e53948dc40109ca91";
    private static String BASE_URL = "https://moly.hu/api/";

    public static String TAG = "MolyApi";

    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public final static String getApiKey() {
        return API_KEY;
    }
}
