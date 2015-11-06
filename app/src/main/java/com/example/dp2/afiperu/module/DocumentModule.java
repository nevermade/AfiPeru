package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.interactor.DocumentInteractor;
import com.example.dp2.afiperu.presenter.DocumentPresenter;
import com.example.dp2.afiperu.syncmodel.SyncDocument;
import com.example.dp2.afiperu.ui.adapter.DocumentsAdapter;
import com.example.dp2.afiperu.ui.viewmodel.DocumentView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 21/10/2015.
 */
@Module
public class DocumentModule {
    private DocumentView view;

    public DocumentModule(DocumentView view) {
        this.view = view;
    }

    @Provides
    public DocumentView provideView(){
        return view;
    }

    @Provides
    public DocumentPresenter providePresenter(DocumentView view, DocumentInteractor interactor){
        return new DocumentPresenter(interactor,view);
    }

    @Provides
    public DocumentsAdapter provideAdapter(Context context, DocumentView view) {
        return new DocumentsAdapter(((BaseFragment)view).getActivity(), (BaseFragment) view, new ArrayList<SyncDocument>());
    }
}
