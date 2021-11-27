package com.example.nettruyen.Mainactivity;

import com.example.nettruyen.Model.Manga;

import java.util.List;

public interface MainActivityView {
    public void OnSuccess(List<Manga> mangas);

    public void onFailed(String error);

    public void Pending();


    public interface IMainActivityPresenter {
        public void HandleGetData(String URL);
    }
}
