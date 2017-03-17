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

        checkGooglePlayServices();

        RemoteConfigUtil.initialize();

        helper.getDefault();

        helper.getByCondition();

        helper.getByBuild();

        setActionText();
    }

    // TODO 3. 플레이 서비스 체크하기
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

    // TODO 10. 응용하여 받아오기 - value를 Json으로 구성하여 OOP스럽게 값을 변경한다.
    private void setActionText() {
        JsonElement json = helper.getAsJson();
        if (json == null || json.isJsonNull()) return;

        String text = json.getAsJsonObject().get("text").getAsString();
        String action = json.getAsJsonObject().get("action").getAsString();

        TextView txtAction = (TextView) findViewById(R.id.txt_action);
        txtAction.setText(text);
        txtAction.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(action));
            startActivity(intent);
        });
    }
}
