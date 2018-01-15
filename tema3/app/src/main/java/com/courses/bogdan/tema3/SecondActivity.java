package com.courses.bogdan.tema3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Bogdan on 12/6/2017.
 */

public class SecondActivity extends AppCompatActivity{

    TextView textView;
    ObjectToSend objRecieved;
    Intent intent;
    Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        textView = (TextView) findViewById(R.id.textSecondActivity);
        objRecieved = (ObjectToSend) getIntent().getSerializableExtra("MyClass");
        button = (Button) findViewById(R.id.goBack);

        textView.setText(objRecieved.getFirstName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWithResult();
            }
        });

    }

    private void finishWithResult()
    {
        Bundle conData = new Bundle();
        conData.putString("results", "Going back");
        intent = new Intent();
        intent.putExtras(conData);
        setResult(RESULT_OK, intent);
        finish();
    }
}
