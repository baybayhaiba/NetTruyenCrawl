package com.example.nettruyen.Mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nettruyen.ActivityDescription.ActivityDescription;
import com.example.nettruyen.Adapter.AdapterManga;
import com.example.nettruyen.Model.Manga;
import com.example.nettruyen.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    private RecyclerView recyclerView;
    private AdapterManga adapterManga;
    private ProgressBar progressBar;
    private String URL = "http://www.nettruyen.com/?page=1";
    private MainActivityPresenter presenter;
    public static final String SEND_LINK = "send_link_manga";
    public static final String TITLE_MANGA = "title_manga";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitUI();
    }

    private void InitUI() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapterManga = new AdapterManga(this);
        recyclerView.setAdapter(adapterManga);
        presenter = new MainActivityPresenter(this, this);
        presenter.HandleGetData(URL);
    }


    @Override
    public void OnSuccess(List<Manga> mangas) {
        adapterManga.setMangas(mangas);
        progressBar.setVisibility(View.GONE);

        adapterManga.setListener(manga -> {
            Intent intent = new Intent(this, ActivityDescription.class);
            intent.putExtra(SEND_LINK, manga.getLinkManga());
            intent.putExtra(TITLE_MANGA, manga.getTitleManga());
            startActivity(intent);
        });
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Pending() {
        Toast.makeText(this, "Pending !!!!", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }
}