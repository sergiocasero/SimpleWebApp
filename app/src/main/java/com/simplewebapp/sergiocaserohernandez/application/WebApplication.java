package com.simplewebapp.sergiocaserohernandez.application;

import android.app.Application;

import com.karumi.dexter.Dexter;

/**
 * Created by sergiocaserohernandez on 23/1/16.
 */
public class WebApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Dexter.initialize(this);
    }
}
