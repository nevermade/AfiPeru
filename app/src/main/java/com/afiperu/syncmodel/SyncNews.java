package com.afiperu.syncmodel;

import com.afiperu.domain.News;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Fernando on 17/11/2015.
 */
public class SyncNews extends SugarRecord<SyncNews> implements Serializable, Comparable<SyncNews> {

    private Integer newsId;
    private String title;
    private String content;
    private Long date;
    private String imageURL;
    private Integer isFavorite;

    public SyncNews() {
    }
    public SyncNews(String imageURL, Integer newsId, String title, String content, Long date) {
        this.imageURL = imageURL;
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.isFavorite = 0;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getDate() {
        return date;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Integer getIsFavorite() {
        return isFavorite;
    }

    public void toggleFavorite(){
        if(isFavorite == 0){
            isFavorite = 1;
        }else{
            isFavorite = 0;
        }
    }

    public void setIsFavorite(Integer isFavorite) {
        this.isFavorite = isFavorite;
    }

    public static SyncNews fromNews(News news){
        return new SyncNews(news.getImageURL(), news.getId(), news.getTitle(), news.getContent(), news.getPostDate()*1000);
    }

    @Override
    public int compareTo(SyncNews o2){
        return o2.date.compareTo(date);
    }
}
