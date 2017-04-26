package com.visualstudio.verboben14.bookie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.visualstudio.verboben14.bookie.Model.BookMoly;
import com.visualstudio.verboben14.bookie.Model.BookMolyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBook extends AppCompatActivity {

    private String mIsbn;
    private MolyApiInterface molyApiService;
    private BookMoly exampleBook;
    private BookMoly finalBook;
    private BookMolyResponse molyBook;

    private TextView isbn;
    private TextView bookTitle;
    private TextView bookAuthors;
    private TextView bookLikes;
    private TextView bookDescription;

    private LinearLayout contentLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //TODO Ha logoláshoz van kötve minden feature akkor minden activitynél külön kell validálni a logolást??? vagy letudom zárni, hogy ez az activity nem jöhet létre csak mainből = logolva van

        //TODO Lekezelni ha nem talált semmit

        finalBook = new BookMoly();

        //INIT VIEW ELEMENTS
        isbn = (TextView) findViewById(R.id.isbn);
        bookTitle = (TextView) findViewById(R.id.bookTitle);
        bookAuthors = (TextView) findViewById(R.id.bookAuthors);
        bookLikes = (TextView) findViewById(R.id.bookLikes);
        bookDescription = (TextView) findViewById(R.id.bookDescription);

        contentLayout = (LinearLayout)findViewById(R.id.add_content_layout);
        progressBar = (ProgressBar)findViewById(R.id.Loading);

        // Get intent extras
        Bundle extras = getIntent().getExtras();
        mIsbn = extras.getString("isbn");
        isbn.setText(mIsbn);

        loadingStart();
        getMolyPreview(mIsbn);

        loadingEnd();

        Button backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getMolyPreview(final String isbnNumber) {
        molyApiService = MolyAPI.getClient().create(MolyApiInterface.class);
        Call<BookMoly> bookPreview = molyApiService.getBookByIsbn(isbnNumber,molyApiService.API_KEY);
        bookPreview.enqueue(new Callback<BookMoly>() {
            @Override
            public void onResponse(Call<BookMoly> call, Response<BookMoly> response) {
                Log.d(MolyAPI.TAG,response.code()+"");

                exampleBook = response.body();
                exampleBook.setIsbn(isbnNumber);

                extendMolyBook();
            }

            @Override
            public void onFailure(Call<BookMoly> call, Throwable t) {
                Log.e(MolyAPI.TAG, t.toString());
            }
        });
    }

    private void extendMolyBook() {
        molyApiService = MolyAPI.getClient().create(MolyApiInterface.class);
        Call<BookMolyResponse> finalBookMoly = molyApiService.getBookById(exampleBook.getId(),molyApiService.API_KEY);
        finalBookMoly.enqueue(new Callback<BookMolyResponse>() {
            @Override
            public void onResponse(Call<BookMolyResponse> call, Response<BookMolyResponse> response) {
                Log.d(MolyAPI.TAG,response.code()+"");

                molyBook = response.body();

                Log.d(MolyAPI.TAG,  molyBook.getMolyBook().toString());
                Log.d(MolyAPI.TAG,  molyBook.getMolyBook().getTitle().toString());

                molyBook.setMolyBookIsbn(exampleBook.getIsbn());
                molyBook.setMolyBookAuthor(exampleBook.getAuthor());

                exampleBook = molyBook.getMolyBook();
                finalBook = exampleBook;
                setView();
            }

            @Override
            public void onFailure(Call<BookMolyResponse> call, Throwable t) {
                Log.e(MolyAPI.TAG, t.toString());
            }
        });
    }

    /**
     * Hide empty element and show loading progress
     */
    private void loadingStart() {
        contentLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void loadingEnd() {
        contentLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void setView() {
        bookAuthors.setText(exampleBook.getAuthor());
        bookDescription.setText(exampleBook.getDescription());
        bookLikes.setText(exampleBook.getLikeAvg().toString());
        bookTitle.setText(exampleBook.getTitle());
    }
}
