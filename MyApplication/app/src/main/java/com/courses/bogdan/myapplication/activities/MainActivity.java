package com.courses.bogdan.myapplication.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.courses.bogdan.myapplication.R;
import com.courses.bogdan.myapplication.constants.IntentActionConstants;
import com.courses.bogdan.myapplication.fragments.MainFragment;
import com.courses.bogdan.myapplication.presenters.MainPresenter;
import com.courses.bogdan.myapplication.presenters.MainPresenterImpl;
import com.courses.bogdan.myapplication.services.MyService;
import com.courses.bogdan.myapplication.views.MainView;

/**
 * Created by Bogdan on 11/6/2017.
 */

public class MainActivity extends FragmentActivity implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button toastButton;
    private Button getButton;
    private Button colorButton;
    private Button fragmentButton;
    private TextView myView;
    private EditText textEditor;
    private RelativeLayout mainlayout;

    private MyReceiver myReceiver;

    private ColorButtonListener colorButtonListener;
    private FragmentButtonListener fragmentButtonListener;
    private GetButtonListener getButtonListener;
    private ToastButtonListener toastButtonListener;

    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        presenter = new MainPresenterImpl(this);
        presenter.onViewCreated();

        myReceiver = new MyReceiver();

        setupListeners();
        setupViews();
        setupFragment();

        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewStarted();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcastReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentActionConstants.ACTIVITY_INFO_INTENT);
        registerReceiver(myReceiver, intentFilter);
    }

    private void setupViews() {
        mainlayout = (RelativeLayout) findViewById(R.id.layout);
        toastButton = (Button) findViewById(R.id.toastButton);
        getButton = (Button) findViewById(R.id.getTextButton);
        colorButton = (Button) findViewById(R.id.colorButton);
        fragmentButton = (Button) findViewById(R.id.fragmentGo);
        myView = (TextView) findViewById(R.id.myText);
        textEditor = (EditText) findViewById(R.id.textEditor);

        toastButton.setOnClickListener(toastButtonListener);
        getButton.setOnClickListener(getButtonListener);
        colorButton.setOnClickListener(colorButtonListener);
        fragmentButton.setOnClickListener(fragmentButtonListener);
    }

    private void setupFragment() {
        MainFragment fragment = MainFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_frame_layout, fragment).commit();
    }

    private void setupListeners() {
        colorButtonListener = new ColorButtonListener();
        fragmentButtonListener = new FragmentButtonListener();
        getButtonListener = new GetButtonListener();
        toastButtonListener = new ToastButtonListener();
    }

    private void setTextToTextView(final String text) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                myView.setText(text);
            }
        });
    }

    @Override
    public void setMainLayoutBackgroundColor(int color) {
        mainlayout.setBackgroundColor(color);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "dvniv");
            setTextToTextView("Fragment button pressed");
            //String getString = String.valueOf(intent.getExtras().get("fragmentActivity"));
        }
    }

    private class ColorButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            presenter.onColorButtonClicked();
        }
    }

    private class FragmentButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(IntentActionConstants.FRAGMENT_INFO_INTENT);
            sendBroadcast(intent);
        }
    }

    private class GetButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            myView.setText(textEditor.getText());
        }
    }

    private class ToastButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String text = myView.getText().toString();
            Log.e(TAG, text);
            Toast.makeText(MainActivity.this, "i pressed the buttoon", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}