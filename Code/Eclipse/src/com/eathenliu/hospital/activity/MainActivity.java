package com.eathenliu.hospital.activity;

import com.eathenliu.hospital.R;
import com.eathenliu.hospital.fragment.HospitalFragment;

import android.os.Bundle;

public class MainActivity extends BaseActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.viewContainer, new HospitalFragment())
                    .commit();
        }
    }

}
