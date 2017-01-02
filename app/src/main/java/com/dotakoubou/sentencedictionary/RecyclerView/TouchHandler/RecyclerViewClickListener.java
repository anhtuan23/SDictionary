package com.dotakoubou.sentencedictionary.RecyclerView.TouchHandler;

import android.view.View;

/**
 * Created by dotua on 02-Jan-17.
 */

public interface RecyclerViewClickListener {
    void onClick(View view, int position);

    void onSingleClick(View view, int position);

    void onLongClick(View view, int position);

    void onDoubleClick(View view, int position);

    void clearSelection();
}
