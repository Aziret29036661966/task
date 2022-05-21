package com.example.task.ui.fragment.home.adapter;

import android.annotation.SuppressLint;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.App;
import com.example.task.R;
import com.example.task.ui.fragment.home.HomeFragment;
import com.example.task.ui.model.ModelForTask;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterForTitle extends RecyclerView.Adapter<AdapterForTitle.ViewHolder> {

    private List<ModelForTask> list = new ArrayList<>();
    private final ItsForStoryInterface Click;
    private final ItsForStoryInterface OnLong;

    public ModelForTask getItem(int pos){
        return list.get(pos);
    }

    public AdapterForTitle(ItsForStoryInterface click, ItsForStoryInterface onLong) {
        Click = click;
        OnLong = onLong;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItem(List<ModelForTask> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
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
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.black);
        } else {
            holder.itemView.setBackgroundResource(R.color.gray);
        }
        holder.itemView.setOnClickListener(v -> {
            Click.click(holder.getAdapterPosition());
        });
       holder.itemView.setOnLongClickListener(view -> {
           OnLong.longClick(holder.getLayoutPosition());
           return false;
       });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void sortByAlphabet() {
        list = App.getDatabase().dao().getAllTasksByAlphabet();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setByDate() {
        list = App.getDatabase().dao().getAllTasksByDate();
        notifyDataSetChanged();
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

        public void onBind(String title, @NonNull String created) {
            this.title.setText(title);
            data.setText(created);
        }
    }

    public interface ItsForStoryInterface {
        void longClick(int position);
        void click(int position);

    }

}
