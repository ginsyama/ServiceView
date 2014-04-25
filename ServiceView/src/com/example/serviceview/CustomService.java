package com.example.serviceview;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomService extends Service {
    private LocalBinder mBinder = new LocalBinder();
    private View mView;
    
    
    public class LocalBinder extends Binder{
        CustomService getService(){
            return CustomService.this;
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        setUpView();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }
    
    private void setUpView(){
        LayoutInflater inflater = LayoutInflater.from(this);
        mView = inflater.inflate(R.layout.activity_main, null);
    }
    
    public View getSetUpView(){
        ViewGroup view = (ViewGroup) mView.getParent();
        if(view != null) view.removeView(mView);
        return mView;
    }

}
