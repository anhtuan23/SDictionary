package com.dotakoubou.sentencedictionary.RecyclerView;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dotakoubou.sentencedictionary.Helper.WordListHelper;
import com.dotakoubou.sentencedictionary.R;

/**
 * Created by dotua on 31-Dec-16.
 */

class WordViewHolder
        extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    CardView cardView = null;
    TextView wordTextView = null;
    private char word = 'h';
    private final RecyclerViewWordAdapter.ClickListener mCallback;

    WordViewHolder(View wordTextView, RecyclerViewWordAdapter.ClickListener callback) {
        super(wordTextView);
        this.mCallback = callback;
        this.cardView = (CardView) wordTextView.findViewById(R.id.wordCardView);
        this.wordTextView = (TextView) wordTextView.findViewById(R.id.word);
        wordTextView.setOnClickListener(this);
        wordTextView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (mCallback != null)
            mCallback.onClick(getAdapterPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        if (mCallback != null)
            mCallback.onLongClick(getAdapterPosition());
        return true;
    }

    void bindModel(int position) {
        this.word = WordListHelper.getWordFromWordList(position);
        wordTextView.setText(String.valueOf(this.word));
    }
}
