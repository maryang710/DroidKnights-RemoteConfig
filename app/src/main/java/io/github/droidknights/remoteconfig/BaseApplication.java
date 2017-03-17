package io.github.droidknights.remoteconfig;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.github.droidknights.remoteconfig.util.FirebaseUtil;

/**
 * Created by rfrost on 2017. 3. 15..
 */

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    public static BaseApplication get() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        startApplication();
    }

    private void startApplication() {
        Stetho.initializeWithDefaults(this);

        // TODO Tip. 파이어 베이스 Application에서 초기화 하기.
        FirebaseUtil.initialize(this);
    }
}
