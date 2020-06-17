package com.li.baseadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.li.adperlibrary.base.AdapterOnItemClick;
import com.li.adperlibrary.base.AdapterOnLongItemClick;
import com.li.adperlibrary.head.HeaderFooterAdapter;
import com.li.adperlibrary.head.HeaderRecycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Li
 * 版本：1.0
 * 创建日期：2020/6/8
 * 描述：普通的adpter
 */
public class MainActivity extends AppCompatActivity {
    HeaderRecycleView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_demo);
        RelativeLayout rl_demo = findViewById(R.id.rl_demo);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("第" + i + "个");
        }
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this,new ArrayList<String>(),R.layout.item_simple);
        simpleAdapter.SetOnClickListener(new AdapterOnItemClick<String>() {
            @Override
            public void click(View view, String s, int type, int pos) {
                Log.e("TAG", "click:-------> "+s+"...." +type+"......"+pos);
                simpleAdapter.remove(s);
            }
        });
        simpleAdapter.SetOnLongClickListener(new AdapterOnLongItemClick<String>() {
            @Override
            public void click(View view, String s, int type, int pos) {
                    simpleAdapter.remove(pos);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(simpleAdapter);
        simpleAdapter.upData(strings);

//        MixLayoutAdapter mixLayoutAdapter = new MixLayoutAdapter(this, strings);
//        mixLayoutAdapter.SetOnClickListener(new AdapterOnItemClick<String>() {
//            @Override
//            public void click(View view, String s, int type, int pos) {
//                Log.e("TAG", "click:-------> " + s + "...." + type + "......" + pos);
//            }
//        });
//        mixLayoutAdapter.SetOnLongClickListener(new AdapterOnLongItemClick<String>() {
//            @Override
//            public void click(View view, String s, int type, int pos) {
//                Log.e("长点击TAG", "click:-------> " + s + "...." + type + "......" + pos);
//            }
//        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(mixLayoutAdapter);
//        View view = LayoutInflater.from(this).inflate(R.layout.layout_header,recyclerView,false);
//        recyclerView.addHeaderView(view);
//        recyclerView.addFooterView(view);
    }


}
