package com.example.serviceview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;

import com.example.serviceview.CustomService.LocalBinder;

public class MainActivity extends Activity {
    private CustomService mService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CustomService.LocalBinder binder = (LocalBinder) service;
            mService  = binder.getService();
            setContentView(mService.getSetUpView());
        }
    };
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent( this, CustomService.class);
        startService(intent);
        bindService(intent, mServiceConnection, 0);
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        if(mService != null)
            unbindService(mServiceConnection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
