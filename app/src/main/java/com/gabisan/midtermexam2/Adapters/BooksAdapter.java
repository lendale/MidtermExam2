package com.gabisan.midtermexam2.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gabisan.midtermexam2.Entities.Book;
import com.gabisan.midtermexam2.R;

import java.util.List;

/**
 * Created by daleg on 24/02/2016.
 */
public class BooksAdapter extends ArrayAdapter<Book> {

    private Context mContext;
    private int mLayoutId;
    private List<Book> mBooks;

    public BooksAdapter(Context context, int resource, List<Book> mBooks) {
        super(context, resource, mBooks);
        this.mContext = context;
        this.mLayoutId = resource;
        this.mBooks = mBooks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // Inflate the layout
            convertView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);

            // create the view holder
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvBookTitle);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            // Set the book data
            Book book = mBooks.get(position);

            if (book != null) {
                if (viewHolder.tvTitle != null) {
                    viewHolder.tvTitle.setText(book.getmTitle());

                    if (book.getmIsRead()) {
                        viewHolder.tvTitle.setPaintFlags(viewHolder.tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        viewHolder.tvTitle.setTextColor(Color.parseColor("#B0171F"));
                    }
                }
            }
        return convertView;
    }

    private static class ViewHolder {
        public TextView tvTitle;
    }

}
