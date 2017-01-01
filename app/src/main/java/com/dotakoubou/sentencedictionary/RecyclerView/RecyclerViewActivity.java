package com.dotakoubou.sentencedictionary.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;
import com.dotakoubou.sentencedictionary.R;

/**
 * Created by dotua on 31-Dec-16.
 */

public class RecyclerViewActivity extends Activity
    implements RecyclerViewWordAdapter.ClickListener,
        DragSelectRecyclerViewAdapter.SelectionListener {

    private RecyclerViewWordAdapter dragSelectRecyclerViewAdapter;
    private AutofitRecyclerView recyclerView = null;

    public RecyclerView.Adapter getAdapter() {
        return (getRecyclerView().getAdapter());
    }

    public void setAdapter(Activity activity) {
        dragSelectRecyclerViewAdapter =
                new RecyclerViewWordAdapter(activity, this);
        getRecyclerView().setAdapter(dragSelectRecyclerViewAdapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager mgr) {
        getRecyclerView().setLayoutManager(mgr);
    }

    public RecyclerView getRecyclerView() {
        if (recyclerView == null) {
//            recyclerView = new AutofitRecyclerView(this);
            recyclerView = (AutofitRecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
//            setContentView(recyclerView);
        }
        return (recyclerView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save selected indices
        dragSelectRecyclerViewAdapter.saveInstanceState(outState);
//        if (mCab != null) mCab.saveState(outState);
    }

    @Override
    public void onClick(int index) {
        dragSelectRecyclerViewAdapter.toggleSelected(index);
    }

    @Override
    public void onLongClick(int index) {
        recyclerView.setDragSelectActive(true, index);
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
