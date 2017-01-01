package com.dotakoubou.sentencedictionary.RecyclerView;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;
import com.dotakoubou.sentencedictionary.Helper.WordListHelper;
import com.dotakoubou.sentencedictionary.R;

/**
 * Created by dotua on 31-Dec-16.
 */

public class RecyclerViewWordAdapter extends DragSelectRecyclerViewAdapter<WordViewHolder> {
    private Activity activity = null;
    private final ClickListener mCallback;

    public interface ClickListener {
        void onClick(int index);

        void onLongClick(int index);
    }

    public RecyclerViewWordAdapter(Activity activity, ClickListener callback) {
        super();
        mCallback = callback;
        this.activity = activity;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = activity.getLayoutInflater()
                .inflate(R.layout.recycler_view_cell, parent, false);
        return (new WordViewHolder(v, mCallback));

    }

    @Override
    public int getItemCount() {
        return (WordListHelper.getWordListSize());
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bindModel(position);

        if (isIndexSelected(position)) {
//            holder.setTextViewBackgroundColor(R.color.grid_background_selected);
            holder.setTextViewTextColor(ContextCompat.getColor(activity, R.color.grid_label_text_selected));
        } else {
//            holder.setTextViewBackgroundColor(R.color.grid_background_normal);
            holder.setTextViewTextColor(ContextCompat.getColor(activity, R.color.grid_label_text_normal));
        }
    }
}