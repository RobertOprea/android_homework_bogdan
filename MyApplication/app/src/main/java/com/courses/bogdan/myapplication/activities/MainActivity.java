package com.courses.bogdan.myapplication.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.courses.bogdan.myapplication.R;
import com.courses.bogdan.myapplication.fragments.MainFragment;
import com.courses.bogdan.myapplication.services.MyService;

import java.util.Random;

/**
 * Created by Bogdan on 11/6/2017.
 */

public class MainActivity extends FragmentActivity {

    private Button toastButton, getButton, colorButton, fragmentButon;
    private TextView myView;
    private EditText textEditor;
    private RelativeLayout mainlayout;
    private Intent intent;
    private FrameLayout frameLayout;
    private static final int MAX_COLOR_VALUE = 256;
    private Random randomColor = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);



        mainlayout = (RelativeLayout) findViewById(R.id.layout);
        frameLayout = (FrameLayout) findViewById(R.id.main_frame_layout);

        toastButton = (Button) findViewById(R.id.toastButton);
        getButton = (Button) findViewById(R.id.getTextButton);
        colorButton = (Button) findViewById(R.id.colorButton);
        fragmentButon = (Button) findViewById(R.id.myFragmentButton);

        myView = (TextView) findViewById(R.id.myText);
        textEditor = (EditText) findViewById(R.id.textEditor);


        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = String.valueOf(myView.getText());
                Log.e("TAAAG", s);
                Toast.makeText(MainActivity.this, "i pressed the buttoon", Toast.LENGTH_SHORT).show();
            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.setText(textEditor.getText());
            }
        });

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainlayout.setBackgroundColor(Color.argb(MAX_COLOR_VALUE - 1, randomColor.nextInt(MAX_COLOR_VALUE),randomColor.nextInt(MAX_COLOR_VALUE),randomColor.nextInt(MAX_COLOR_VALUE)));


            }
        });

        setupFragment();


        fragmentButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                intent.setAction("com.example.demo.action.info");
                sendBroadcast(intent);
            }
        });


    }


    private void setupFragment() {
        MainFragment fragment = MainFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_frame_layout, fragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainlayout.setBackgroundColor(Color.argb(MAX_COLOR_VALUE-1, randomColor.nextInt(MAX_COLOR_VALUE),randomColor.nextInt(MAX_COLOR_VALUE),randomColor.nextInt(MAX_COLOR_VALUE)));
    }


    public void updateTheTextView(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                myView.setText(t);

            }
        });
    }


    public class MyReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                updateTheTextView("Fragment button pressed");
                String getString = String.valueOf(intent.getExtras().get("fragmentActivity"));
            } catch (Exception e) {

            }
        }
    }

}