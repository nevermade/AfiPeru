package com.afiperu.module;

import android.content.Context;

import com.afiperu.common.BaseFragment;
import com.afiperu.interactor.DocumentInteractor;
import com.afiperu.presenter.DocumentPresenter;
import com.afiperu.syncmodel.SyncDocument;
import com.afiperu.ui.adapter.DocumentsAdapter;
import com.afiperu.ui.viewmodel.DocumentView;

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
