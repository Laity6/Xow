package com.ol.xow.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ol.xow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础的RecyclerView加载数据
 */
public class RecycleViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 99; i++) {
            list.add(String.format("测试标题%d", i));
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        //横向滚动
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        //竖向滚动
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
//        todo recyclerView 有哪些方法，对应的函数都有什么
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //适配器
    class RecyclerAdapter extends RecyclerView.Adapter {
        private Context context;
        private List<String> list;

        public RecyclerAdapter(Context context, List<String> list) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //TODO 这里第三个参数为什么是root
            View view = LinearLayout.inflate(context, R.layout.adapter_recyclerview, null);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            RecyclerViewHolder holder = (RecyclerViewHolder) viewHolder;
            holder.tvTitle.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }
    }

    //视图
    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);

        }
    }
}
