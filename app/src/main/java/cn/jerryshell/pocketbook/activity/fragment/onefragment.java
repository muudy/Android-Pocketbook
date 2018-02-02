package cn.jerryshell.pocketbook.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import cn.jerryshell.pocketbook.R;
import cn.jerryshell.pocketbook.activity.MainActivity;

/**
 * Created by admin on 2017/12/19.
 */

public class onefragment extends Fragment {
    View view;

    @Nullable
    @Override
    //onCreateView
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //???????
         view=inflater.inflate(R.layout.content_item_list,container,false);

        SwipeMenuRecyclerView itemSwipeMenuRecyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.lv_item);
        MainActivity.instance.initItemRecyclerView(itemSwipeMenuRecyclerView);

        //Log.i("标志","onefragment:"+itemSwipeMenuRecyclerView);
//        MainActivity mainActivity = new MainActivity();
//        mainActivity.initItemRecyclerView(itemSwipeMenuRecyclerView);
        return view;
    }
}
