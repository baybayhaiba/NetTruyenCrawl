package com.example.nettruyen.Model;

public class Chapter {
    private String chapter;
    private String linkChapter;

    public Chapter(String chapter, String linkChapter) {
        this.chapter = chapter;
        this.linkChapter = linkChapter;
    }

    public Chapter() {
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getLinkChapter() {
        return linkChapter;
    }

    public void setLinkChapter(String linkChapter) {
        this.linkChapter = linkChapter;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "chapter='" + chapter + '\'' +
                ", linkChapter='" + linkChapter + '\'' +
                '}';
    }
}
