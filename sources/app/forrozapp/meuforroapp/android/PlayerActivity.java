package app.forrozapp.meuforroapp.android;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.plugmusica.application1.R;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    /* renamed from: ANÚNCIO_ID  reason: contains not printable characters */
    private static final String f950ANNCIO_ID = "ca-app-pub-3491610413834288/4900950854";
    static ItemListView Banda = null;
    private static final String INTERCALAR_ID = "ca-app-pub-3491610413834288/6377684059";
    private static AdView adView1;
    static List<ObjectItens> comnome;
    static String currentTrack = "01";
    /* access modifiers changed from: private */
    public static ProgressDialog dialog2;
    static Intent intent_service;

    /* renamed from: db */
    SQLiteDatabase f31db;
    /* access modifiers changed from: private */
    public ProgressDialog dialog;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    private InterstitialAd mInterstitialAd;
    /* access modifiers changed from: private */
    public Handler mhandler;
    /* access modifiers changed from: private */
    public ProgressDialog mprogressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_player);
        this.mhandler = new Handler();
        intent_service = new Intent(this, servicePlayer.class);
        try {
            start();
        } catch (Exception e) {
            startActivity(new Intent(this, SplashActivity.class));
        }
        final ImageButton btnBaixar = (ImageButton) findViewById(R.id.btnBaixar);
        final ImageButton btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        final ImageButton btnStop = (ImageButton) findViewById(R.id.btnStop);
        final ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        final ImageButton btnNext = (ImageButton) findViewById(R.id.btnNext);
        final SeekBar mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
        runOnUiThread(new Runnable() {
            int ant = 0;

            public void run() {
                if (servicePlayer.mediaPlayer != null) {
                    try {
                        mSeekBar.setMax(servicePlayer.mediaPlayer.getDuration());
                        int carregando = servicePlayer.mediaPlayer.getCurrentPosition();
                        mSeekBar.setProgress(carregando);
                        if (servicePlayer.mediaPlayer.getCurrentPosition() + 0 != servicePlayer.mediaPlayer.getDuration() + 0 && servicePlayer.mediaPlayer.isPlaying()) {
                            btnPlay.setEnabled(true);
                            btnStop.setEnabled(true);
                            btnBack.setEnabled(true);
                            btnNext.setEnabled(true);
                            mSeekBar.setEnabled(true);
                            ((TextView) PlayerActivity.this.findViewById(R.id.txvFaixa)).setText(servicePlayer.faixa);
                        }
                        this.ant = carregando;
                    } catch (Exception e) {
                    }
                }
                PlayerActivity.this.mHandler.postDelayed(this, 1000);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (servicePlayer.mediaPlayer != null && fromUser) {
                    servicePlayer.mediaPlayer.seekTo(progress);
                }
            }
        });
        final ImageButton imageButton = btnPlay;
        final ImageButton imageButton2 = btnStop;
        final ImageButton imageButton3 = btnBack;
        final ImageButton imageButton4 = btnNext;
        final SeekBar seekBar = mSeekBar;
        btnBaixar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    PlayerActivity.this.chama_intersetial();
                    TextView txvfaixa = (TextView) PlayerActivity.this.findViewById(R.id.txvFaixa);
                    if (txvfaixa.getText() != "PARADO!...") {
                        btnBaixar.setEnabled(true);
                        imageButton.setEnabled(true);
                        imageButton2.setEnabled(true);
                        imageButton3.setEnabled(true);
                        imageButton4.setEnabled(true);
                        seekBar.setEnabled(true);
                        PlayerActivity.this.baixar(txvfaixa.getText().toString());
                        return;
                    }
                    PlayerActivity.this.mensagemquefaixa();
                } catch (Exception e) {
                }
            }
        });
        final ImageButton imageButton5 = btnPlay;
        final ImageButton imageButton6 = btnStop;
        final ImageButton imageButton7 = btnBack;
        final ImageButton imageButton8 = btnNext;
        final SeekBar seekBar2 = mSeekBar;
        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    btnBaixar.setEnabled(true);
                    imageButton5.setEnabled(true);
                    imageButton6.setEnabled(true);
                    imageButton7.setEnabled(false);
                    imageButton8.setEnabled(false);
                    seekBar2.setEnabled(false);
                    ((TextView) PlayerActivity.this.findViewById(R.id.txvFaixa)).setText("PARADO!...");
                    servicePlayer.mediaPlayer.stop();
                    PlayerActivity.this.stop();
                    PlayerActivity.this.stopService(PlayerActivity.intent_service);
                } catch (Exception e) {
                }
            }
        });
        final ImageButton imageButton9 = btnPlay;
        final ImageButton imageButton10 = btnStop;
        final ImageButton imageButton11 = btnBack;
        final ImageButton imageButton12 = btnNext;
        final SeekBar seekBar3 = mSeekBar;
        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    btnBaixar.setEnabled(true);
                    imageButton9.setEnabled(false);
                    imageButton10.setEnabled(true);
                    imageButton11.setEnabled(false);
                    imageButton12.setEnabled(false);
                    seekBar3.setEnabled(false);
                    ((TextView) PlayerActivity.this.findViewById(R.id.txvFaixa)).setText("AGUARDE UM MOMENTO!...");
                    servicePlayer.mediaPlayer.stop();
                    PlayerActivity.this.stop();
                } catch (Exception e) {
                }
                try {
                    PlayerActivity.this.start();
                } catch (Exception e2) {
                }
            }
        });
        final ImageButton imageButton13 = btnPlay;
        final ImageButton imageButton14 = btnStop;
        final ImageButton imageButton15 = btnBack;
        final ImageButton imageButton16 = btnNext;
        final SeekBar seekBar4 = mSeekBar;
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(servicePlayer.currentTrack) != 1) {
                        btnBaixar.setEnabled(true);
                        imageButton13.setEnabled(false);
                        imageButton14.setEnabled(true);
                        imageButton15.setEnabled(false);
                        imageButton16.setEnabled(false);
                        seekBar4.setEnabled(false);
                        ((TextView) PlayerActivity.this.findViewById(R.id.txvFaixa)).setText("AGUARDE UM MOMENTO!...");
                        PlayerActivity.this.voltar();
                        servicePlayer.mediaPlayer.seekTo(servicePlayer.mediaPlayer.getDuration());
                    }
                } catch (Exception e) {
                }
            }
        });
        final ImageButton imageButton17 = btnPlay;
        final ImageButton imageButton18 = btnStop;
        final ImageButton imageButton19 = btnBack;
        final ImageButton imageButton20 = btnNext;
        final SeekBar seekBar5 = mSeekBar;
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(servicePlayer.currentTrack) != Integer.parseInt(PlayerActivity.Banda.getFaixas())) {
                        btnBaixar.setEnabled(true);
                        imageButton17.setEnabled(false);
                        imageButton18.setEnabled(false);
                        imageButton19.setEnabled(false);
                        imageButton20.setEnabled(false);
                        seekBar5.setEnabled(false);
                        ((TextView) PlayerActivity.this.findViewById(R.id.txvFaixa)).setText("AGUARDE UM MOMENTO!...");
                        PlayerActivity.this.next();
                    }
                } catch (Exception e) {
                }
            }
        });
        setVolumeControlStream(3);
        adView1 = (AdView) findViewById(R.id.adView);
        adView1.loadAd(new AdRequest.Builder().build());
    }

    /* access modifiers changed from: private */
    public void showInterstitial() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setQueryHint("cidade ou banda");
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pesquisar) {
            final Dialog dialog3 = new Dialog(this);
            dialog3.setContentView(R.layout.dialog_busca);
            dialog3.setTitle("Pesquisar!");
            ((TextView) dialog3.findViewById(R.id.text)).setText("Nome da banda ou cidade...");
            final EditText edtBuscar = (EditText) dialog3.findViewById(R.id.edtBuscar);
            ((ImageButton) dialog3.findViewById(R.id.imgbtnBuscar)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog3.dismiss();
                    PlayerActivity.this.redirect_buscar1(edtBuscar.getText().toString());
                }
            });
            dialog3.show();
            return true;
        } else if (id == R.id.compartilhar) {
            compartilhar_forrozapp();
            return true;
        } else if (id == R.id.sobrenos) {
            Dialog dialog4 = new Dialog(this);
            dialog4.setContentView(R.layout.dialog_quemsomos);
            dialog4.setTitle("Reivindicações e termos...");
            dialog4.show();
            return true;
        } else if (id == R.id.facebook) {
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("fb://page/1570454153232223")));
                return true;
            } catch (Exception e) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/ForroZapp")));
                return true;
            }
        } else if (id != R.id.instagram) {
            return super.onOptionsItemSelected(item);
        } else {
            try {
                Intent iIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                iIntent.setComponent(new ComponentName("com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
                iIntent.setData(Uri.parse("http://instagram.com/_u/forrozapp/"));
                startActivity(iIntent);
                return true;
            } catch (Exception e2) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/forrozapp")));
                return true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void redirect_buscar1(final String query) {
        this.dialog = ProgressDialog.show(this, "ForróZapp", "Carregando dados...", false, true);
        this.dialog.setIcon(R.mipmap.ic_launcher);
        this.dialog.setCancelable(true);
        new Thread() {
            public void run() {
                BuscarActivity.query = query;
                BuscarActivity.lista = new ClassJSoup().buscar(query);
                PlayerActivity.this.dialog.dismiss();
                PlayerActivity.this.redirect_buscar2();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void redirect_buscar2() {
        startActivity(new Intent(this, BuscarActivity.class));
        finish();
    }

    /* access modifiers changed from: package-private */
    public void compartilhar_forrozapp() {
        Toast.makeText(this, "Compartilhar Show!", 1).show();
        this.mprogressDialog = new ProgressDialog(this);
        this.mprogressDialog.setCancelable(true);
        this.mprogressDialog.setIcon(R.mipmap.ic_launcher);
        this.mprogressDialog.setTitle("Compartilhar nas redes sociais...");
        this.mprogressDialog.setMessage("Compartilhar " + Banda.getDiretorio() + "! :-)");
        this.mprogressDialog.setProgressStyle(0);
        this.mprogressDialog.show();
        new Thread() {
            public void run() {
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.TEXT", "💥💥Estourou💥💥\nBaixe agora!💿💿 \n\n" + PlayerActivity.Banda.getTexto() + "\n😎🎶🎤🔝🔝💥💥\n\nAplicativo ForróZapp\n🎤💥⚡🔥💿📀💿💿TA SHOW!!💥💥\n\n. Disponível para:\nAndroid📱 e Windows Phone📲\nhttp://forrozapp.com.br/lista.php?banda=" + PlayerActivity.Banda.getTexto().replace(" ", "+") + "&id=" + PlayerActivity.Banda.getId());
                    PlayerActivity.this.startActivity(Intent.createChooser(intent, "Share with"));
                } catch (Exception e) {
                }
                PlayerActivity.this.mhandler.post(new Runnable() {
                    public void run() {
                    }
                });
                PlayerActivity.this.mprogressDialog.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void mensagemquefaixa() {
        Toast.makeText(this, "Baixar que faixa?", 1).show();
    }

    /* access modifiers changed from: package-private */
    public void next() {
        dialog2 = ProgressDialog.show(this, "PROCESSANDO...", "Aguarde...", false, true);
        dialog2.setIcon(R.mipmap.ic_launcher);
        dialog2.setCancelable(true);
        new Thread() {
            public void run() {
                servicePlayer.mediaPlayer.seekTo(servicePlayer.mediaPlayer.getDuration());
                PlayerActivity.dialog2.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void voltar() {
        dialog2 = ProgressDialog.show(this, "PROCESSANDO...", "Aguarde...", false, true);
        dialog2.setIcon(R.mipmap.ic_launcher);
        dialog2.setCancelable(true);
        new Thread() {
            public void run() {
                PlayerActivity.this.f31db = PlayerActivity.this.openOrCreateDatabase("db_forrozapp", 0, (SQLiteDatabase.CursorFactory) null);
                PlayerActivity.this.f31db.execSQL("CREATE TABLE IF NOT EXISTS player2(titulo VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, faixas VARCHAR, proxima VARCHAR, img VARCHAR, diretorio VARCHAR, downs VARCHAR, data VARCHAR, id VARCHAR);");
                int anterior = 1;
                Cursor c = PlayerActivity.this.f31db.rawQuery("SELECT * FROM player2", (String[]) null);
                if (c.moveToFirst()) {
                    PlayerActivity.Banda = new ItemListView();
                    PlayerActivity.Banda.setTexto(c.getString(0));
                    PlayerActivity.Banda.setUrl(c.getString(1));
                    PlayerActivity.Banda.setPrefixo(c.getString(2));
                    PlayerActivity.Banda.setSufixo(c.getString(3));
                    PlayerActivity.Banda.setFaixas(c.getString(4));
                    anterior = Integer.parseInt(c.getString(5)) - 2;
                    PlayerActivity.Banda.setIconeRid(c.getString(6));
                    PlayerActivity.Banda.setDiretorio(c.getString(7));
                    PlayerActivity.Banda.setDowns(c.getString(8));
                    PlayerActivity.Banda.setData(c.getString(9));
                    PlayerActivity.Banda.setId(c.getString(10));
                }
                try {
                    PlayerActivity.this.f31db.execSQL("DELETE FROM player2");
                } catch (Exception e) {
                }
                PlayerActivity.this.f31db.execSQL("INSERT INTO player2 VALUES('" + PlayerActivity.Banda.getTexto() + "','" + PlayerActivity.Banda.getUrl() + "','" + PlayerActivity.Banda.getPrefixo() + "','" + PlayerActivity.Banda.getSufixo() + "','" + PlayerActivity.Banda.getFaixas() + "','" + anterior + "', '" + PlayerActivity.Banda.getIconeRid() + "', '" + PlayerActivity.Banda.getDiretorio() + "', '" + PlayerActivity.Banda.getDowns() + "', '" + PlayerActivity.Banda.getData() + "', '" + PlayerActivity.Banda.getId() + "');");
                PlayerActivity.dialog2.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void start() {
        if (Banda != null) {
            this.f31db = openOrCreateDatabase("db_forrozapp", 0, (SQLiteDatabase.CursorFactory) null);
            this.f31db.execSQL("CREATE TABLE IF NOT EXISTS player2(titulo VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, faixas VARCHAR, proxima VARCHAR, img VARCHAR, diretorio VARCHAR, downs VARCHAR, data VARCHAR, id VARCHAR);");
            this.f31db.execSQL("CREATE TABLE IF NOT EXISTS musicas(nome VARCHAR, id VARCHAR);");
            try {
                this.f31db.execSQL("DELETE FROM player2");
                this.f31db.execSQL("DELETE FROM musicas");
            } catch (Exception e) {
            }
            this.f31db.execSQL("INSERT INTO player2 VALUES('" + Banda.getTexto() + "','" + Banda.getUrl() + "','" + Banda.getPrefixo() + "','" + Banda.getSufixo() + "','" + Banda.getFaixas() + "','" + 1 + "', '" + Banda.getIconeRid() + "', '" + Banda.getDiretorio() + "', '" + Banda.getDowns() + "', '" + Banda.getData() + "', '" + Banda.getId() + "');");
            int cont = 1;
            for (ObjectItens item : comnome) {
                this.f31db.execSQL("INSERT INTO musicas VALUES('" + item.getNome() + "','" + cont + "');");
                cont++;
            }
            intent_service.putExtra("ServiceForroZappPlayer", "ForroZappPlayer");
            startService(intent_service);
        } else {
            this.f31db = openOrCreateDatabase("db_forrozapp", 0, (SQLiteDatabase.CursorFactory) null);
            this.f31db.execSQL("CREATE TABLE IF NOT EXISTS player2(titulo VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, faixas VARCHAR, proxima VARCHAR, img VARCHAR, diretorio VARCHAR, downs VARCHAR, data VARCHAR, id VARCHAR);");
            this.f31db.execSQL("CREATE TABLE IF NOT EXISTS musicas(nome VARCHAR, id VARCHAR);");
            Cursor c = this.f31db.rawQuery("SELECT * FROM player2", (String[]) null);
            if (c.moveToFirst()) {
                Banda = new ItemListView();
                Banda.setTexto(c.getString(0));
                Banda.setUrl(c.getString(1));
                Banda.setPrefixo(c.getString(2));
                Banda.setSufixo(c.getString(3));
                Banda.setFaixas(c.getString(4));
                Banda.setIconeRid(c.getString(6));
                Banda.setDiretorio(c.getString(7));
                Banda.setDowns(c.getString(8));
                Banda.setData(c.getString(9));
                Banda.setId(c.getString(10));
            }
            comnome = new ArrayList();
            Cursor c2 = this.f31db.rawQuery("SELECT * FROM musicas", (String[]) null);
            while (c2.moveToNext()) {
                ObjectItens temp = new ObjectItens();
                temp.setNome(c2.getString(0));
                temp.setId(c2.getString(1));
                comnome.add(temp);
            }
        }
        atualiza_textos();
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        stopService(intent_service);
    }

    /* access modifiers changed from: package-private */
    public void atualiza_textos() {
        ImageView imgBanda = (ImageView) findViewById(R.id.imgBanda);
        if (Banda.getCd_cover() == null) {
            Picasso.with(this).load(Banda.getCd_cover().replace(" ", "%20")).into(imgBanda);
        } else {
            Picasso.with(this).load((Banda.getUrl() + "/cd_cover.jpg").replace(" ", "%20")).into(imgBanda);
        }
        ((TextView) findViewById(R.id.txvText)).setText(Banda.getTexto());
        ((TextView) findViewById(R.id.txvSubtext)).setText("Postado em " + Banda.getData() + " com " + Banda.getDowns() + " downloads");
        ((TextView) findViewById(R.id.txvFaixa)).setText(servicePlayer.faixa);
    }

    /* access modifiers changed from: package-private */
    public void baixar(final String faixa) {
        Toast.makeText(this, "Baixar " + faixa + " - " + Banda.getDiretorio(), 1).show();
        this.mprogressDialog = new ProgressDialog(this);
        this.mprogressDialog.setCancelable(true);
        this.mprogressDialog.setIcon(R.mipmap.ic_launcher);
        this.mprogressDialog.setTitle("Baixando " + faixa + "...");
        this.mprogressDialog.setMessage("/sdcard/MeuForro/" + Banda.getDiretorio().substring(0, 10) + "...");
        chama_intersetial();
        this.mprogressDialog.setProgressStyle(0);
        this.mprogressDialog.show();
        new Thread() {
            public void run() {
                ClassDownloads baixar = new ClassDownloads();
                try {
                    ClassJSoup MaisUm = new ClassJSoup();
                    baixar.download_jsoup(PlayerActivity.Banda.getCd_cover(), "/" + PlayerActivity.Banda.getDiretorio(), "cd_cover.jpg");
                    Thread.sleep(1000);
                    PlayerActivity.this.forceMediaScan("cd_cover.jpg");
                    if (servicePlayer.comnome.size() == 0 || servicePlayer.comnome == null) {
                        baixar.downloadFile(PlayerActivity.Banda.getUrl() + "/" + faixa, "/" + PlayerActivity.Banda.getDiretorio(), faixa, PlayerActivity.this.getBaseContext());
                        MaisUm.mais_um(faixa, PlayerActivity.Banda.getId());
                    } else {
                        baixar.downloadFile(PlayerActivity.Banda.getUrl() + "/" + servicePlayer.comnome.get(Integer.parseInt(servicePlayer.currentTrack) - 1).getNome(), "/" + PlayerActivity.Banda.getDiretorio(), faixa, PlayerActivity.this.getBaseContext());
                        MaisUm.mais_um(faixa, PlayerActivity.Banda.getId());
                    }
                    Thread.sleep(5000);
                    PlayerActivity.this.forceMediaScan(faixa);
                } catch (Exception e) {
                }
                PlayerActivity.this.mhandler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(PlayerActivity.this.getApplicationContext(), "Concluído", 1).show();
                    }
                });
                PlayerActivity.this.mprogressDialog.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void forceMediaScan(String nome) {
        String path = "/sdcard/MeuForro/" + Banda.getDiretorio() + "/" + nome;
        new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE").setData(Uri.fromFile(new File(path)));
        MediaScannerConnection.scanFile(this, new String[]{path}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
    }

    /* access modifiers changed from: package-private */
    public void chama_intersetial() {
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                PlayerActivity.this.showInterstitial();
            }
        });
    }

    public void onBackPressed() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService("connectivity");
        boolean is3g = manager.getNetworkInfo(0).isConnectedOrConnecting();
        if (manager.getNetworkInfo(1).isConnectedOrConnecting() || is3g) {
            ListaMusicasActivity.Banda = Banda;
            dialog2 = ProgressDialog.show(this, "ForróZapp", "Carregando dados...", false, true);
            dialog2.setIcon(R.mipmap.ic_launcher);
            dialog2.setCancelable(true);
            final Intent it = new Intent(this, ListaMusicasActivity.class);
            new Thread() {
                public void run() {
                    PlayerActivity.this.finish();
                    PlayerActivity.this.startActivity(it);
                    PlayerActivity.dialog2.dismiss();
                }
            }.start();
            return;
        }
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        ConnectivityManager manager = (ConnectivityManager) getSystemService("connectivity");
        boolean is3g = manager.getNetworkInfo(0).isConnectedOrConnecting();
        if (manager.getNetworkInfo(1).isConnectedOrConnecting() || is3g) {
            ListaMusicasActivity.Banda = Banda;
            dialog2 = ProgressDialog.show(this, "ForróZapp", "Carregando dados...", false, true);
            dialog2.setIcon(R.mipmap.ic_launcher);
            dialog2.setCancelable(true);
            final Intent it = new Intent(this, ListaMusicasActivity.class);
            new Thread() {
                public void run() {
                    PlayerActivity.this.finish();
                    PlayerActivity.this.startActivity(it);
                    PlayerActivity.dialog2.dismiss();
                }
            }.start();
            return true;
        }
        finish();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
    }

    public boolean onQueryTextChange(String arg0) {
        return false;
    }

    public boolean onQueryTextSubmit(String arg0) {
        redirect_buscar1(arg0);
        return false;
    }
}
