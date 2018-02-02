package cn.jerryshell.pocketbook.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.jerryshell.pocketbook.R;
import cn.jerryshell.pocketbook.activity.MainActivity;
/**
 * Created by miaoxinyu on 2018/1/10.
 * 在fragment中调用主activity方法
 * 须在MainActivity中定义
 * public static MainActivity instance;
 * 且在oncreat中初始化instance = this;
 * 然后才可以引用
 */
public class threefragment extends Fragment {
    View view;
    public ListView tab_listview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_summary_graph, container, false);
        tab_listview=(ListView)view.findViewById(R.id.tab_listview);
        MainActivity .instance.initListView(tab_listview);
        return view;

    }//onCreateView,end

}//threefragment,end

