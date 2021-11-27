package com.example.nettruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nettruyen.Model.Chapter;
import com.example.nettruyen.Model.Manga;
import com.example.nettruyen.R;

import java.util.List;

public class AdapterChapter extends RecyclerView.Adapter<AdapterChapter.MyViewHolder> {

    private Context context;
    private List<Chapter> chapters;
    private ListenerChapter listener;

    public interface ListenerChapter {
        void onClick(Chapter manga);
    }

    public AdapterChapter(Context context, List<Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    public void setListener(ListenerChapter listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterChapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChapter.MyViewHolder holder, int position) {
        Chapter chapter = chapters.get(position);

        holder.tvChapter.setText(chapter.getChapter());
        holder.tvChapter.setOnClickListener(v -> {
            listener.onClick(chapter);
        });
    }

    @Override
    public int getItemCount() {
        if (chapters != null)
            return chapters.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
        }
    }
}
