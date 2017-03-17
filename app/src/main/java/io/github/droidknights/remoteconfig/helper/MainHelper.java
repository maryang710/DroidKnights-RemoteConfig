package io.github.droidknights.remoteconfig.helper;

import android.util.Log;

import com.google.gson.JsonElement;

import io.github.droidknights.remoteconfig.constant.RemoteConfigConstant;
import io.github.droidknights.remoteconfig.util.RemoteConfigUtil;

/**
 * Created by rfrost on 2017. 3. 18..
 */

public class MainHelper {

    private static final String TAG = MainHelper.class.getSimpleName();

    // TODO 6. 값 받아오기.
    public void getDefault() {
        String example = RemoteConfigUtil.getConfigValue(RemoteConfigConstant.EXAMPLE_KEY);
        Log.i(TAG, "example: " + example);

        /**
         * TODO 7. 기본 값 설정하기.
         * Admin에서 값을 받아오기 전에 실행될 경우 Crash가 난다.
         * res - xml - remote_config_defaults.xml 이 파일에 로컬 기본값을 설정해놓아야 한다.
         */

        // TODO 8. 변경 값 실시간으로 가져오기. RemoteConfig fetch로 업데이트 후 알아서 가져온다.
    }

    // TODO 9. 조건에 따라 값 가져오기
    public void getByCondition() {
        String conditionExample = RemoteConfigUtil.getConfigValue(RemoteConfigConstant.CONDITION_EXAMPLE_KEY);
        Log.i(TAG, "conditionExample: " + conditionExample);
    }

    // TODO 10. 응용하여 받아오기 - key를 Build Mode에 따라 분기한다.
    public void getByBuild() {
        String someByBuild = RemoteConfigUtil.getConfigValueByBuild(RemoteConfigConstant.SOME_KEY);
        Log.i(TAG, "someByBuild: " + someByBuild);
    }

    public JsonElement getAsJson() {
        JsonElement someJson = RemoteConfigUtil.getConfigJson(RemoteConfigConstant.SOME_JSON_KEY);
        Log.i(TAG, "someJson: " + someJson);
        return someJson;
    }
}
