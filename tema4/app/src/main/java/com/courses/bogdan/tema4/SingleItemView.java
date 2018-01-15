package com.courses.bogdan.tema4;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Bogdan on 1/5/2018.
 */

public class SingleItemView extends Activity {

    private ImageView imageView;
    private TextView title, description;
    private int  position, image;
    private String os, osDescritption;
    private Intent i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.single_itemview);

        // Get position from intent passed from MainActivity.java
        i = getIntent();

        position= i.getExtras().getInt("id");
        image = i.getExtras().getInt("image");
        os = i.getExtras().getString("title");
        osDescritption =  i.getExtras().getString("description");


        // Locate the ImageView in single_item_view.xml
        imageView = (ImageView) findViewById(R.id.detailImageView);

        title = (TextView) findViewById(R.id.os_title);
        description =(TextView) findViewById(R.id.os_description);

        // Get image and position from ImageAdapter.java and set into ImageView
        imageView.setImageResource(image);
        title.setText(os);
        description.setText(osDescritption);


    }
}
