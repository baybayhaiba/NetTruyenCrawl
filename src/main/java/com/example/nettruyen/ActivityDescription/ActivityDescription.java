package com.example.nettruyen.ActivityDescription;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nettruyen.ActivityReadManga;
import com.example.nettruyen.Adapter.AdapterChapter;
import com.example.nettruyen.Mainactivity.MainActivity;
import com.example.nettruyen.Model.Chapter;
import com.example.nettruyen.R;

import java.util.ArrayList;

public class ActivityDescription extends AppCompatActivity implements DescriptionView {

    private RecyclerView recyclerViewChapter;
    private TextView tvTitle, tvViewed, tvUpdated, tvStatus, tvDescription;
    private DescriptionPresenter presenter;
    private AdapterChapter adapterChapter;
    private Button btnFirst, btnLast;
    private NestedScrollView nestedScrollView;
    public static final String URL_COMIC = "URL_COMIC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        InitUI();

    }

    private void InitUI() {
        recyclerViewChapter = findViewById(R.id.recyclerViewChapter);
        recyclerViewChapter.setHasFixedSize(true);
        recyclerViewChapter.setLayoutManager(new LinearLayoutManager(this));
        tvTitle = findViewById(R.id.tvTitle);
        tvViewed = findViewById(R.id.tvViewed);
        tvUpdated = findViewById(R.id.tvUpdate);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        tvStatus = findViewById(R.id.tvStatus);
        tvDescription = findViewById(R.id.tvDescription);
        btnFirst = findViewById(R.id.btnReadBegin);
        btnLast = findViewById(R.id.btnReadLastest);
        presenter = new DescriptionPresenter(this, this);
        presenter.handleDataTitle(getIntent().getExtras().getString(MainActivity.SEND_LINK));
        tvTitle.setText(getIntent().getExtras().getString(MainActivity.TITLE_MANGA));
    }

    @Override
    public void onSuccessTitle(ArrayList<String> title_detail) {
//        Tác giả Đang cập nhật
//        Tình trạng Đang tiến hành
//        Thể loại Action - Adventure - Manhua - Mystery - Shounen - Truyện Màu
//        Lượt xem 45.561.299

        tvStatus.setText(title_detail.get(1));
        tvViewed.setText(title_detail.get(3));
    }

    @Override
    public void onSuccessChapter(ArrayList<Chapter> list_chapter) {
        //remove watch more
        Chapter lastChapter = list_chapter.get(list_chapter.size() - 1);
        if (lastChapter.getLinkChapter().equals("#"))
            list_chapter.remove(list_chapter.size() - 1);

        AdapterChapter adapterChapter = new AdapterChapter(this, list_chapter);
        recyclerViewChapter.setAdapter(adapterChapter);

        Intent intent = new Intent(this, ActivityReadManga.class);

        adapterChapter.setListener(manga -> {
            intent.putExtra(URL_COMIC, manga.getLinkChapter());
            startActivity(intent);
        });

        btnFirst.setOnClickListener(v -> {
            intent.putExtra(URL_COMIC, lastChapter.getLinkChapter());
            startActivity(intent);
        });

        btnLast.setOnClickListener(v -> {
            intent.putExtra(URL_COMIC, list_chapter.get(0).getLinkChapter());
            startActivity(intent);
        });

    }

    @Override
    public void onSuccessDesccription(String description) {
        tvDescription.setText(description);
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}