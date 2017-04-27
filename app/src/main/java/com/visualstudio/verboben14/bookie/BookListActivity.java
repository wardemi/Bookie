package com.visualstudio.verboben14.bookie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.visualstudio.verboben14.bookie.Model.BookMoly;
import com.visualstudio.verboben14.bookie.Model.BookMolyResponse;
import com.visualstudio.verboben14.bookie.Model.BookPreview;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends AppCompatActivity {

    private Intent goToLogin;
    private MolyApiInterface molyApiService;
    private BookPreview exampleBook;
    private BookMolyResponse molyBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        goToLogin = new Intent(this, LoginActivity.class);

        final FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            startActivity(goToLogin);
        } else {
            String email = user.getEmail();

            Toast.makeText(BookListActivity.this, "Üdv "+email.toString(),
                    Toast.LENGTH_SHORT).show();
        }

        Button signOutBtn = (Button) findViewById(R.id.singOutBtn);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(BookListActivity.this, "Kiléps ",
                        Toast.LENGTH_SHORT).show();

                startActivity(goToLogin);
            }
        });

        Button scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                new IntentIntegrator(MainActivity.this).initiateScan(); // `this` is the current Activity

                IntentIntegrator integrator = new IntentIntegrator(BookListActivity.this);
                integrator.setOrientationLocked(false);
                integrator.setTimeout(8000);
                integrator.initiateScan();


            }
        });

        Button orderButton = (Button) findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderFragmentIntent = new Intent(BookListActivity.this, OrderFragment.class);


            }
        });


        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manualIsbnIntent = new Intent(BookListActivity.this, IsbnSearchFragment.class);
            }
        });

        final ListView booksListView = (ListView) findViewById(R.id.books_listview);
        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookMoly selectedBook = (BookMoly) booksListView.getSelectedItem();
                Intent bookDetailsIntent = new Intent(BookListActivity.this, BookDetails.class);
                bookDetailsIntent.putExtra("selectedBook", selectedBook);
                startActivity(bookDetailsIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(BookListActivity.this, "Cancelled", Toast.LENGTH_LONG).show();

                /**
                 *
                 * ApiInterface apiService =
                 ApiClient.getClient().create(ApiInterface.class);

                 Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
                 call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                }

                @Override
                public void onFailure(Call<MoviesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                }
                });
                 */


                String isbnNumber = "9789634033523";

                getMolyPreview(isbnNumber);






            } else {
                Toast.makeText(BookListActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                String isbnNumber = result.getContents();


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void getMolyPreview(final String isbnNumber) {
        molyApiService = MolyAPI.getClient().create(MolyApiInterface.class);
        Call<BookPreview> bookPreview = molyApiService.getBookByIsbn(isbnNumber,molyApiService.API_KEY);
        bookPreview.enqueue(new Callback<BookPreview>() {
            @Override
            public void onResponse(Call<BookPreview> call, Response<BookPreview> response) {
                Log.d(MolyAPI.TAG,response.code()+"");

                exampleBook = response.body();
                exampleBook.setIsbn(isbnNumber);

                extendMolyBook();
            }

            @Override
            public void onFailure(Call<BookPreview> call, Throwable t) {
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

                BookMolyResponse temp = response.body();

                Log.d(MolyAPI.TAG,  temp.getBooks().toString());
            }

            @Override
            public void onFailure(Call<BookMolyResponse> call, Throwable t) {
                Log.e(MolyAPI.TAG, t.toString());
            }
        });
    }
}
