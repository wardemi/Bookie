package com.visualstudio.verboben14.bookie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.visualstudio.verboben14.bookie.Model.BookMoly;

public class MainActivity extends AppCompatActivity {

    private Intent goToLogin;
    private DatabaseReference mRef;
    private DatabaseReference mBookRef;

    private RecyclerView mBookView;
    private LinearLayoutManager mBookList;
    private FirebaseRecyclerAdapter<BookMoly, BookHolder> mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToLogin = new Intent(this, LoginActivity.class);

        final FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            startActivity(goToLogin);
        } else {
            String email = user.getEmail();

            Toast.makeText(MainActivity.this, "Üdv "+email.toString(),
                    Toast.LENGTH_SHORT).show();
        }


        mBookList = new LinearLayoutManager(this);
        mBookList.setReverseLayout(false);


        mBookView = (RecyclerView) findViewById(R.id.book_list_recycler);
        mBookView.setHasFixedSize(false);
        mBookView.setLayoutManager(mBookList);

        Button signOutBtn = (Button) findViewById(R.id.singOutBtn);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Kiléps ",
                        Toast.LENGTH_SHORT).show();

                startActivity(goToLogin);
            }
        });

        Button scanBtn = (Button) findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

        mRef = FirebaseDatabase.getInstance().getReference();
        mBookRef = mRef.child("users/"+user.getUid()+"/books/");

        mContext = getApplicationContext();
        attachRecyclerViewAdapter();
    }



    /**
     * Get zxing result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //TODO test ISBN read and redirect
        Intent intent = new Intent(this, AddBookActivity.class);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                String isbnNumber = "9789634033523";
                intent.putExtra("isbn", isbnNumber);
                startActivity(intent);
            } else {
                //Readed ISBN
                Toast.makeText(MainActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                String isbnNumber = result.getContents();
                intent.putExtra("isbn", isbnNumber);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void attachRecyclerViewAdapter() {
        mAdapter = new BookAdapter(BookMoly.class, R.layout.book_list, BookHolder.class, mBookRef,mContext);
        // Scroll to bottom on new messages
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                mBookList.smoothScrollToPosition(mBookView, null, mAdapter.getItemCount());
            }
        });
        mBookView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
