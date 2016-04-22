package org.zreo.zhihureader;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by kakin on 2016/4/22.
 */
public class ViewPageAdapter extends PagerAdapter {

    private ArrayList<View> views;
    public ViewPageAdapter(ArrayList<View> views){
        this.views = views;
    }

    @Override
    public int getCount() {

        if (views!= null){
            return views.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position),0);
        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }


}
