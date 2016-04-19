package org.zreo.zhihureader;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kakin on 2016/4/18.
 */
public class FindFragment extends Fragment {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,container,false);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.find_viewPager);
        viewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tab = (TabLayout) view.findViewById(R.id.tabs);
        tab.setupWithViewPager(viewPager);
        return view;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 :
                    return new FindrecommendFragment();

                case 1 :
                    return new FindhotFragment();

                case 2 :
                    return new FindcolFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "推荐";
                case 1:
                    return "热门";
                case 2:
                    return "收藏";
            }
            return null;
        }
    }



}
