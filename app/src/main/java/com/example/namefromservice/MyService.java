package com.example.namefromservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {



    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Myservice", "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new ServiceBinder();
    }
    public void getname(){
        Intent intent1=new Intent("xyz.com");
        intent1.putExtra("name","Praveen");
        sendBroadcast(intent1);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Myservice", "onDestroy");
    }

    public class ServiceBinder extends Binder {

        public MyService getMyservice(){
            return MyService.this;
        }
    }
}