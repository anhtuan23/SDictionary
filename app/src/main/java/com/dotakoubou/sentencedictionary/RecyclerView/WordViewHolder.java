package com.dotakoubou.sentencedictionary.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dotakoubou.sentencedictionary.Helper.WordListHelper;
import com.dotakoubou.sentencedictionary.R;

/**
 * Created by dotua on 31-Dec-16.
 */

class WordViewHolder
        extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    private TextView wordTextView = null;
    private char word = 'h';
    private final RecyclerViewWordAdapter.ClickListener mCallback;

    WordViewHolder(View wordTextView, RecyclerViewWordAdapter.ClickListener callback) {
        super(wordTextView);
        this.mCallback = callback;
        this.wordTextView = (TextView) wordTextView.findViewById(R.id.word);
        wordTextView.setOnClickListener(this);
        wordTextView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),String.valueOf(word) + " short clicked", Toast.LENGTH_SHORT).show();
        if (mCallback != null)
            mCallback.onClick(getAdapterPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(v.getContext(),String.valueOf(word) + " long clicked", Toast.LENGTH_SHORT).show();
        if (mCallback != null)
            mCallback.onLongClick(getAdapterPosition());
        return true;
    }

    void bindModel(int position) {
        this.word = WordListHelper.getWordFromWordList(position);
        wordTextView.setText(String.valueOf(this.word));
    }

    public void setTextViewTextColor (int color){
        this.wordTextView.setTextColor(color);
    }

    public void setTextViewBackgroundColor (int color){
        this.wordTextView.setBackgroundColor(color);
    }
}
