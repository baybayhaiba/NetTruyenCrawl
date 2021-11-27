package com.example.nettruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nettruyen.Model.Manga;
import com.example.nettruyen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterManga extends RecyclerView.Adapter<AdapterManga.ViewHolder> {

    private Context context;
    private List<Manga> mangas;
    private ListenerManga listener;

    public interface ListenerManga {
        void onClick(Manga manga);
    }

    public AdapterManga(Context context) {
        this.context = context;
    }

    public AdapterManga(Context context, List<Manga> mangas) {
        this.context = context;
        this.mangas = mangas;
    }

    public void setListener(ListenerManga listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manga, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Manga manga = mangas.get(position);

        String url = "https:" + manga.getImageManga();
        String title = manga.getTitleManga().replace("Truyện tranh", "");
        Picasso.get().load(url).into(holder.imgManga);
        holder.tvTitleMaga.setText(title);

        holder.cardView.setOnClickListener(v -> {
            listener.onClick(manga);
        });

    }

    public void setMangas(List<Manga> mangas) {
        this.mangas = mangas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mangas != null)
            return mangas.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgManga;
        private TextView tvTitleMaga;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgManga = itemView.findViewById(R.id.img_manga);
            tvTitleMaga = itemView.findViewById(R.id.tv_title_manga);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
