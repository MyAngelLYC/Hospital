package com.eathenliu.hospital.activity;

import com.eathenliu.hospital.R;
import com.eathenliu.hospital.fragment.HospitalFragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout mTitleBar;
    private LinearLayout mTitleLeftButton;
    private LinearLayout mTitleRightButton;
	private View mTitleView;
    private Button mIntroductionTab;
    private Button mDoctorTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.viewContainer, new HospitalFragment())
                    .commit();
        }
    }

    private void initView(){
        mTitleBar = (RelativeLayout)findViewById(R.id.titleBarContainer);
        mTitleLeftButton = (LinearLayout)mTitleBar.findViewById(R.id.titleLeftButton);
        mTitleRightButton = (LinearLayout)mTitleBar.findViewById(R.id.titleRightButton);
        ViewStub mTitleViewStub = (ViewStub)mTitleBar.findViewById(R.id.titleViewStub);
        mTitleViewStub.setLayoutResource(R.layout.tab_button);
        mTitleView = mTitleViewStub.inflate();
        mIntroductionTab = (Button)mTitleView.findViewById(R.id.tabButtonLeft);
        mDoctorTab = (Button)mTitleView.findViewById(R.id.tabButtonRight);
        mIntroductionTab.setOnClickListener(this);
        mDoctorTab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tabButtonLeft:
                changeTabButtonState(mIntroductionTab, false);
                changeTabButtonState(mDoctorTab, true);
                break;
            case R.id.tabButtonRight:
                changeTabButtonState(mIntroductionTab, true);
                changeTabButtonState(mDoctorTab, false);
                break;
        }
    }

   private void changeTabButtonState(Button tabButton, boolean state){
        tabButton.setEnabled(state);
        tabButton.setClickable(state);
    }
}
