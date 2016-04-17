package com.eathenliu.hospital.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.eathenliu.hospital.R;

public class BaseActivity extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setImmersiveStatueBar();
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setImmersiveStatueBar(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            int height = getStatusBarHeight();
            if(height > 0){
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
                FrameLayout statueBarBackground = (FrameLayout)findViewById(R.id.statueBarBackground);
                statueBarBackground.setLayoutParams(params);
                statueBarBackground.setVisibility(View.VISIBLE);
            }
        }
    }
}

