package com.visualstudio.verboben14.bookie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.visualstudio.verboben14.bookie.Model.BookMoly;

public class MainActivity extends BaseActivity {

    private DatabaseReference mBookRef;
    private DatabaseReference mRef;
    static boolean calledAlready = false;

    private RecyclerView mBookView;
    private LinearLayoutManager mBookList;
    private FirebaseRecyclerAdapter<BookMoly, BookHolder> mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isLogged()) {
            mRedirectIntent = new Intent(this, LoginActivity.class);
            Toast.makeText(MainActivity.this, "Jelentkezzen be", Toast.LENGTH_SHORT).show();
            startActivity(mRedirectIntent);
        } else {
            if (!isNetworkAvailable()) {
                Toast.makeText(MainActivity.this, "Internet elérés szükséges", Toast.LENGTH_SHORT).show();
            } else {


                mBookList = new LinearLayoutManager(this);
                mBookList.setReverseLayout(false);

                mBookView = (RecyclerView) findViewById(R.id.book_list_recycler);
                mBookView.setHasFixedSize(false);
                mBookView.setLayoutManager(mBookList);

                //TODO Signout button hozzáadása
                BottomNavigationItemView signOutBtn = (BottomNavigationItemView) findViewById(R.id.orderButton);
                signOutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(MainActivity.this, "Kilépés ", Toast.LENGTH_SHORT).show();
                        mRedirectIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(mRedirectIntent);
                    }
                });

                BottomNavigationItemView scanBtn = (BottomNavigationItemView) findViewById(R.id.scanButton);
                scanBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                        integrator.setOrientationLocked(false);
                        integrator.initiateScan();
                    }
                });

                BottomNavigationItemView addButton = (BottomNavigationItemView) findViewById(R.id.addButton);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("ISBN kód");

                        // Set up the input
                        final EditText input = new EditText(MainActivity.this);
                        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);

                        // Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent addBookIntent = new Intent(MainActivity.this, AddBookActivity.class);
                                addBookIntent.putExtra("isbn", input.getText().toString());
                                startActivity(addBookIntent);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    }
                });

                //Persistance miatt csak 1x inicializálható
                if (!calledAlready) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //TODO barcode visszatérénsél elszáll Persistance... mert már valahol lézetik , de elv le vna kezelva hogy ha létezik skipp
                    //database.setPersistenceEnabled(true);
                    mRef = database.getReference();
                    calledAlready = true;
                }

                mBookRef = mRef.child("users/" + mUser.getUid().toString() + "/books/");

                mContext = getApplicationContext();
                attachRecyclerViewAdapter();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isNetworkAvailable()) {
            TextView notNetwork = (TextView) findViewById(R.id.networkStatus);
            notNetwork.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Get zxing result
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //TODO test ISBN read and redirect
        Intent intent = new Intent(this, AddBookActivity.class);

        if (result != null) {
            if (result.getContents() == null) {
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
        mAdapter = new BookAdapter(BookMoly.class, R.layout.book_list, BookHolder.class, mBookRef, mContext);
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
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mAdapter != null)
        mAdapter.cleanup();
    }
}
