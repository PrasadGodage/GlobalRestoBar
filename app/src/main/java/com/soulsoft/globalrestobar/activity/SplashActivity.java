package com.soulsoft.globalrestobar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.soulsoft.globalrestobar.BaseActivity;
import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.utility.AllKeys;
import com.soulsoft.globalrestobar.utility.CommonMethods;

public class SplashActivity extends BaseActivity {

    private final int PERMISSION_ID = 44;

    @Override
    protected void onStart() {
        super.onStart();
        // FirebaseApp.initializeApp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int SPLASH_DISPLAY_DURATION = 2000;

        new Handler().postDelayed(() -> {
            /* Create an Intent that will start the Menu-Activity. */
            if (CommonMethods.getPrefrence(mContext, AllKeys.EMPCODE).equals(AllKeys.DNF)) {
                // check if permissions are given
                Intent intent = new Intent(mContext, IPAddressActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(mContext, DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_splash;
    }
}