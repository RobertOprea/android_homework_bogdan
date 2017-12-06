package com.courses.bogdan.myapplication.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.courses.bogdan.myapplication.R;
import com.courses.bogdan.myapplication.activities.MainActivity;

/**
 * Created by Bogdan on 11/9/2017.
 */

public class MainFragment extends android.support.v4.app.Fragment {
    private Button fragmentButon;
    private TextView fragmentView;

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_layout,container,false);
        fragmentButon = (Button) view.findViewById(R.id.myFragmentButton);
        fragmentView = (TextView)view.findViewById(R.id.fragmentView);

        View.OnClickListener listnr=new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.MyReceiver.class);
                intent.setAction("com.example.demo.action.info");
//
                getActivity().sendBroadcast(intent);


            }

        };


        return view;
    }

    public class MyReceiverFragment extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                fragmentView.setText("Activity recieved");
                //String getString = String.valueOf(intent.getExtras().get("fragmentActivity"));
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //
    }
}
