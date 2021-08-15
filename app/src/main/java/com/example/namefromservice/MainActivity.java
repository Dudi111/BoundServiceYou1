package com.example.namefromservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvname;
    private Button mbtnstart;
    private Button mbtnfetch;
    private Button mbtnstop;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter=new IntentFilter("xyz.com");
        registerReceiver(receiver,intentFilter);
        initviews();
    }

    private void initviews() {
        tvname=findViewById(R.id.lodki);
        mbtnstart=findViewById(R.id.btnstart);
        mbtnfetch=findViewById(R.id.btnfetch);
        mbtnstop=findViewById(R.id.btnstop);
        Intent intent=new Intent(this,MyService.class);
        mbtnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });
        mbtnfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.getname();

            }
        });
        mbtnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             unbindService(serviceConnection);
            }
        });
    }
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MyService.ServiceBinder serviceBinder= (MyService.ServiceBinder) binder;
            myService=serviceBinder.getMyservice();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvname.setText(intent.getStringExtra("name"));
        }
    };
}