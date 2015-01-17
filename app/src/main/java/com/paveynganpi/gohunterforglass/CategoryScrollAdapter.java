package com.paveynganpi.gohunterforglass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.google.android.glass.widget.CardScrollAdapter;

/**
 * Created by paveynganpi on 1/17/15.
 */
public class CategoryScrollAdapter extends CardScrollAdapter{

    Context mContext;
    CategoryManager mCategoryManager;

    public CategoryScrollAdapter(Context context, CategoryManager categoryManager){

        this.mContext = context;
        this.mCategoryManager = categoryManager;

    }

    @Override
    public int getCount() {
        return mCategoryManager.getCount();
    }

    @Override
    public Object getItem(int i) {
        return mCategoryManager.getCategoryAt(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getPosition(Object o) {

        int position = AdapterView.INVALID_POSITION;
        if(o instanceof Category){

            for(int i =0 ; i< getCount(); i++){

                if(getItem(i) == o){
                    position = i;
                    break;
                }

            }

        }

        return 0;
    }
}
