package io.github.droidknights.remoteconfig.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rfrost on 2017. 3. 15..
 */

public class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected BaseActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        this.context = this;
        this.activity = this;
    }
}
