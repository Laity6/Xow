package com.ol.xow.movedrop;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class RecycleViewMoveDorp implements View.OnTouchListener {

    private RecyclerView recyclerView;
    private List<?> list;
    private int positionTag;
    private MoveLayoutManager moveLayoutManager;
    private View removeView;

    public RecycleViewMoveDorp(RecyclerView recyclerView, List<?> list, View removeView) {
        this.recyclerView = recyclerView;
        this.list = list;
        this.removeView = removeView;

        moveLayoutManager = new MoveLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(moveLayoutManager);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setOnTouchListener(this);
    }

    private boolean isLongPress;
    //为RecycleView绑定触摸事件
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //首先回调的方法 返回int表示是否监听该方向
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;//拖拽
            int swipeFlags = 0;//侧滑删除
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //滑动事件
            int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
            int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);//位置变化
            positionTag = toPosition;
            //数据源位置更换
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(list, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(list, i, i - 1);
                }
            }
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        }

        @Override
        public boolean isLongPressDragEnabled() {
            //todo 长按了Item事件，显示删除组件
            moveLayoutManager.setScrollEnabled(false);//禁止滑动
            isLongPress = true;
            moveDropViewLisener.onMoveView(false);
            return true;
        }
    });

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float xDown = event.getX();
        float yDown = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {// 按下

        } else if (event.getAction() == MotionEvent.ACTION_UP) {// 抬起
            if (isLongPress) {
                if (isTouchPointInView(removeView, xDown, yDown)) {
                    moveDropViewLisener.removeView(positionTag);
                }
            }
            moveDropViewLisener.onNomalView();
            isLongPress = false;
            moveLayoutManager.setScrollEnabled(true);//开启滑动
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) // 移动
        {
            if (isLongPress) {
                moveDropViewLisener.onMoveView(isTouchPointInView(removeView, xDown, yDown));
            }
        }
        return false;
    }

    //(x,y)是否在view的区域内
    private boolean isTouchPointInView(View view, float x, float y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }

    private MoveDropViewLisener moveDropViewLisener;

    public void setMoveDropViewLisener(MoveDropViewLisener moveDropViewLisener) {
        this.moveDropViewLisener = moveDropViewLisener;
    }

}
