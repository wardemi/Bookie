package com.visualstudio.verboben14.bookie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetails extends AppCompatActivity {

    private TextView isbn;
    private TextView bookTitle;
    private TextView bookAuthors;
    private TextView bookLikes;
    private TextView bookDescription;
    private ImageView bookCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        isbn = (TextView) findViewById(R.id.book_details_isbn);
        bookTitle = (TextView) findViewById(R.id.book_details_booktitle);
        bookAuthors = (TextView) findViewById(R.id.book_details_bookauthors);
        bookLikes = (TextView) findViewById(R.id.book_details_booklikes);
        bookDescription = (TextView) findViewById(R.id.book_details_bookdescription);
        bookCover = (ImageView) findViewById(R.id.book_details_bookcover);

        ImageButton favoriteBtn = (ImageButton) findViewById(R.id.book_details_favoriteBtn);
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookDetails.this, "@string/book_details_added_favorites", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
