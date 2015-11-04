package com.example.dp2.afiperu.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.dp2.afiperu.common.BaseFragment;

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
            LayoutInflater inflater = (LayoutInflater) parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(itemLayout, null);
        }
        prepareItemView(convertView, getItem(position), position);
        return convertView;
    }

    public abstract void prepareItemView(View convertView, T item, int position);

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
