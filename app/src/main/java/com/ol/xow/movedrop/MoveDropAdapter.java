package com.ol.xow.movedrop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ol.xow.R;

import java.util.List;

public class MoveDropAdapter extends RecyclerView.Adapter implements View.OnLongClickListener {

    private static final int mLayout = R.layout.adapter_movedrop;

    private Context context;
    private List<String> list;


    public MoveDropAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(mLayout, null);
        return new MoveDropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof MoveDropViewHolder) {
            MoveDropViewHolder holder = (MoveDropViewHolder) viewHolder;
            String title = list.get(position);
            holder.tvTitle.setText(title);

            holder.itemView.setTag(mLayout, position);
            holder.itemView.setOnLongClickListener(this);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public boolean onLongClick(View view) {
        int position = (int) view.getTag(mLayout);
        if (onAdapterListener != null) {
            onAdapterListener.onItemLongClick(view, position);
        }
        return false;
    }


    public class MoveDropViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public MoveDropViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

    private OnAdapterListener onAdapterListener;

    public void setOnAdapterListener(OnAdapterListener onAdapterListener) {
        this.onAdapterListener = onAdapterListener;
    }

    public interface OnAdapterListener {
        void onItemLongClick(View view, int position);
    }
}
