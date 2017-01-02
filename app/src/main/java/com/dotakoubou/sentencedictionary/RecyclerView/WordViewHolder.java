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
        extends RecyclerView.ViewHolder {

    CardView cardView = null;
    TextView wordTextView = null;

    WordViewHolder(View wordTextView) {
        super(wordTextView);
        this.cardView = (CardView) wordTextView.findViewById(R.id.wordCardView);
        this.wordTextView = (TextView) wordTextView.findViewById(R.id.word);
    }

    void bindModel(int position) {
        String word = WordListHelper.getWordFromWordList(position);
        wordTextView.setText(word);
    }

}
