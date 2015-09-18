package com.river.ilikeit.main.photo;

public class PhotoInfo {
    public String id;
    public String content;
    public String link;
    public String author;
    public String catalogues;
    public int nLike;
    public int nView;
    public boolean isLiked;
    public boolean isFavourite;
    public long uploadTime;

    public PhotoInfo(String id, String content, String link, String author, int nLike, int nView, boolean isLiked, boolean isFavourite) {
        this.id = id;
        this.content = content;
        this.link = link;
        this.author = author;
        this.nLike = nLike;
        this.nView = nLike;
        this.isLiked = isLiked;
        this.isFavourite = isFavourite;
    }

    @Override
    public String toString() {
        return content;
    }
}
