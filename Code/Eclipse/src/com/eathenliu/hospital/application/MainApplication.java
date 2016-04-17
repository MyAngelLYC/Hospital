package com.eathenliu.hospital.application;

import android.app.Application;

public class MainApplication extends Application{
	private static MainApplication sInstance = null;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		sInstance = this;
	}

	public static MainApplication getInstance(){
		if(sInstance == null){
			sInstance = new MainApplication();
		}
		return sInstance;
	}
}
