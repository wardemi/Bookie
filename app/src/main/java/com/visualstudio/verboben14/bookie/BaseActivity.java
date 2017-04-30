package com.visualstudio.verboben14.bookie;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by zsoltdemjan on 2017. 04. 30..
 */

public class BaseActivity extends AppCompatActivity {

    protected FirebaseUser mUser;
    protected Intent mRedirectIntent;

    public BaseActivity() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    protected boolean isLogged() {
        if(mUser == null)  return false;
        else return true;
    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
