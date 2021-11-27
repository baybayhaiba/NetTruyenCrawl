package com.example.nettruyen.Mainactivity;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.example.nettruyen.Model.Manga;
import com.example.nettruyen.Utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.observers.BlockingBaseObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityView.IMainActivityPresenter {

    private Context context;
    private MainActivityView view;

    public MainActivityPresenter(Context context, MainActivityView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void HandleGetData(String URL) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Manga> mangas = new ArrayList<>();

        Observable<ArrayList<Manga>> managa = Observable.create(emitter -> {
            Document document = Jsoup.connect(URL).data("query", "Java").userAgent("Chrome").
                    cookie("auth", "token").timeout(Utils.TIME_LIMIT).post();

            Log.d("HUY", "onNext: 3213123121");

            Elements elements = document.getElementsByClass("items").get(0).children();

            for (Element e : elements) {
                Elements list_manga = e.getElementsByClass("image");

                for (Element content : list_manga) {
                    String link_manga = content.getElementsByTag("a").attr("href");
                    String image_manga = content.getElementsByTag("img").attr("data-original");
                    String title_manga = content.getElementsByTag("a").attr("title");

                    Manga manga = new Manga(image_manga, title_manga, link_manga);
                    mangas.add(manga);
                }

                emitter.onNext(mangas);
            }
        });

        Observable.just(URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BlockingBaseObserver<String>() {
                    @Override
                    public void onNext(@NonNull String s) {
                        try {
                            Document document = Jsoup.connect(s).data("query", "Java").userAgent("Chrome").
                                    cookie("auth", "token").timeout(Utils.TIME_LIMIT).post();

                            Log.d("HUY", "onNext: 3213123121");

                            Elements elements = document.getElementsByClass("items").get(0).children();

                            for (Element e : elements) {
                                Elements list_manga = e.getElementsByClass("image");

                                for (Element content : list_manga) {
                                    String link_manga = content.getElementsByTag("a").attr("href");
                                    String image_manga = content.getElementsByTag("img").attr("data-original");
                                    String title_manga = content.getElementsByTag("a").attr("title");

                                    Manga manga = new Manga(image_manga, title_manga, link_manga);
                                    mangas.add(manga);
                                }
                            }
                            view.OnSuccess(mangas);
                        } catch (Exception e) {
                            view.onFailed(e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onFailed(e.getMessage());
                    }
                });
    }
}
