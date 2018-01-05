package com.courses.bogdan.tema4;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.sql.Types.NULL;

public class MainActivity extends Activity {

    GridView gridview;

    public static String[] osNameList = {
            "Android",
            "iOS",
            "Linux",
            "MacOS",
            "MS DOS",
            "Symbian",
            "Windows 10",
            "Windows XP",
    };


    public static String[] osNameList2 = {
            "OS1",
            "OS2",
            "OS3",
            "OS4",
            "OS5",
            "OS6",
            "OS7",
            "OS8",
    };
    public static int[] osImages = {
            R.mipmap.android,
            R.mipmap.ios,
            R.mipmap.linux,
            R.mipmap.macos,
            R.mipmap.msdos,
            R.mipmap.symbian,
            R.mipmap.windows10,
            R.mipmap.winxp,};

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridview = (GridView) findViewById(R.id.customgrid);
        gridview.setAdapter(new CustomAdapter(this, osNameList,osNameList2, osImages));

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(osNameList.length>0) {
                    delete();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Launch ViewImage.java using intent
                Intent i = new Intent(MainActivity.this, SingleItemView.class);

                // Show the item position using toast
                Toast.makeText(MainActivity.this, "Position " + position,
                        Toast.LENGTH_SHORT).show();

                // Send captured position to ViewImage.java
                i.putExtra("id", position);
                i.putExtra("image",osImages[position]);
                i.putExtra("title",osNameList[position]);
                i.putExtra("description",osNameList2[position]);

                // Start ViewImage.java
                startActivity(i);
            }
        });

    }

    public void delete(){

        List<String> list =  new ArrayList<String>();
        Collections.addAll(list, osNameList);
        list.remove(osNameList.length-1);
        osNameList = list.toArray(new String[list.size()]);


        List<String> list2 =  new ArrayList<String>();
        Collections.addAll(list2, osNameList2);
        list2.remove(osNameList2.length-1);
        osNameList2 = list2.toArray(new String[list2.size()]);

        for(int i=osImages.length-1; i>0;i--){
            osImages[i] = osImages[i-1];

        }
        osImages[osImages.length-1]= NULL;

        gridview.setAdapter(new CustomAdapter(this, osNameList,osNameList2, osImages));

    }
}
