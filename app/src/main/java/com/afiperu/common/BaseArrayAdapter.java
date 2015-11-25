package com.afiperu.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.afiperu.syncmodel.SyncDocument;

import java.util.List;

/**
 * Created by Fernando on 26/09/2015.
 */
public abstract class BaseArrayAdapter<T> extends ArrayAdapter<T> {

    private BaseFragment fragment;
    private int itemLayout;

    public BaseArrayAdapter(Context context, BaseFragment fragment, int itemLayout, List<T> objects){
        super(context, 0, objects);
        this.fragment = fragment;
        this.itemLayout = itemLayout;
    }

    public final BaseFragment getFragment(){
        return fragment;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(itemLayout, null);
        }
        prepareItemView(convertView, getItem(position), position);
        return convertView;
    }

    public abstract void prepareItemView(View convertView, T item, int position);

    public void downloadFile(String URL, String fileName, boolean overwrite){
        fragment.downloadFile(URL, fileName, overwrite);
    }

    public void downloadDocument(SyncDocument document, String URL){
        fragment.downloadDocument(document, URL);
    }

    public void setImage(ImageView v, String URL, String fileName){
        fragment.setImage(v, URL, fileName);
    }

    public void update(List<T> objects){
        if(objects != null){
            clear();
            addAll(objects);
            notifyDataSetChanged();
        }
    }

}
