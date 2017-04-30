package com.visualstudio.verboben14.bookie;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.visualstudio.verboben14.bookie.Model.BookMoly;
import com.visualstudio.verboben14.bookie.Model.BookMolyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity {

    private Intent mRedirectIntent;
    private String mUid;

    private String mIsbn;
    private MolyApiInterface molyApiService;
    private BookMoly exampleBook;
    private BookMolyResponse molyBook;

    private TextView isbn;
    private TextView bookTitle;
    private TextView bookAuthors;
    private TextView bookLikes;
    private TextView bookDescription;

    private LinearLayout contentLayout;
    private LinearLayout progressBar;
    private LinearLayout failLayout;

    private DatabaseReference mBookReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //Check available Network
        if(!isNetworkAvailable()) {
             Toast.makeText(AddBookActivity.this, "Szükséges internet elérés",
                             Toast.LENGTH_SHORT).show();

            mRedirectIntent = new Intent(this, MainActivity.class);
            startActivity(mRedirectIntent);
            finish();
        }

        //Init Firebase Auth
        mRedirectIntent = new Intent(this, LoginActivity.class);
        final FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            startActivity(mRedirectIntent);
        } else {
            mUid = user.getUid();
        }



        //INIT VIEW ELEMENTS
        isbn = (TextView) findViewById(R.id.isbn);
        bookTitle = (TextView) findViewById(R.id.bookTitle);
        bookAuthors = (TextView) findViewById(R.id.bookAuthors);
        bookLikes = (TextView) findViewById(R.id.bookLikes);
        bookDescription = (TextView) findViewById(R.id.bookDescription);

        contentLayout = (LinearLayout)findViewById(R.id.add_content_layout);
        progressBar = (LinearLayout) findViewById(R.id.Loading);
        failLayout = (LinearLayout) findViewById(R.id.fail_result);

        // Get intent extras
        Bundle extras = getIntent().getExtras();
        mIsbn = extras.getString("isbn");
        isbn.setText(mIsbn);

        loadingStart();
        getMolyPreview(mIsbn);

        //Init FireBase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mBookReference = database.getReference();

        Button backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button failBackBtn = (Button) findViewById(R.id.fail_back_btn);
        failBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button addBtn = (Button) findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mBookReference.push().getKey();
                exampleBook.setUid(mUid);
                exampleBook.setBid(key);
                mBookReference.child("users/"+mUid+"/books/"+key).setValue(exampleBook).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddBookActivity.this, "Sikeresen hozzá lett adva a könyv a listájához",
                                Toast.LENGTH_SHORT).show();
                        addBtn.setVisibility(View.GONE);
                    }
                });
            }
        });
    }


    private void getMolyPreview(final String isbnNumber) {
        molyApiService = MolyAPI.getClient().create(MolyApiInterface.class);
        Call<BookMoly> bookPreview = molyApiService.getBookByIsbn(isbnNumber,MolyAPI.getApiKey());
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
                loadingFail();
                Toast.makeText(AddBookActivity.this, "Nincs találat a könyvre",
                        Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void extendMolyBook() {
        molyApiService = MolyAPI.getClient().create(MolyApiInterface.class);
        Call<BookMolyResponse> finalBookMoly = molyApiService.getBookById(exampleBook.getId(),MolyAPI.getApiKey());
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

    private void loadingFail(){
        progressBar.setVisibility(View.GONE);
        failLayout.setVisibility(View.VISIBLE);
    }

    private void setView() {
        bookAuthors.setText(exampleBook.getAuthor());
        bookDescription.setText(exampleBook.getDescription());
        bookLikes.setText(exampleBook.getLikeAvg().toString());
        bookTitle.setText(exampleBook.getTitle());

        loadingEnd();

        ImageView imageView = (ImageView) findViewById(R.id.book_cover);
        Glide.with(this).load(exampleBook.getCover()).into(imageView);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}