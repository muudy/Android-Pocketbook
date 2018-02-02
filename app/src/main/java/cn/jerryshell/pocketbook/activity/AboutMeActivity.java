package cn.jerryshell.pocketbook.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.jerryshell.pocketbook.R;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        setTitle("关于作者");
    }
}
