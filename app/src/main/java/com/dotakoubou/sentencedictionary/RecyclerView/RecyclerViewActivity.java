package com.dotakoubou.sentencedictionary.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;
import com.dotakoubou.sentencedictionary.Helper.WordListHelper;
import com.dotakoubou.sentencedictionary.R;
import com.dotakoubou.sentencedictionary.RecyclerView.TouchHandler.RecyclerViewClickListener;
import com.dotakoubou.sentencedictionary.RecyclerView.TouchHandler.RecyclerViewTouchListener;

/**
 * Created by dotua on 31-Dec-16.
 */

public class RecyclerViewActivity extends Activity
        implements DragSelectRecyclerViewAdapter.SelectionListener {

    private RecyclerViewWordAdapter dragSelectRecyclerViewAdapter;
    private AutofitRecyclerView recyclerView = null;

    public RecyclerView.Adapter getAdapter() {
        return (getRecyclerView().getAdapter());
    }

    public void setAdapter(Activity activity) {
        dragSelectRecyclerViewAdapter =
                new RecyclerViewWordAdapter(activity);
        getRecyclerView().setAdapter(dragSelectRecyclerViewAdapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager mgr) {
        getRecyclerView().setLayoutManager(mgr);
    }

    public RecyclerView getRecyclerView() {
        if (recyclerView == null) {
            recyclerView = (AutofitRecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);

            recyclerView.addOnItemTouchListener(
                    new RecyclerViewTouchListener(
                            getApplicationContext(),
                            recyclerView,
                            new RecyclerViewClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                }

                                @Override
                                public void onSingleClick(View view, int position) {
                                    int colorDictSendID = view.getContext()
                                            .getResources()
                                            .getInteger(R.integer.COLOR_DICT_INTENT_ID);
                                    if (dragSelectRecyclerViewAdapter.isNoneSelected()) {
                                        //if the clicked word is the first, send word to search intent
                                        WordListHelper.sendWordIntent(view.getContext(), new int[]{position}, colorDictSendID);
                                    } else if (dragSelectRecyclerViewAdapter.isIndexSelected(position)) {
                                        /*if the clicked word is within the string of words selected,
                                            send the string to search intent*/
                                        int[] selectedPostions = dragSelectRecyclerViewAdapter.getSelectedArray();
                                        WordListHelper.sendWordIntent(view.getContext(), selectedPostions, colorDictSendID);
                                    } else {
                                        //if the clicked word is outside of selected string, clear selection
                                        dragSelectRecyclerViewAdapter.decideSelectStateWhenClicked(position);
                                    }
                                }

                                @Override
                                public void onLongClick(View view, int position) {
                                    Vibrator v = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(30); // Vibrate for 30 milliseconds
                                    /*if long click item is out side of the selected string,
                                        remove selected string then begin new choosing action*/
                                    dragSelectRecyclerViewAdapter.clearSelected();
                                    recyclerView.setDragSelectActive(true, position)    ;
                                }

                                @Override
                                public void onDoubleClick(View view, int position) {
                                    Vibrator v = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(30); // Vibrate for 30 milliseconds
                                    int sendTextIntentID = view.getContext()
                                            .getResources()
                                            .getInteger(R.integer.SEND_TEXT_INTENT_ID);
                                    if (dragSelectRecyclerViewAdapter.isIndexSelected(position)) {
                                        /*if the clicked word is within the string of words selected,
                                            send the string to search intent*/
                                        int[] selectedPostions = dragSelectRecyclerViewAdapter.getSelectedArray();
                                        WordListHelper.sendWordIntent(view.getContext(), selectedPostions, sendTextIntentID);
                                    } else {//if the clicked word is the first, send word to search intent
                                        WordListHelper.sendWordIntent(view.getContext(), new int[]{position}, sendTextIntentID);
                                    }
                                }

                                @Override
                                public void clearSelection() {
                                    dragSelectRecyclerViewAdapter.clearSelected();
                                }
                            }));
        }
        return (recyclerView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save selected indices
        dragSelectRecyclerViewAdapter.saveInstanceState(outState);
    }


    @Override
    public void onDragSelectionChanged(int count) {
        if (count > 0) {
//            if (mCab == null) {
//                mCab = new MaterialCab(this, R.id.cab_stub)
//                        .setMenu(R.menu.cab)
//                        .setCloseDrawableRes(R.drawable.ic_close)
//                        .start(this);
//            }
//            mCab.setTitleRes(R.string.cab_title_x, count);
//        } else if (mCab != null && mCab.isActive()) {
//            mCab.reset().finish();
//            mCab = null;
        }
    }

    @Override
    public void onBackPressed() {
//        if (mAdapter.getSelectedCount() > 0)
//            mAdapter.clearSelected();
//        else
        super.onBackPressed();
    }
}
