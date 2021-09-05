package app.forrozapp.meuforroapp.android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import com.plugmusica.application1.R;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class servicePlayer extends Service implements MediaPlayer.OnCompletionListener {
    static List<ObjectItens> comnome;
    static String currentTrack;
    static String faixa;
    static MediaPlayer mediaPlayer;
    ItemListView Banda = new ItemListView();

    /* renamed from: db */
    SQLiteDatabase f35db;

    public void onCreate() {
        super.onCreate();
        comnome = new ArrayList();
        play();
    }

    /* access modifiers changed from: package-private */
    public void play() {
        this.f35db = openOrCreateDatabase("db_forrozapp", 0, (SQLiteDatabase.CursorFactory) null);
        updatebd();
        try {
            if (comnome.size() == 0) {
                mediaplayer(this.Banda.getUrl() + "/" + this.Banda.getPrefixo() + currentTrack + this.Banda.getSufixo());
            } else {
                mediaplayer(this.Banda.getUrl() + "/" + comnome.get(Integer.parseInt(currentTrack) - 1).getNome());
            }
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    public void mediaplayer(String stream) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
        faixa = "forrozapp-" + currentTrack + ".mp3";
        try {
            mediaPlayer.stop();
        } catch (Exception e) {
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(3);
        mediaPlayer.setDataSource(stream);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.prepare();
        mediaPlayer.start();
        notification();
    }

    /* access modifiers changed from: package-private */
    public void deleteall() {
        try {
            this.f35db.execSQL("DELETE FROM player2");
            this.f35db.execSQL("DELETE FROM musicas");
        } catch (Exception e) {
        }
    }

    public void onCompletion(MediaPlayer arg0) {
        try {
            arg0.release();
        } catch (Exception e) {
        }
        updatebd();
        try {
            if (comnome.size() == 0) {
                mediaplayer(this.Banda.getUrl() + "/" + this.Banda.getPrefixo() + currentTrack + this.Banda.getSufixo());
            } else {
                mediaplayer(this.Banda.getUrl() + "/" + comnome.get(Integer.parseInt(currentTrack) - 1).getNome());
            }
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: package-private */
    public void updatebd() {
        select();
        deleteall();
        if (Integer.parseInt(PlayerActivity.currentTrack) >= Integer.parseInt(this.Banda.getFaixas())) {
            PlayerActivity.currentTrack = "01";
        }
        this.f35db.execSQL("INSERT INTO player2 VALUES('" + this.Banda.getTexto() + "','" + this.Banda.getUrl() + "','" + this.Banda.getPrefixo() + "','" + this.Banda.getSufixo() + "','" + this.Banda.getFaixas() + "','" + (Integer.parseInt(currentTrack) + 1) + "', '" + this.Banda.getIconeRid() + "', '" + this.Banda.getDiretorio() + "', '" + this.Banda.getDowns() + "', '" + this.Banda.getData() + "', '" + this.Banda.getId() + "');");
        int cont = 1;
        for (ObjectItens item : comnome) {
            this.f35db.execSQL("INSERT INTO musicas VALUES('" + item.getNome() + "','" + cont + "');");
            cont++;
        }
    }

    /* access modifiers changed from: package-private */
    public void select() {
        Cursor c = this.f35db.rawQuery("SELECT * FROM player2", (String[]) null);
        if (c.moveToFirst()) {
            this.Banda.setTexto(c.getString(0));
            this.Banda.setUrl(c.getString(1));
            this.Banda.setPrefixo(c.getString(2));
            this.Banda.setSufixo(c.getString(3));
            this.Banda.setFaixas(c.getString(4));
            String prox = c.getString(5);
            this.Banda.setIconeRid(c.getString(6));
            this.Banda.setDiretorio(c.getString(7));
            this.Banda.setDowns(c.getString(8));
            this.Banda.setData(c.getString(9));
            this.Banda.setId(c.getString(10));
            if (Integer.parseInt(prox) < 10) {
                prox = "0" + prox;
            }
            currentTrack = prox;
        }
        comnome = new ArrayList();
        Cursor c2 = this.f35db.rawQuery("SELECT * FROM musicas", (String[]) null);
        while (c2.moveToNext()) {
            ObjectItens temp = new ObjectItens();
            temp.setNome(c2.getString(0));
            temp.setId(c2.getString(1));
            comnome.add(temp);
        }
    }

    /* access modifiers changed from: package-private */
    public void notification() {
        NotificationManager mgr = (NotificationManager) getSystemService("notification");
        try {
            mgr.cancel(1337);
        } catch (Exception e) {
        }
        new Notification(R.mipmap.play, "Executando " + faixa + " - " + this.Banda.getTexto(), System.currentTimeMillis());
        mgr.notify(1337, new Notification.Builder(getBaseContext()).setContentTitle("Executando: " + faixa).setContentText("" + this.Banda.getTexto()).setSmallIcon(R.mipmap.play).setContentIntent(PendingIntent.getActivity(getBaseContext(), 0, new Intent(getBaseContext(), PlayerActivity.class), 0)).build());
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(strURL).openConnection();
            connection.setDoInput(true);
            connection.connect();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public void tread() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        if (!servicePlayer.mediaPlayer.isPlaying()) {
                            servicePlayer.mediaPlayer.seekTo(servicePlayer.mediaPlayer.getCurrentPosition());
                            servicePlayer.mediaPlayer.start();
                        }
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
