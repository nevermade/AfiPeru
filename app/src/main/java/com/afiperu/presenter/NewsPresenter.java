package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.NewsInteractor;
import com.afiperu.syncmodel.SyncNews;
import com.afiperu.ui.viewmodel.NewsView;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class NewsPresenter extends BasePresenter{

    NewsInteractor interactor;
    NewsView view;

    public NewsPresenter(NewsInteractor interactor, NewsView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void getAllNews(Context context){
        interactor.getAllNews(context, this);

        /*ArrayList<SyncNews> news = new ArrayList<>();
        news.add(new SyncNews("https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-xat1/v/t1.0-9/13779_10153228555392486_8679903887061635913_n.jpg?oh=00b977b776d2b46e53c88f229bc38250&oe=5668F7B7&__gda__=1453937731_0addbe5c62688f1ca9aa12cef23593eb",
                15, "Paseo pinoteco al parque de las leyendas", "abcdef\n\nabcdef", 1447563600000L));
        news.add(new SyncNews("https://scontent-mia1-1.xx.fbcdn.net/hphotos-xpf1/v/t1.0-9/11024597_10153107115432486_3774679476823351402_n.jpg?oh=2e13703d43f85c64bde46ce3b0ff4738&oe=5697777F",
                13, "Voluntarios accederan a puntajes para becas y viviendas", "abcdef\n\nabcdef", 1447304400000L));
        news.add(new SyncNews("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-xfa1/v/t1.0-9/11150479_10153215037187486_66126204997766216_n.jpg?oh=efc6181115bfcd36dd12eeda1f2be26d&oe=56A16C75&__gda__=1449207363_cde5ddb130a171ea4e9fc3d09933356b",
                12, "Recurso multimedia que utiliza el cuento como motivación inicial, dirigido a niños", "abcdef\n\nabcdef", 1446872400000L));

        List<SyncNews> favoriteNews = SyncNews.find(SyncNews.class, "is_favorite = ?", "1");
        SyncNews.deleteAll(SyncNews.class);

        for(SyncNews newsArticle : news){
            for(SyncNews favorite : favoriteNews){
                if(newsArticle.getNewsId() == favorite.getNewsId()){
                    newsArticle.setIsFavorite(1);
                    break;
                }
            }
            newsArticle.save();
        }

        view.displayNews(news);*/
    }

    public void onNewsFound(List<SyncNews> news){
        if(news!=null)
            view.displayNews(news);
    }

    public void onNewsErrorOrFailure(){
        view.displayErrorOrFailure();
    }
}
