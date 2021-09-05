package app.forrozapp.meuforroapp.android;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;
import java.util.List;

public class service extends Service {
    static ItemListView Banda;
    static List<ObjectItens> lista1;
    static List<ObjectItens> lista2;
    static List<ObjectItens> lista3;

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        tread();
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public void tread() {
        new Thread() {
            public void run() {
                ConnectivityManager manager = (ConnectivityManager) service.this.getSystemService("connectivity");
                boolean isConnectedOrConnecting = manager.getNetworkInfo(0).isConnectedOrConnecting();
                boolean isConnectedOrConnecting2 = manager.getNetworkInfo(1).isConnectedOrConnecting();
                int a = 1;
                while (true) {
                    if (manager.getNetworkInfo(1).isConnectedOrConnecting()) {
                        try {
                            Log.e("service", "log");
                            if (MainActivity.lista1 == null || MainActivity.lista2 == null || MainActivity.lista3 == null) {
                                service.this.pega_novidades();
                                a = 1;
                            } else if (MainActivity.lista1.size() == 0 || MainActivity.lista2.size() == 0 || MainActivity.lista3.size() == 0) {
                                service.this.pega_novidades();
                                a = 1;
                            } else if (a > 5) {
                                service.this.pega_novidades();
                                a = 1;
                            } else {
                                a++;
                            }
                            Thread.sleep(100000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            Thread.sleep(100000);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void pega_novidades() {
        ClassJSoup jsoup = new ClassJSoup();
        do {
            MainActivity.lista1 = jsoup.destaques();
        } while (MainActivity.lista1 == null);
        do {
            MainActivity.lista2 = jsoup.topbimestre();
        } while (MainActivity.lista2 == null);
        do {
            MainActivity.lista3 = jsoup.topmes();
        } while (MainActivity.lista3 == null);
        do {
            MainActivity.lista4 = jsoup.topsemana();
        } while (MainActivity.lista4 == null);
        do {
            MainActivity.lista5 = jsoup.maisrecentes();
        } while (MainActivity.lista5 == null);
    }

    private class OwnThread extends Thread {
        private OwnThread() {
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    return;
                }
            }
        }
    }
}
