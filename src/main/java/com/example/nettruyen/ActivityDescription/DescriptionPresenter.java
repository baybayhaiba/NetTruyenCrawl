package com.example.nettruyen.ActivityDescription;

import android.content.Context;
import android.os.StrictMode;

import com.example.nettruyen.Model.Chapter;
import com.example.nettruyen.Utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.observers.BlockingBaseObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DescriptionPresenter implements DescriptionView.IDescriptionPresenter {

    private Context context;
    private DescriptionView view;

    public DescriptionPresenter(Context context, DescriptionView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void handleDataTitle(String url) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Observable.just(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BlockingBaseObserver<String>() {
                    @Override
                    public void onNext(@NonNull String s) {
                        try {
                            Document document = Jsoup.connect(s).data("query", "Java").userAgent("Chrome").
                                    cookie("auth", "token").timeout(Utils.TIME_LIMIT).post();

                            //Init
                            ArrayList<String> title_detail = new ArrayList<>();
                            ArrayList<Chapter> list_chapter = new ArrayList<>();

                            //title_detail
                            Elements elementsTitle = document.getElementsByClass("detail-info")
                                    .get(0).getElementsByTag("li");

                            for (Element title : elementsTitle) {
                                title_detail.add(title.text());
                            }

                            view.onSuccessTitle(title_detail);

                            //list_chapter
                            Elements elementsChapter = document.getElementsByClass("list-chapter").get(0)
                                    .getElementsByTag("a");

                            for (Element chapter : elementsChapter) {
                                list_chapter.add(new Chapter(chapter.text(), chapter.attr("href")));
                            }

                            view.onSuccessChapter(list_chapter);
                            //description
                            view.onSuccessDesccription(document.getElementsByClass
                                    ("detail-content").get(0).getElementsByTag("p").text());

                        } catch (IOException e) {
                            view.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onFailure(e.getMessage());
                    }
                });
    }
}
