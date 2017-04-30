package com.visualstudio.verboben14.bookie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BookHolder extends RecyclerView.ViewHolder {
    private final TextView mBookTitle;
    private final TextView mBookAuthor;
    public ImageView mCover;


    public BookHolder(View itemView) {
        super(itemView);
        mBookTitle = (TextView) itemView.findViewById(R.id.book_title);
        mBookAuthor = (TextView) itemView.findViewById(R.id.book_author);
        mCover = (ImageView) itemView.findViewById(R.id.book_cover);
    }

    public void setBookTitle(String title) {
        mBookTitle.setText(title);
    }

    public void setBookAuthor(String author) {
        mBookAuthor.setText(author);
    }

    public TextView getBookTitle() {
        return mBookTitle;
    }

    public TextView getBookAuthor() {
        return mBookAuthor;
    }

    public ImageView getCover() {
        return mCover;
    }
}
