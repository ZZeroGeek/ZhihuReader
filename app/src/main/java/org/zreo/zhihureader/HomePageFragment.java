package org.zreo.zhihureader;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kakin on 2016/4/19.
 */
public class HomePageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int REFRESH_COMPLETE = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> datas = new ArrayList<String>();

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){

                case REFRESH_COMPLETE:
                    datas.add("华为哪些做法让你无法接受？");
                    datas.add("中国菜到底有多好吃？");
                    datas.add("在日本吃上等和牛是怎样一种体验？");
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page,container,false);
        datas.add("这是首页");
        datas.add("内容是…………");
        datas.add("你吃过那些直击灵魂的食物？");

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        listView = (ListView) view.findViewById(R.id.find_list);

        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onRefresh() {

        handler.sendEmptyMessageDelayed(REFRESH_COMPLETE,2000);
    }
}
