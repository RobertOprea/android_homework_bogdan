package com.courses.bogdan.tema3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.BufferUnderflowException;
import java.security.AccessController;

public class MainActivity extends AppCompatActivity {

    private Intent intent, intent2;
    private Button secondActivity, thirdActivity;
    private ObjectToSend obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondActivity = (Button) findViewById(R.id.activityChanger);
        thirdActivity = (Button) findViewById(R.id.thirdActivity);


        intent = new Intent(getApplicationContext(),SecondActivity.class);
        obj = new ObjectToSend("George","Georgel",30,"turzii");
        secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("MyClass", obj);
                startActivityForResult(intent,90);
            }
        });
        intent2 = new Intent(getApplicationContext(),ThirdActivity.class);
        thirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(intent2);
                    }

                },5000);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        switch(requestCode){
            case 90:
                if (resultCode ==RESULT_OK){
                    Toast toast = Toast.makeText(this,"toast",Toast.LENGTH_SHORT);
                    Bundle res = data.getExtras();
                    String result = res.getString("results");
                    toast.setText(result);
                    toast.show();
                }
                break;
        }

    }



}
