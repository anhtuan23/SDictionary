package com.dotakoubou.sentencedictionary;

import android.os.Bundle;

import com.dotakoubou.sentencedictionary.Helper.WordListHelper;
import com.dotakoubou.sentencedictionary.RecyclerView.RecyclerViewActivity;

public class MainActivity extends RecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WordListHelper.setWordList();

        setContentView(R.layout.recycler_view_autofit_drag_select);
        setAdapter(this);
    }
}
