package com.visualstudio.verboben14.bookie;

import android.content.Context;
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
    protected void populateViewHolder(BookHolder viewHolder, final BookMoly model, final int position) {
        viewHolder.setBookTitle(model.getTitle());
        viewHolder.setBookAuthor(model.getAuthor());
        Glide.with(context).load(model.getCover()).into(viewHolder.mCover);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, getRef(position).getKey(),
                        Toast.LENGTH_SHORT).show();

                //TODO getKey átadása másik ablaknak ahol lekérdezhető a többi adat + máveletvégzések
            }
        });
    }

}
