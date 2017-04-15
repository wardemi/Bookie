package com.visualstudio.verboben14.bookie;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zsoltdemjan on 2017. 04. 15..
 *
 * https://moly.hu/api/books.json?q=vándorünnep&key=515018eae40f203e53948dc40109ca91
 * https://moly.hu/api/book_by_isbn.json?q=963825419X&key=515018eae40f203e53948dc40109ca91
 */

public class MolyAPI {
    private static String API_KEY = "515018eae40f203e53948dc40109ca91";
    private static String BASE_URL = "https://moly.hu/api/";
    private static String KEY = "&key="+API_KEY;
    public static String SERACH_TITLE = "books.json?q=";
    public static String SERACH_ISBN = "book_by_isbn.json?q=";

    private String resultString = "";
    private static String LOG_TAG = "MolyAPI";

    OkHttpClient client = new OkHttpClient();

    String run(String method,String search) throws IOException {


        String searchURL = BASE_URL+method+search+KEY;
        Log.d(LOG_TAG, "url:" + searchURL);

        Request request = new Request.Builder()
                .url(searchURL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(LOG_TAG, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.w(LOG_TAG, response.body().string());
                Log.i(LOG_TAG, response.toString());
                resultString = response.body().string();
            }
        });

        return resultString;
    }
}
