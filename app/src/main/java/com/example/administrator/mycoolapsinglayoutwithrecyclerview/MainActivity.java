package com.example.administrator.mycoolapsinglayoutwithrecyclerview;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> mdatas = new ArrayList<>();
    MyAdapter myAdapter;
    AppBarLayout appBarLayout;
    boolean flag;
    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    TextView hint;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rc);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        hint = (TextView) findViewById(R.id.tv_hint);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        setSupportActionBar(toolbar);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, toolbar);
        inidatas();
        myAdapter = new MyAdapter(mdatas, this, new MyAdapter.MyListener() {
            @Override
            public void onClick(View view) {
                flag = !flag;
                if (flag) {
                    appBarLayout.setExpanded(true, true);
                } else {
                    appBarLayout.setExpanded(false, true);
                }
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //展开
                if (Math.abs(verticalOffset) >= 0 && Math.abs(verticalOffset) < appBarLayout.getTotalScrollRange()) {
                    toolbar.setTitle("this is the title");
                    toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
                    hint.setVisibility(View.GONE);
                    if (menu != null)
                        showMenu();;
                    ;
                } else {
                    toolbar.setTitle("");
                    toolbar.setNavigationIcon(null);
                    hideMenu();
                    hint.setVisibility(View.VISIBLE);
                }
                Log.e("xxx", "verticaloffset: " + verticalOffset);
                Log.e("xxx", "appBarLayout.getTotalScrollRange(): " + appBarLayout.getTotalScrollRange() + "\n");
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    private void showMenu() {
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setVisible(true);
        }
    }

    private void hideMenu() {
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setVisible(false);
        }
    }

    private void inidatas() {
        for (int i = 0; i < 20; i++) {
            mdatas.add("this is the " + i + " position item");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.muitymenu, menu);
        this.menu = menu;
        return true;
    }
}
