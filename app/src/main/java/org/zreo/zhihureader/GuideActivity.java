package org.zreo.zhihureader;

import android.animation.ArgbEvaluator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by kakin on 2016/4/22.
 */

public class GuideActivity extends FragmentActivity {

    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private ArrayList<View> views;
    private View view1,view2,view3;
    private ImageView[] dots;
    private ImageView pointing;
    private int[] ids = {R.id.point1,R.id.point2,R.id.point3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
        initDots();

        viewPager.setPageTransformer(true, new parallaxTransformer(0.4f));
        viewPager.addOnPageChangeListener(new pageChangeListener());


        findViewById(R.id.gotologin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog loginDialog = new LoginDialog(GuideActivity.this,R.style.LoginDialog);
                loginDialog.show();

            }
        });
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
        pointing = (ImageView) findViewById(R.id.pointing);
        dots = new ImageView[views.size()];
        for (int i = 0; i<views.size(); i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    class parallaxTransformer implements ViewPager.PageTransformer{

        float parallaxCoefficient;

        ArgbEvaluator colorEvaluator = new ArgbEvaluator();
        int colorTextBegin, colorTextEnd;

        public parallaxTransformer(float parallaxCoefficient){
            this.parallaxCoefficient = parallaxCoefficient;

            colorTextBegin = getResources().getColor(R.color.colorTextbegin);
            colorTextEnd = getResources().getColor(R.color.colorTextEnd);


        }
        @Override
        public void transformPage(View page, float position) {

            float scrollxoffset = page.getWidth()*parallaxCoefficient;

            TextView tv;
            tv= (TextView) page.findViewById(R.id.tvZhiHu);
            if (tv != null){
                tv.setTranslationX(-scrollxoffset*position);
                Integer color = (Integer) colorEvaluator.evaluate(-position, colorTextBegin, colorTextEnd);
                tv.setTextColor(color);
            }

        }
    }
    class pageChangeListener implements ViewPager.OnPageChangeListener{

        int mPageWidth, mTotalScrollWidth;
        public pageChangeListener(){
            Point size = new Point();
            Display display = getWindowManager().getDefaultDisplay();
            display.getSize(size);
            mPageWidth = size.x;
            mTotalScrollWidth = mPageWidth*viewPageAdapter.getCount();

        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {  //页面被滑动时调用

            float ratio = (mPageWidth * position + positionOffsetPixels) / (float) mTotalScrollWidth;
            Log.d("实时数据：","onPageScrolled当前点击页面："+position+"--当前页面偏移的百分比："+positionOffset
                    +"--当前页面偏移的像数位置："+positionOffsetPixels);
            int[] location = new int[2];
            int[] location2 = new int[2];
            dots[0].getLocationInWindow(location);
            dots[2].getLocationInWindow(location2);
//        TranslateAnimation ta = new TranslateAnimation(0,(location2[0]-location[0]),0,0);
//        pointing.startAnimation(ta);
            pointing.setTranslationX((location2[0]-location[0])*ratio*3/2);
        }

        @Override
        public void onPageSelected(int position) {                            //当前页面被选中时候调用

//            for (int i = 0; i<ids.length; i++){
//                if (i == position){
//                    dots[i].setImageResource(R.drawable.login_point_selected);
//                }else {
//                    dots[i].setImageResource(R.drawable.login_point);
//                }
//            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {                   //滑动状态改变时候调用

        }

    }

    @Override
    protected void onDestroy() {
        viewPager.removeOnPageChangeListener(new pageChangeListener());
        super.onDestroy();
    }
}
