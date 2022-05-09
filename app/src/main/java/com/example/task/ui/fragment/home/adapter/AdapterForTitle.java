package com.example.task.ui.fragment.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.ui.model.ModelForTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterForTitle extends RecyclerView.Adapter<AdapterForTitle.ViewHolder> {

    private final List<ModelForTask> list = new ArrayList<>();


   public void addItem(ModelForTask model) {
        list.add(model);
        notifyItemChanged(list.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_for_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position).getTitle(), list.get(position).getCreated());
        if(position % 2 == 0)
        {
            holder.itemView.setBackgroundResource(R.color.black);
        }
        else
        {
            holder.itemView.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, data;
        LinearLayout rootView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView.findViewById(androidx.appcompat.R.id.list_item);
            title = itemView.findViewById(R.id.txt_task_tv);
            data = itemView.findViewById(R.id.txt_created);
        }

        public void onBind(String title, @NonNull Date created) {
            this.title.setText(title);
            data.setText(created.toString());
        }
    }

}
