package com.courses.bogdan.myapplication.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.courses.bogdan.myapplication.R;
import com.courses.bogdan.myapplication.activities.MainActivity;
import com.courses.bogdan.myapplication.constants.IntentActionConstants;

/**
 * Created by Bogdan on 11/9/2017.
 */

public class MainFragment extends android.support.v4.app.Fragment {
    private Button fragmentButon;
    private TextView fragmentView;

    private MyReceiverFragment receiverFragment;

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiverFragment = new MyReceiverFragment();
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
                Log.e("TAG", "smivdnudn");
                Intent intent = new Intent();
                intent.setAction(IntentActionConstants.ACTIVITY_INFO_INTENT);
                getActivity().sendBroadcast(intent);


            }

        };

        fragmentButon.setOnClickListener(listnr);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentActionConstants.FRAGMENT_INFO_INTENT);
        getContext().registerReceiver(receiverFragment, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(receiverFragment);
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
