package com.fuxing.festec;

import com.fuxing.latter_core.activities.ProxyActivity;
import com.fuxing.latter_core.delegates.LatteDalegate;

public class MainActivity extends ProxyActivity {


    @Override
    public LatteDalegate setRootDelegate() {
        return new MainDelegate();

    }


}

