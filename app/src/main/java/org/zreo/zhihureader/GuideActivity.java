package org.zreo.zhihureader;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * Created by kakin on 2016/4/22.
 */

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private ArrayList<View> views;
    private View view1,view2,view3;
    private ImageView[] dots;
    private int[] ids = {R.id.point1,R.id.point2,R.id.point3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
        initDots();
        viewPager.addOnPageChangeListener(this);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
            }
        });

    }

    private void initView(){
        LayoutInflater lif = LayoutInflater.from(this);
        views = new ArrayList<View>();
        view1 = lif.inflate(R.layout.guide_view1,null);
        view2 = lif.inflate(R.layout.guide_view2,null);
        view3 = lif.inflate(R.layout.guide_view3,null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPageAdapter = new ViewPageAdapter(views);
        viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        viewPager.setAdapter(viewPageAdapter);

    }

    private void initDots(){
        dots = new ImageView[views.size()];
        for (int i = 0; i<views.size(); i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {  //页面被滑动时调用

    }

    @Override
    public void onPageSelected(int position) {                            //当前页面被选中时候调用

        for (int i = 0; i<ids.length; i++){
            if (i == position){
                dots[i].setImageResource(R.drawable.login_point_selected);
            }else {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {                   //滑动状态改变时候调用

    }
}
