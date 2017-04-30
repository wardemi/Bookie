package com.visualstudio.verboben14.bookie;

import com.visualstudio.verboben14.bookie.Model.BookMoly;
import com.visualstudio.verboben14.bookie.Model.BookMolyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zsoltdemjan on 2017. 04. 18..
 *
 * https://moly.hu/api/books.json?q=vándorünnep&key=515018eae40f203e53948dc40109ca91
 * https://moly.hu/api/book_by_isbn.json?q=963825419X&key=515018eae40f203e53948dc40109ca91
 *
 * https://moly.hu/api/book/15331.json?key=515018eae40f203e53948dc40109ca91
 * 114139
 */

interface MolyApiInterface {
    /**
     * Get book by title
     * @param title
     * @param apiKey
     * @return
     */
    @GET("books.json")
    Call<BookMoly> getBookByTitle(@Query("q") String title, @Query("key") String apiKey);


    /**
     * Get book by ISBN number
     * @param isbn
     * @param apiKey
     * @return
     */
    @GET("book_by_isbn.json")
    Call<BookMoly> getBookByIsbn(@Query("q") String isbn, @Query("key") String apiKey);

    /**
     * Get book by Id
     * @param id
     * @param apiKey
     * @return
     */
    @GET("book/{id}.json")
    Call<BookMolyResponse> getBookById(@Path("id") Long id, @Query("key") String apiKey);
}
