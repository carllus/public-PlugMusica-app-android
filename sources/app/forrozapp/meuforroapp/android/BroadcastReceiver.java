package app.forrozapp.meuforroapp.android;

import android.content.Context;
import android.content.Intent;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!"YES_ACTION".equals(action) && !"MAYBE_ACTION".equals(action) && "NO_ACTION".equals(action)) {
        }
    }
}
