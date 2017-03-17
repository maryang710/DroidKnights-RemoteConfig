package io.github.droidknights.remoteconfig.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.github.droidknights.remoteconfig.R;

/**
 * Created by rfrost on 2017. 3. 18..
 */

public class ActionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
    }
}
