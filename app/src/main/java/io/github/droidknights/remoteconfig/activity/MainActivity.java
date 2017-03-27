package io.github.droidknights.remoteconfig.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.JsonElement;

import io.github.droidknights.remoteconfig.R;
import io.github.droidknights.remoteconfig.helper.MainHelper;
import io.github.droidknights.remoteconfig.util.RemoteConfigUtil;

public class MainActivity extends BaseActivity {

    private MainHelper helper = new MainHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 3. 플레이 서비스 체크하기.
        checkGooglePlayServices();

        // TODO 4. 어드민에서 값 정의하기.

        // TODO 5. 초기화. Remote Config Admin에 설정한 값들을 처음에 받아온다.
        RemoteConfigUtil.initialize();

        // TODO 6. 값 받아오기.
        helper.getDefault();

        // TODO 9. 조건에 따라 값 가져오기
        helper.getByCondition();

        // TODO 10. Test, Product 모드 구분하기.
        helper.getByBuild();

        // TODO 11. 응용하여 받아오기 - value를 Json으로 구성하여 OOP스럽게 값을 변경한다.
        setActionText();
    }

    private void checkGooglePlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(context);

        if (status != ConnectionResult.SUCCESS) {
            Dialog dialog = googleApiAvailability.getErrorDialog(activity, status, -1);
            dialog.setOnDismissListener(dialogInterface -> finish());
            dialog.show();

            googleApiAvailability.showErrorNotification(context, status);
        }
    }

    private void setActionText() {
        JsonElement json = helper.getAsJson();
        if (json == null || json.isJsonNull()) return;

        TextView txtAction = (TextView) findViewById(R.id.txt_action);

        JsonElement textJson = json.getAsJsonObject().get("text");
        if (textJson != null && !textJson.isJsonNull()) {
            String text = textJson.getAsString();
            txtAction.setText(text);
        }

        JsonElement actionJson = json.getAsJsonObject().get("action");
        if (actionJson != null && !actionJson.isJsonNull()) {
            txtAction.setOnClickListener(v -> {
                String action = actionJson.getAsString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(action));
                startActivity(intent);
            });
        }
    }
}
