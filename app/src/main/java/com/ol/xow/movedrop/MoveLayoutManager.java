package com.ol.xow.movedrop;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class MoveLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public MoveLayoutManager(Context context) {
        this(context, LinearLayoutManager.VERTICAL, false);
    }

    public MoveLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        isScrollEnabled = scrollEnabled;
    }
}
