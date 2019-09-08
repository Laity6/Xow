package com.ol.xow;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ol.xow.base.view.CircularProgressBar;
import com.ol.xow.base.view.RateTextCircularProgressBar;

public class MainActivity extends AppCompatActivity {


    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout drawerLayout;
    private RelativeLayout main_left_drawer_layout, main_right_drawer_layout;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);
        setTitle("Xow标题");

        RateTextCircularProgressBar circularProgressBar=findViewById(R.id.cb_progress);
        circularProgressBar.setMax(100);
        circularProgressBar.setProgress(50);
        initLayout();
    }

    public void initLayout() {

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);

        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //左边菜单
        main_left_drawer_layout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
        //右边菜单
        main_right_drawer_layout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);

    }

    //左边菜单开关事件
    public void openLeftLayout(View view) {
        if (drawerLayout.isDrawerOpen(main_left_drawer_layout)) {
            drawerLayout.closeDrawer(main_left_drawer_layout);
        } else {
            drawerLayout.openDrawer(main_left_drawer_layout);
        }
    }

    // 右边菜单开关事件
    public void openRightLayout(View view) {
        if (drawerLayout.isDrawerOpen(main_right_drawer_layout)) {
            drawerLayout.closeDrawer(main_right_drawer_layout);
        } else {
            drawerLayout.openDrawer(main_right_drawer_layout);
        }
    }


}
