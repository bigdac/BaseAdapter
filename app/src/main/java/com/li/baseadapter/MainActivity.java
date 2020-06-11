package com.li.baseadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.li.adperlibrary.AdapterOnItemClick;
import com.li.adperlibrary.OnItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Li
 * 版本：1.0
 * 创建日期：2020/6/8
 * 描述：普通的adpter
 *
 */
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_demo);
        List<String> strings = new ArrayList<>();
        for (int i=0;i<20;i++){
            strings.add("第"+i+"个");
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,strings,R.layout.item_simple);
        simpleAdapter.SetOnClickListener(new AdapterOnItemClick<String>() {
            @Override
            public void click(View view, String s, int type, int pos) {
                Log.e("TAG", "click:-------> "+s+"...." +type+"......"+pos);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(simpleAdapter);
    }



}
