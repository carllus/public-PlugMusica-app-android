package app.forrozapp.meuforroapp.android;

import android.content.Context;
import android.content.Intent;

public class BootUpReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent i) {
        Intent intent = new Intent(context, service.class);
        intent.putExtra("ServiceForroZappMain", "ForroZappMain");
        context.startService(intent);
    }
}
