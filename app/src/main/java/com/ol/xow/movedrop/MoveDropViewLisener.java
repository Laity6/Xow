package com.ol.xow.movedrop;

public interface MoveDropViewLisener {
    void onNomalView();
    void onMoveView(boolean isTouchPointInView);
    void removeView(int position);
}
