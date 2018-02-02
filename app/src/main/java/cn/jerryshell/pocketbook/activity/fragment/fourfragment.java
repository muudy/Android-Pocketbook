package cn.jerryshell.pocketbook.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.jerryshell.pocketbook.R;
import cn.jerryshell.pocketbook.activity.MainActivity;
import cn.jerryshell.pocketbook.activity.SummaryGraphActivity;
import cn.jerryshell.pocketbook.modle.Item;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

import static android.content.ContentValues.TAG;

public class fourfragment extends Fragment {


    public static final String EXTRA_ITEM_LIST = "itemList";
    private List<Item> mItemList;
    private Map<String, Integer> mDataTreeMap;


    View view;
    public ListView tab_listview;

    @Nullable
    @Override
    //onCreateView
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_summary_graph, container, false);



        //tab_listview = (ListView) view.findViewById(R.id.tab_listview);

        MainActivity mainActivity = new MainActivity();
        mainActivity.initItemList1();


        return view;


    }//onCreateView,end

}//threefragment,end

