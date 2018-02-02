package cn.jerryshell.pocketbook.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.jerryshell.pocketbook.R;
import cn.jerryshell.pocketbook.activity.adapter.ItemRecyclerAdapter;
import cn.jerryshell.pocketbook.activity.adapter.ListViewAdapter;
import cn.jerryshell.pocketbook.activity.fragment.fourfragment;
import cn.jerryshell.pocketbook.activity.fragment.onefragment;
import cn.jerryshell.pocketbook.activity.fragment.threefragment;
import cn.jerryshell.pocketbook.activity.fragment.twofragment;
import cn.jerryshell.pocketbook.db.DatabaseHelper;
import cn.jerryshell.pocketbook.modle.Item;
import cn.jerryshell.pocketbook.modle.Item1;


//FragmentActivity,AppCompatActivity
public class MainActivity extends AppCompatActivity {

    public static List<Item> mItemList = new ArrayList<>();
    public static List<Item1> mItemList1 = new ArrayList<>();

    public static ItemRecyclerAdapter mItemRecyclerAdapter;
    public static ListViewAdapter mListViewAdapter;
    public static DatabaseHelper mDatabaseHelper;
    public static ImageButton mChartsImg;
    public static ImageButton mContactImg;
    public static ImageButton mDiscoverImg;
    public static TextView mChartsText;
    public static TextView mContactText;
    public static TextView mDiscoverText;
    public static FragmentPagerAdapter fragmentPagerAdapter;
    public static List<Fragment> fragmentList;
    public static ViewPager viewPager;

    //这里的名字是自己取得
    public static LinearLayout id_tab_one;
    public static LinearLayout id_tab_two;
    public static LinearLayout id_tab_three;

    private Context Context;
    public Activity activity = null;
    public static MainActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //初始化数据库
        mDatabaseHelper = new DatabaseHelper(this);

        init();
        instance = this;
        //initDatabase();
        initItemList();
        initItemList1();//初始化
        //initItemRecyclerView();

        initevent();
        Log.i("标志", "initevent();");
    }


    public void setActivity(Activity act) {
        this.activity = act;
    }


    private void init() {

        mChartsImg = (ImageButton) findViewById(R.id.id_tab_chats_img);
        mContactImg = (ImageButton) findViewById(R.id.id_tab_contact_img);
        mDiscoverImg = (ImageButton) findViewById(R.id.id_tab_discover_img);

        mChartsText = (TextView) findViewById(R.id.one);
        mContactText = (TextView) findViewById(R.id.two);
        mDiscoverText = (TextView) findViewById(R.id.three);

        viewPager = (ViewPager) findViewById(R.id.id_view_content);

        id_tab_one = (LinearLayout) findViewById(R.id.id_tab_chats);
        id_tab_two = (LinearLayout) findViewById(R.id.id_tab_contact);
        id_tab_three = (LinearLayout) findViewById(R.id.id_tab_discover);

        fragmentList = new ArrayList<Fragment>();

        android.support.v4.app.Fragment mone = new onefragment();
        android.support.v4.app.Fragment mtwo = new twofragment();
        android.support.v4.app.Fragment mthree = new threefragment();
        android.support.v4.app.Fragment mfour = new fourfragment();

        //装载进来
        fragmentList.add(mone);
        fragmentList.add(mtwo);
        fragmentList.add(mthree);
        //fragmentList.add(mfour);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);//装载适配器
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //resetTabs();

                //setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initevent() {

        id_tab_one.setOnClickListener(new View.OnClickListener() {//创建监听
            public void onClick(View v) {
                resetTabs();
                setSelected(0);
                viewPager.setCurrentItem(0);
            }

        });

        id_tab_two.setOnClickListener(new View.OnClickListener() {//创建监听
            public void onClick(View v) {
                resetTabs();
                setSelected(1);
                viewPager.setCurrentItem(1);
            }

        });

        id_tab_three.setOnClickListener(new View.OnClickListener() {//创建监听
            public void onClick(View v) {
                resetTabs();
                setSelected(2);

                viewPager.setCurrentItem(2);

//                Intent startIntent = SummaryGraphActivity.getStartIntent(MainActivity.this, mItemList);
//                startActivity(startIntent);

            }

        });
    }


    public void initItemRecyclerView(SwipeMenuRecyclerView itemSwipeMenuRecyclerView) {
        //SwipeMenuRecyclerView itemSwipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.lv_item);
        //SwipeMenuRecyclerView itemSwipeMenuRecyclerView
        //=getFragmentManager().findFragmentById(R.layout.content_item_list).getView().findViewById(R.id.lv_item);

        //View rootView = inflater.inflate(R.layout.content_item_list, container, false);
        //SwipeMenuRecyclerView itemSwipeMenuRecyclerView = (SwipeMenuRecyclerView)rootView.findViewById(R.id.lv_item);


        initItemRecyclerAdapter(itemSwipeMenuRecyclerView);

        itemSwipeMenuRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        itemSwipeMenuRecyclerView.setItemViewSwipeEnabled(true);


        //移动事件
        itemSwipeMenuRecyclerView.setOnItemMoveListener(new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                return false;
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                int position = srcHolder.getAdapterPosition();
                deleteItem(position);
            }
        });
    }

    public void initListView(ListView listView) {

        //适配器填数据
        Log.i("标志-initListView", "mItemList1:" + mItemList1);//到这了

        mListViewAdapter = new ListViewAdapter(MainActivity.this, mItemList1);
        //mListViewAdapter = new ListViewAdapter(mItemList1);
        listView.setAdapter(mListViewAdapter);
    }


    public void initItemRecyclerAdapter(SwipeMenuRecyclerView itemSwipeMenuRecyclerView) {
        //适配器填数据
        Log.i("标志", "initItemRecyclerAdapter-mItemList:" + mItemList);
        mItemRecyclerAdapter = new ItemRecyclerAdapter(mItemList);

        //点击事件
        mItemRecyclerAdapter.setOnItemClickListener(new ItemRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item item = mItemList.get(position);
                //showDelItemDialog(position);
                //showChangeItemDialog(item);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //showDelItemDialog(position);
            }

        });

        itemSwipeMenuRecyclerView.setAdapter(mItemRecyclerAdapter);
    }


    private void showDelItemDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("你要删除这个条目吗？");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem(position);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }


    /*
    private void showChangeItemDialog(final Item item) {
        View dialogView = View.inflate(MainActivity.this, R.layout.add_item, null);
        final EditText titleEditText = (EditText) dialogView.findViewById(R.id.et_title);
        titleEditText.setText(item.getTitle());
        final EditText moneyEditText = (EditText) dialogView.findViewById(R.id.et_money);
        moneyEditText.setText(item.getMoney());
        final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.dp_date);
        String[] split = item.getDate().split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]) - 1;
        int day = Integer.parseInt(split[2]);
        datePicker.init(year, month, day, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("修改条目");
        builder.setView(dialogView);
        builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = titleEditText.getText().toString();
                String money = moneyEditText.getText().toString();
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                String date = year + "-" + month + "-" + day;
                item.setTitle(title);
                item.setMoney(money);
                item.setDate(date);
                updateItem(item);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }
*/


    private void deleteItem(int position) {
        mDatabaseHelper.deleteItem(mItemList.get(position));
        mItemList.remove(position);
        mItemRecyclerAdapter.notifyItemRemoved(position);
    }


    //
    public void addItem(Item item, Context context) {
        if (TextUtils.isEmpty(item.getMoney())) {
            item.setMoney("0");
        }
        mDatabaseHelper.insertItem(item);
        mItemList.add(0, item);
        Log.i("标志", "addItem-mItemList:" + mItemList);
        mItemRecyclerAdapter.notifyItemInserted(0);

        //刷新mitemlist
        initItemList1();
        mListViewAdapter.notifyDataSetChanged();

        Toast.makeText(context, "成功添加", Toast.LENGTH_SHORT).show();
    }


    private void updateItem(Item item) {
        mDatabaseHelper.updateItem(item);
        mItemRecyclerAdapter.notifyDataSetChanged();//ctrl  j;

    }


    public void initItemList() {
        mItemList.clear();
        Cursor cursor = mDatabaseHelper.queryAllItem();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID));
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE));
                String money = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MONEY));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATE));
                mItemList.add(new Item(UUID.fromString(id), title, money, date));
            }
            cursor.close();
        }
    }


    public void initItemList1() {
        mItemList1.clear();
        Cursor cursor = mDatabaseHelper.countByMonth();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String money = cursor.getString(cursor.getColumnIndex("summoney"));

                String date = cursor.getString(cursor.getColumnIndex("date"));
                //String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATE));
                mItemList1.add(new Item1(money, date));
                //date.substring(1,7);
                Log.i("标志", "初始化mItemList1:" + mItemList1);
            }
        }
    }

    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);//菜单布局
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                showDeleteAllItemDialog();
                return true;

            case R.id.settings:
                Toast.makeText(MainActivity.this, "未开发", Toast.LENGTH_SHORT).show();
                //Intent startIntent = SummaryGraphActivity.getStartIntent(MainActivity.this, mItemList);
                //startActivity(startIntent);
                break;

            case R.id.action_about_me:
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDeleteAllItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你要删除所有条目吗？");
        //setPositiveButton
        builder.setPositiveButton("删除所有条目", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int line = mDatabaseHelper.deleteAllItem();
                mItemList.clear();
                mItemList1.clear();
                mItemRecyclerAdapter.notifyDataSetChanged();
                mListViewAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "删除了 " + line + " 条数据", Toast.LENGTH_SHORT).show();
            }
        });

        //setNegativeButton
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }


    /**
     * 将Tab变灰
     */
    public void resetTabs() {
        mChartsImg.setImageResource(R.drawable.charts_off);
        mChartsText.setTextColor(getResources().getColor(R.color.off));

        mContactImg.setImageResource(R.drawable.contacts_off);
        mContactText.setTextColor(getResources().getColor(R.color.off));

        mDiscoverImg.setImageResource(R.drawable.discover_off);
        mDiscoverText.setTextColor(getResources().getColor(R.color.off));
    }


    //将点击的Tab变亮
    private void setSelected(int i) {

        switch (i) {
            case 0:
                mChartsImg.setImageResource(R.drawable.charts_on);
                mChartsText.setTextColor(getResources().getColor(R.color.on));
                break;

            case 1:
                mContactImg.setImageResource(R.drawable.contacts_on);
                mContactText.setTextColor(getResources().getColor(R.color.on));
                break;

            case 2:
                mDiscoverImg.setImageResource(R.drawable.discover_on);
                mDiscoverText.setTextColor(getResources().getColor(R.color.on));
                break;
        }
    }


}
