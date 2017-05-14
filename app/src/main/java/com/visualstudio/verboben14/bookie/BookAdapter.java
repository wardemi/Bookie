package com.visualstudio.verboben14.bookie;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.visualstudio.verboben14.bookie.Model.BookMoly;

/**
 * Created by zsoltdemjan on 2017. 04. 30..
 */

public class BookAdapter extends FirebaseRecyclerAdapter<BookMoly,BookHolder> {

        private Context context;

    public BookAdapter(Class<BookMoly> modelClass, int modelLayout, Class<BookHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
    }

    @Override
    protected void populateViewHolder(final BookHolder viewHolder, final BookMoly model, final int position) {
        viewHolder.setBookTitle(model.getTitle());
        viewHolder.setBookAuthor(model.getAuthor());
        Glide.with(context).load(model.getCover()).into(viewHolder.mCover);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addBookIntent = new Intent(context, AddBookActivity.class);
                addBookIntent.putExtra("isbn",model.getIsbn());
                addBookIntent.putExtra("oldBook",true);
                addBookIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(addBookIntent);
            }
        });
    }



}
