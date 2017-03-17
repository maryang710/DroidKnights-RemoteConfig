package io.github.droidknights.remoteconfig.util;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * Created by rfrost on 2017. 3. 15..
 */

public class FirebaseUtil {

    public static void initialize(Context context) {
        if (!FirebaseApp.getApps(context).isEmpty()) return;
        FirebaseApp.initializeApp(context, FirebaseOptions.fromResource(context));
    }
}
