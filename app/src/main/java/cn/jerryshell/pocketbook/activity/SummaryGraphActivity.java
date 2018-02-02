package cn.jerryshell.pocketbook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.jerryshell.pocketbook.R;
import cn.jerryshell.pocketbook.modle.Item;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class SummaryGraphActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM_LIST = "itemList";
    private List<Item> mItemList;
    private Map<String, Integer> mDataTreeMap;

    public static Intent getStartIntent(Context context, List<Item> itemList) {
        Intent intent = new Intent(context, SummaryGraphActivity.class);
        intent.putExtra(EXTRA_ITEM_LIST, (Serializable) itemList);
        return intent;
    }

    private void initItemListFromIntent() {
        Intent intent = getIntent();
        mItemList = (List<Item>) intent.getSerializableExtra(EXTRA_ITEM_LIST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_graph);
        setTitle("统计图");

        initItemListFromIntent();

        //LineChartView lineChartView = (LineChartView) findViewById(R.id.chart);

        initDataTreeMap();

        //setLineChartViewData(lineChartView, mDataTreeMap);
    }

    private void initDataTreeMap() {
        mDataTreeMap = new TreeMap<>();
        for (Item item : mItemList) {
            String date = item.getDate();
            if (mDataTreeMap.containsKey(date)) {
                int originValue = mDataTreeMap.get(date);
                int newValue = originValue + Integer.parseInt(item.getMoney());
                mDataTreeMap.put(date, newValue);
            } else {
                mDataTreeMap.put(date, Integer.parseInt(item.getMoney()));
            }
        }
    }

    private void setLineChartViewData(LineChartView lineChartView, Map<String, Integer> dataTreeMap) {
        List<PointValue> pointValues = new ArrayList<>();
        List<AxisValue> axisBottomValueList = new ArrayList<>();
        List<AxisValue> axisLeftValueList = new ArrayList<>();
        int index = 0;
        Set<Map.Entry<String, Integer>> entries = dataTreeMap.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            pointValues.add(new PointValue(index, value));
            axisBottomValueList.add(new AxisValue(index).setLabel(key));
            axisLeftValueList.add(new AxisValue(value).setLabel(value.toString()));
            index++;
        }
        Line line = new Line(pointValues).setColor(ChartUtils.COLOR_BLUE);
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        LineChartData lineChartData = new LineChartData(lines);
        lineChartData.setAxisXBottom(new Axis(axisBottomValueList));
        lineChartData.setAxisYLeft(new Axis(axisLeftValueList));
        lineChartView.setLineChartData(lineChartData);
    }
}
