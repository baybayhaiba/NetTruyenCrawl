package com.example.nettruyen.Model;

public class Manga {
    private String imageManga;
    private String titleManga;
    private String linkManga;

    public Manga(String imageManga, String titleManga, String linkManga) {
        this.imageManga = imageManga;
        this.titleManga = titleManga;
        this.linkManga = linkManga;
    }

    public String getLinkManga() {
        return linkManga;
    }

    public void setLinkManga(String linkManga) {
        this.linkManga = linkManga;
    }

    public Manga() {
    }

    public String getImageManga() {
        return imageManga;
    }

    public void setImageManga(String imageManga) {
        this.imageManga = imageManga;
    }

    public String getTitleManga() {
        return titleManga;
    }

    public void setTitleManga(String titleManga) {
        this.titleManga = titleManga;
    }

    @Override
    public String toString() {
        return "Manga{" +
                "imageManga='" + imageManga + '\'' +
                ", titleManga='" + titleManga + '\'' +
                ", linkManga='" + linkManga + '\'' +
                '}';
    }
}
