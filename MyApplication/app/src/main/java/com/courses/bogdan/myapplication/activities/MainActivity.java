package com.courses.bogdan.myapplication.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SupportActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.courses.bogdan.myapplication.R;

import java.util.Random;

/**
 * Created by Bogdan on 11/6/2017.
 */

public class MainActivity extends SupportActivity {

    private Button button, getButton, colorButton;
    private TextView textView;
    private EditText editText;
    private RelativeLayout rootlayout;
    private  int red ,green,blue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);


        rootlayout = (RelativeLayout) findViewById(R.id.layout);

        button = (Button)findViewById(R.id.button);
        getButton = (Button)findViewById(R.id.getTextButton);
        colorButton = (Button)findViewById(R.id.colorButton);

        textView = (TextView)findViewById(R.id.myText) ;
        editText = (EditText)findViewById(R.id.textEditor) ;

        getButton.setEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = String.valueOf(textView.getText());
                Log.e("TAAAG", s);
                Toast.makeText(MainActivity.this,"i pressed the buttoon", Toast.LENGTH_SHORT).show();
            }
        });

        getButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText());
            }
        });

        colorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setRandomColor();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setRandomColor();
        getButton.setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRandomColor();
        getButton.setEnabled(true);

    }

    public void setRandomColor(){
        Random randomColor = new Random();
        red= randomColor.nextInt(256);
        green = randomColor.nextInt(256);
        blue = randomColor.nextInt(256);
        rootlayout.setBackgroundColor(Color.argb(255,red,green,blue));
    }
}
