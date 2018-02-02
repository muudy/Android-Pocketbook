package cn.jerryshell.pocketbook.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import cn.jerryshell.pocketbook.R;
import cn.jerryshell.pocketbook.activity.adapter.ItemRecyclerAdapter;
import cn.jerryshell.pocketbook.db.DatabaseHelper;
import cn.jerryshell.pocketbook.modle.Item;
import cn.jerryshell.pocketbook.activity.MainActivity;

public class twofragment extends Fragment {
    private EditText titleEditText;
    private EditText moneyEditText;
    private DatePicker datePicker;
    private Button button_add;
    View view;

    private DatabaseHelper mDatabaseHelper;
    Context context;

    @Nullable
    @Override
    //onCreateView
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_item, container, false);

        //view = inflater.inflate(R.layout.fragment_left, container, false);
        //newsTitleList = (ListView) view.findViewById(R.id.id_news_title);
        titleEditText = (EditText) view.findViewById(R.id.et_title);
        moneyEditText = (EditText) view.findViewById(R.id.et_money);
        datePicker = (DatePicker) view.findViewById(R.id.dp_date);
        button_add = (Button) view.findViewById(R.id.btnbtn_add);

        button_add.setOnClickListener(new View.OnClickListener() {//创建监听
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String money = moneyEditText.getText().toString();
                int year = datePicker.getYear();
                int month = datePicker.getMonth()+1;
                int day = datePicker.getDayOfMonth();
                String date = year + "-" + month + "-" + day;
                //String date = year + "+" + month + "+" + day;
                Item item = new Item(title, money, date);
                Log.i("标志","item"+item);
                //Log.i("twofragment","item"+item.toString());
                MainActivity mainActivity = new MainActivity();
                mainActivity.addItem(item,getActivity());

                //addItem(item);
            }

        });

        return view;
    }







}

