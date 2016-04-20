package org.zreo.zhihureader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kakin on 2016/4/20.
 */
public class FocusFragment extends Fragment {

    private ViewPager viewPager;
    private List<Fragment> fragments;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout tab;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus,container,false);

        viewPager = (ViewPager) view.findViewById(R.id.focus_viewPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new FocusquesFragment());
        fragments.add(new FocuscolFragment());
        fragments.add(new FocustopicFragment());
        fragments.add(new FocuscolumnFragment());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(mSectionsPagerAdapter);

        tab = (TabLayout) view.findViewById(R.id.focus_tabs);
        tab.setupWithViewPager(viewPager);
        tab.getTabAt(0).setText("问题");
        tab.getTabAt(1).setText("收藏");
        tab.getTabAt(2).setText("话题");
        tab.getTabAt(3).setText("专栏");

        return view;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments = new ArrayList<Fragment>();
        public SectionsPagerAdapter(FragmentManager fm, List<Fragment> mfragments) {
            super(fm);
            fragments = mfragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }


}
