package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.domain.News;
import com.afiperu.presenter.NewsPresenter;
import com.afiperu.rest.AfiApiServiceEndPoints;
import com.afiperu.syncmodel.SyncNews;
import com.afiperu.util.NetworkManager;

import java.util.Collections;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class NewsInteractorImpl implements NewsInteractor {
    AfiApiServiceEndPoints service;

    public NewsInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void getAllNews(Context context, final NewsPresenter presenter) {
        if(NetworkManager.isNetworkConnected(context)){
            Call<List<News>> call = service.getAllNews();
            call.enqueue(new Callback<List<News>>() {
                @Override
                public void onResponse(Response<List<News>> response, Retrofit retrofit) {
                    List<News> result = response.body();
                    if(result != null) {
                        List<SyncNews> favoriteNews = SyncNews.find(SyncNews.class, "is_favorite = ?", "1");
                        SyncNews.deleteAll(SyncNews.class);

                        for(News news : result){
                            SyncNews newsArticle = SyncNews.fromNews(news);
                            for(SyncNews favorite : favoriteNews){
                                if(newsArticle.getNewsId() == favorite.getNewsId()){
                                    newsArticle.setIsFavorite(1);
                                    break;
                                }
                            }
                            newsArticle.save();
                        }

                        List<SyncNews> news = SyncNews.listAll(SyncNews.class);
                        Collections.sort(news);
                        presenter.onNewsFound(news);
                    }else{
                        presenter.onNewsErrorOrFailure();
                    }
                }

                @Override
                public void onFailure(Throwable t){
                    presenter.onNewsErrorOrFailure();
                }
            });
        }else{
            List<SyncNews> news = SyncNews.listAll(SyncNews.class);
            Collections.sort(news);
            presenter.onNewsFound(news);
        }
     }
}
