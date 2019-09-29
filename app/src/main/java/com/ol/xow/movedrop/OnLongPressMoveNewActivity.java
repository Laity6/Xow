package com.ol.xow.movedrop;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ol.xow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 拖动View 删除
 */
public class OnLongPressMoveNewActivity extends AppCompatActivity implements RecycleViewLongPressMove.OnLongPressMoveLisener {

    private static final String TAG = "OnLongPressMoveNewActivity";
    private MoveDropAdapter moveDropAdapter;
    private RecyclerView recyclerView;
    private List<String> list;
    private TextView tvButton;

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
        recyclerView.setAdapter(moveDropAdapter);
        moveDropAdapter.notifyDataSetChanged();
        tvButton = findViewById(R.id.tv_button);

        RecycleViewLongPressMove recycleViewLongPressMove = new RecycleViewLongPressMove(recyclerView, list, tvButton);
        recycleViewLongPressMove.setOnLongPressMoveLisener(this);
    }


    @Override
    public void onNomalView() {
        Log.d(TAG, "onNomalView");
        tvButton.setBackgroundColor(Color.WHITE);
        tvButton.setTextColor(Color.GREEN);
    }

    @Override
    public void onMoveView(boolean isTouchPointInView) {
        Log.d(TAG, "onMoveView");
        if(isTouchPointInView){
            tvButton.setBackgroundColor(Color.RED);
            tvButton.setTextColor(Color.GRAY);
        }else{
            tvButton.setBackgroundColor(Color.RED);
            tvButton.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void onOperation(int position) {
        tvButton.setBackgroundColor(Color.WHITE);
        tvButton.setTextColor(Color.BLUE);

        Log.d(TAG, "onOperation" + ">" + position);
        list.remove(position);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
