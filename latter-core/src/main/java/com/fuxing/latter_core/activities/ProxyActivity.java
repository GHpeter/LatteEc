package com.fuxing.latter_core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;

import com.fuxing.latter_core.R;
import com.fuxing.latter_core.delegates.LatteDalegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:
 **/
public abstract class ProxyActivity extends SupportActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    @SuppressLint("RestrictedApi")
    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    public abstract LatteDalegate setRootDelegate();
}
