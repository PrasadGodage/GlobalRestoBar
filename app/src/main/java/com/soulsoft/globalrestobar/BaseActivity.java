package com.soulsoft.globalrestobar;

import android.content.Context;
import android.icu.util.LocaleData;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.soulsoft.globalrestobar.services.GlobalRestoBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.paperdb.Paper;

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;
    public Unbinder unbinder;
    public GlobalRestoBar globalRestoBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        unbinder = ButterKnife.bind(this);
        globalRestoBar = (GlobalRestoBar) getApplication();
        mContext = this;
        Paper.init(mContext);
    }

    protected abstract int getActivityLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
