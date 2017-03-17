package io.github.droidknights.remoteconfig.util;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.github.droidknights.remoteconfig.BuildConfig;
import io.github.droidknights.remoteconfig.R;

/**
 * Created by rfrost on 2017. 3. 17..
 */

public class RemoteConfigUtil {

    // TODO 5. 초기화. Remote Config Admin에 설정한 값들을 처음에 받아온다.
    public static void initialize() {
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                // 여러명의 개발자가 값을 빈번하게 바꿀 경우 Developer Mode를 enable 하여 캐쉬 설정을 변경한다.
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();

        remoteConfig.setConfigSettings(configSettings);
        remoteConfig.setDefaults(R.xml.remote_config_defaults);

        // 기본 캐쉬 만료시간은 12시간이다. 원래는 Developer Mode 여부에 따라 숫자를 바꾸지만 여기서는 Test를 위해 0으로 한다.
        remoteConfig.fetch(0).addOnCompleteListener(task -> {
            if (task.isSuccessful()) remoteConfig.activateFetched();
        });
    }

    // TODO 6. 값 받아오기.
    public static String getConfigValue(String key) {
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        return remoteConfig.getString(key);
    }

    public static String getConfigValueByBuild(String key) {
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        return remoteConfig.getString(getKey(key));
    }

    private static String getKey(String key) {
        if (BuildConfig.DEBUG) {
            return "dev_" + key;
        } else {
            return key;
        }
    }

    public static JsonElement getConfigJson(String key) {
        return new JsonParser().parse(getConfigValueByBuild(key));
    }
}
