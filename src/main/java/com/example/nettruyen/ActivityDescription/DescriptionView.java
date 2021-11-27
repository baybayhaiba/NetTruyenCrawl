package com.example.nettruyen.ActivityDescription;

import com.example.nettruyen.Model.Chapter;

import java.util.ArrayList;

public interface DescriptionView {
    public void onSuccessTitle(ArrayList<String> title_detail);

    public void onSuccessChapter(ArrayList<Chapter> list_chapter);

    public void onSuccessDesccription(String description);

    public void onFailure(String error);


    public interface IDescriptionPresenter {
        public void handleDataTitle(String url);
    }
}
