package com.ol.xow.movedrop;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ol.xow.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 拖动View 删除
 */
public class MoveDropActivity extends AppCompatActivity implements MoveDropAdapter.OnAdapterListener {

    private static final String TAG = "MoveDropActivity";
    private MoveDropAdapter moveDropAdapter;
    private RecyclerView recyclerView;
    private List<String> list;
    private int positionTag;
    private CustomGridLayoutManager layoutManager;
    private boolean isLongPress = false;//是否是长按
    private TextView tvButton;
    private int recycleHeight;
    private int buttonHeight;
    private float mHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_drop);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("我是第" + i + "个被拖拽的");
        }

        moveDropAdapter = new MoveDropAdapter(this, list);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new CustomGridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moveDropAdapter);
        moveDropAdapter.notifyDataSetChanged();
        tvButton = findViewById(R.id.tv_button);


        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tvButton.measure(w, h);
        recycleHeight = tvButton.getMeasuredHeight();

        //moveDropAdapter.setOnAdapterListener(this);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        //隐藏控件高度：注意必须在此计算 因为默认是隐藏的 获取的高度是0
        Log.i(TAG, "GGGGGGG:" + buttonHeight);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                float xDown = event.getX();
                float yDown = event.getY();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {// 按下
                    Log.d("TEST", "onTouch" + "   X = " + xDown + "   Y = " + yDown);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {// 抬起
                    if (isLongPress) {
                        if (event.getY() > mHeight) {
                            list.remove(positionTag);
                            moveDropAdapter.notifyDataSetChanged();
                        }
                    }
                    tvButton.setTextColor(Color.WHITE);
                    tvButton.setVisibility(View.GONE);

                    isLongPress = false;
                    layoutManager.setScrollEnabled(true);//开启滑动
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) // 移动
                {
                    if (isLongPress) {
                        Log.i(TAG, "event.getY() " + event.getY() + " mHeight" + mHeight);
                        if (event.getY() > mHeight) {
                            tvButton.setTextColor(Color.BLUE);
                        } else {
                            tvButton.setTextColor(Color.GREEN);
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onItemLongClick(View view, int position) {
        float mHeight = tvButton.getHeight();//隐藏控件高度：注意必须在此计算 因为默认是隐藏的 获取的高度是0
    }

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
            moveDropAdapter.notifyItemMoved(fromPosition, toPosition);//位置变化
            positionTag = toPosition;
            //数据源位置更换
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++)//-1是因为加有头布局
                {
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
            //是否可拖拽
            tvButton.setVisibility(View.VISIBLE);
            if (mHeight == 0) {
                mHeight = tvButton.getTop();
            }
            tvButton.setBackgroundColor(Color.RED);
            layoutManager.setScrollEnabled(false);//禁止滑动
            isLongPress = true;
            return true;
        }
    });
}
