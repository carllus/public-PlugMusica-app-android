package app.forrozapp.meuforroapp.android;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.p000v4.app.ActivityCompat;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.SearchView;
import android.support.p003v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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

public class ListaMusicasActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener, MediaScannerConnection.MediaScannerConnectionClient {

    /* renamed from: ANÚNCIO_ID  reason: contains not printable characters */
    private static final String f948ANNCIO_ID = "ca-app-pub-3491610413834288/4900950854";
    static ItemListView Banda = null;
    private static final String FILE_TYPE = "audio/*";
    private static final String INTERCALAR_ID = "ca-app-pub-3491610413834288/6377684059";
    private static AdView adView1;
    static AdapterListView_Musicas adapterListView;
    static List<String> comnome = new ArrayList();
    static ArrayList<ItemListView_Musicas> itens;
    static ListView listView;
    private String SCAN_PATH;
    public String[] allFiles;
    private MediaScannerConnection conn;

    /* renamed from: db */
    SQLiteDatabase f28db;
    /* access modifiers changed from: private */
    public ProgressDialog dialog;
    /* access modifiers changed from: private */
    public ProgressDialog dialog2;
    boolean javotou = false;
    private InterstitialAd mInterstitialAd;
    MediaPlayer mediaPlayer;
    /* access modifiers changed from: private */
    public Handler mhandler;
    /* access modifiers changed from: private */
    public ProgressDialog mprogressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_lista_musicas);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        isStoragePermissionGranted();
        this.mhandler = new Handler();
        preenche_listview();
        adView1 = (AdView) findViewById(R.id.adView);
        adView1.loadAd(new AdRequest.Builder().build());
        mais_view();
        ((ImageButton) findViewById(R.id.imgbtnBaixar)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ListaMusicasActivity.this.chama_intersetial();
                if (ListaMusicasActivity.comnome.size() == 0) {
                    ListaMusicasActivity.this.baixar("todos!", true, Integer.parseInt(ListaMusicasActivity.Banda.getFaixas()), 1);
                } else {
                    ListaMusicasActivity.this.baixar_comnome("todos!", true, Integer.parseInt(ListaMusicasActivity.Banda.getFaixas()), 1);
                }
            }
        });
        ((ImageButton) findViewById(R.id.imgbtnShare)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ListaMusicasActivity.this.compartilhar_forrozapp();
            }
        });
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        return false;
    }

    /* access modifiers changed from: private */
    public void showInterstitial() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
        }
    }

    /* access modifiers changed from: package-private */
    public void mais_view() {
        this.dialog2 = ProgressDialog.show(this, "Carregando...", "Aguarde...", false, true);
        this.dialog2.setIcon(R.mipmap.ic_launcher);
        this.dialog2.setCancelable(true);
        new Thread() {
            public void run() {
                new ClassJSoup().mais_view(ListaMusicasActivity.Banda.getId());
                ListaMusicasActivity.this.dialog2.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void votar() {
        this.dialog2 = ProgressDialog.show(this, "Computando voto...", "Aguarde...", false, true);
        this.dialog2.setIcon(R.mipmap.ic_launcher);
        this.dialog2.setCancelable(true);
        new Thread() {
            public void run() {
                new ClassJSoup().mais_voto(ListaMusicasActivity.Banda.getId());
                ListaMusicasActivity.this.javotou = true;
                ListaMusicasActivity.this.dialog2.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void redirect_player() {
        this.f28db = openOrCreateDatabase("db_forrozapp", 0, (SQLiteDatabase.CursorFactory) null);
        this.f28db.execSQL("CREATE TABLE IF NOT EXISTS player2(titulo VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, faixas VARCHAR, proxima VARCHAR, img VARCHAR, diretorio VARCHAR, downs VARCHAR, data VARCHAR, id VARCHAR);");
        if (this.f28db.rawQuery("SELECT * FROM player2", (String[]) null).moveToFirst()) {
            finish();
            startActivity(new Intent(this, PlayerActivity.class));
            return;
        }
        Toast.makeText(getApplicationContext(), "Selecione uma banda para ouvir :-)", 1).show();
    }

    /* access modifiers changed from: package-private */
    public void player() {
        this.dialog2 = ProgressDialog.show(this, "ForróZapp", "Preparando Player...", false, true);
        this.dialog2.setIcon(R.mipmap.ic_launcher);
        this.dialog2.setCancelable(true);
        new Thread() {
            public void run() {
                try {
                    ListaMusicasActivity.this.stopService(PlayerActivity.intent_service);
                } catch (Exception e) {
                }
                PlayerActivity.Banda = ListaMusicasActivity.Banda;
                ListaMusicasActivity.Banda = null;
                ListaMusicasActivity.this.redirectplayer();
                ListaMusicasActivity.this.finish();
                ListaMusicasActivity.this.dialog2.dismiss();
            }
        }.start();
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
                    intent.putExtra("android.intent.extra.TEXT", ListaMusicasActivity.Banda.getTexto() + "\n\nhttps://plugmusica.com/" + ListaMusicasActivity.Banda.getTexto().replace(" ", "-").replace(" ", "").replace("---", "-").toLowerCase());
                    ListaMusicasActivity.this.startActivity(Intent.createChooser(intent, "Share with"));
                } catch (Exception e) {
                }
                ListaMusicasActivity.this.mhandler.post(new Runnable() {
                    public void run() {
                    }
                });
                ListaMusicasActivity.this.mprogressDialog.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void reconhece_media() {
        this.allFiles = new File("/sdcard/PlugMusica/" + Banda.getDiretorio() + "/").list();
        for (int i = 0; i < this.allFiles.length; i++) {
            Log.d("all file path" + i, this.allFiles[i] + this.allFiles.length);
        }
        this.SCAN_PATH = Environment.getExternalStorageDirectory().toString() + "/PlugMusica/" + Banda.getDiretorio() + "/" + this.allFiles[0];
        System.out.println(" SCAN_PATH  " + this.SCAN_PATH);
        startScan();
    }

    private void startScan() {
        Log.d("Connected", "success" + this.conn);
        if (this.conn != null) {
            this.conn.disconnect();
        }
        this.conn = new MediaScannerConnection(this, this);
        this.conn.connect();
    }

    /* access modifiers changed from: package-private */
    public void baixar(String Texto, final boolean progress, final int faixas, final int inicio) {
        Toast.makeText(this, "Baixar " + Texto, 1).show();
        this.mprogressDialog = new ProgressDialog(this);
        this.mprogressDialog.setCancelable(false);
        this.mprogressDialog.setIcon(R.mipmap.ic_launcher);
        this.mprogressDialog.setTitle("Baixando " + Texto + "...");
        try {
            this.mprogressDialog.setMessage("/sdcard/PlugMusica/" + Banda.getDiretorio().substring(0, 10) + "...");
        } catch (Exception e) {
            this.mprogressDialog.setMessage("/sdcard/PlugMusica/" + Banda.getDiretorio() + "...");
        }
        chama_intersetial();
        if (progress) {
            this.mprogressDialog.setProgressStyle(1);
            this.mprogressDialog.setProgress(0);
            this.mprogressDialog.setMax(Integer.parseInt(Banda.getFaixas()) + 1);
        } else {
            this.mprogressDialog.setProgressStyle(0);
        }
        this.mprogressDialog.show();
        new Thread() {
            public void run() {
                ClassDownloads baixar = new ClassDownloads();
                try {
                    ClassJSoup MaisUm = new ClassJSoup();
                    if (progress) {
                        MaisUm.mais_todos(ListaMusicasActivity.Banda.getId());
                    }
                    baixar.download_jsoup(ListaMusicasActivity.Banda.getCd_cover(), "/" + ListaMusicasActivity.Banda.getDiretorio(), "cd_cover.jpg");
                    Thread.sleep(1000);
                    ListaMusicasActivity.this.forceMediaScan("cd_cover.jpg");
                    ListaMusicasActivity.this.mprogressDialog.incrementProgressBy(1);
                    for (int temp = inicio; temp <= faixas; temp++) {
                        if (temp < 10) {
                            if (progress) {
                                baixar.downloadFile(ListaMusicasActivity.Banda.getUrl() + "/" + ListaMusicasActivity.Banda.getPrefixo() + "0" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), "/" + ListaMusicasActivity.Banda.getDiretorio(), ListaMusicasActivity.Banda.getPrefixo() + "0" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), ListaMusicasActivity.this.getBaseContext());
                                Thread.sleep(1500);
                            } else {
                                baixar.downloadFile(ListaMusicasActivity.Banda.getUrl() + "/" + ListaMusicasActivity.Banda.getPrefixo() + "0" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), "/" + ListaMusicasActivity.Banda.getDiretorio(), ListaMusicasActivity.Banda.getPrefixo() + "0" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), ListaMusicasActivity.this.getBaseContext());
                                Thread.sleep(5000);
                                MaisUm.mais_um(ListaMusicasActivity.Banda.getPrefixo() + "0" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), ListaMusicasActivity.Banda.getId());
                            }
                            ListaMusicasActivity.this.forceMediaScan(ListaMusicasActivity.Banda.getPrefixo() + "0" + temp + "" + ListaMusicasActivity.Banda.getSufixo());
                        } else {
                            if (progress) {
                                baixar.downloadFile(ListaMusicasActivity.Banda.getUrl() + "/" + ListaMusicasActivity.Banda.getPrefixo() + "" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), "/" + ListaMusicasActivity.Banda.getDiretorio(), ListaMusicasActivity.Banda.getPrefixo() + "" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), ListaMusicasActivity.this.getBaseContext());
                                Thread.sleep(1500);
                            } else {
                                baixar.downloadFile(ListaMusicasActivity.Banda.getUrl() + "/" + ListaMusicasActivity.Banda.getPrefixo() + "" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), "/" + ListaMusicasActivity.Banda.getDiretorio(), ListaMusicasActivity.Banda.getPrefixo() + "" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), ListaMusicasActivity.this.getBaseContext());
                                Thread.sleep(5000);
                                MaisUm.mais_um(ListaMusicasActivity.Banda.getPrefixo() + "" + temp + "" + ListaMusicasActivity.Banda.getSufixo(), ListaMusicasActivity.Banda.getId());
                            }
                            ListaMusicasActivity.this.forceMediaScan(ListaMusicasActivity.Banda.getPrefixo() + "" + temp + "" + ListaMusicasActivity.Banda.getSufixo());
                        }
                        ListaMusicasActivity.this.mprogressDialog.incrementProgressBy(1);
                    }
                } catch (Exception e) {
                }
                ListaMusicasActivity.this.mhandler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(ListaMusicasActivity.this.getApplicationContext(), "Downloads iniciados :-)", 1).show();
                    }
                });
                ListaMusicasActivity.this.mprogressDialog.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void chama_intersetial() {
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                ListaMusicasActivity.this.showInterstitial();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void baixar_comnome(String Texto, final boolean progress, final int faixas, final int inicio) {
        Toast.makeText(this, "Baixar " + Texto, 1).show();
        this.mprogressDialog = new ProgressDialog(this);
        this.mprogressDialog.setCancelable(true);
        this.mprogressDialog.setIcon(R.mipmap.ic_launcher);
        this.mprogressDialog.setTitle("Baixando " + Texto + "...");
        try {
            this.mprogressDialog.setMessage("/sdcard/PlugMusica/" + Banda.getDiretorio().substring(0, 10) + "...");
        } catch (Exception e) {
            this.mprogressDialog.setMessage("/sdcard/PlugMusica/" + Banda.getDiretorio() + "...");
        }
        chama_intersetial();
        if (progress) {
            this.mprogressDialog.setProgressStyle(1);
            this.mprogressDialog.setProgress(0);
            this.mprogressDialog.setMax(Integer.parseInt(Banda.getFaixas()) + 1);
        } else {
            this.mprogressDialog.setProgressStyle(0);
        }
        this.mprogressDialog.show();
        new Thread() {
            public void run() {
                ClassDownloads baixar = new ClassDownloads();
                try {
                    ClassJSoup MaisUm = new ClassJSoup();
                    if (progress) {
                        MaisUm.mais_todos(ListaMusicasActivity.Banda.getId());
                    }
                    baixar.download_jsoup(ListaMusicasActivity.Banda.getCd_cover(), "/" + ListaMusicasActivity.Banda.getDiretorio(), "cd_cover.jpg");
                    Thread.sleep(1000);
                    ListaMusicasActivity.this.forceMediaScan("cd_cover.jpg");
                    ListaMusicasActivity.this.mprogressDialog.incrementProgressBy(1);
                    int cont = 1;
                    for (String item : ListaMusicasActivity.comnome) {
                        if (cont >= inicio && cont <= faixas) {
                            if (cont < 10) {
                                String num = "0" + cont;
                            } else {
                                String num2 = "" + cont;
                            }
                            if (progress) {
                                baixar.downloadFile(ListaMusicasActivity.Banda.getUrl() + "/" + item, "/" + ListaMusicasActivity.Banda.getDiretorio(), item, ListaMusicasActivity.this.getBaseContext());
                                Thread.sleep(1500);
                            } else {
                                baixar.downloadFile(ListaMusicasActivity.Banda.getUrl() + "/" + item, "/" + ListaMusicasActivity.Banda.getDiretorio(), item, ListaMusicasActivity.this.getBaseContext());
                                Thread.sleep(5000);
                                MaisUm.mais_um(item, ListaMusicasActivity.Banda.getId());
                            }
                            ListaMusicasActivity.this.forceMediaScan(item);
                            ListaMusicasActivity.this.mprogressDialog.incrementProgressBy(1);
                        }
                        cont++;
                    }
                } catch (Exception e) {
                }
                ListaMusicasActivity.this.mhandler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(ListaMusicasActivity.this.getApplicationContext(), "Download iniciado..", 1).show();
                    }
                });
                ListaMusicasActivity.this.mprogressDialog.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void preenche_listview() {
        ItemListView_Musicas item;
        ImageView imgBanda = (ImageView) findViewById(R.id.imgIcoBanda);
        ((TextView) findViewById(R.id.txvTituloBanda)).setText(Banda.getTexto());
        ((TextView) findViewById(R.id.txvSubtituloBanda)).setText("Postado em " + Banda.getData() + "\n" + Banda.getViews() + " views & " + Banda.getDowns() + " downloads");
        if (Banda.getCd_cover() == null) {
            Picasso.with(this).load(Banda.getCd_cover().replace(" ", "%20")).into(imgBanda);
        } else {
            Picasso.with(this).load((Banda.getUrl() + "/cd_cover.jpg").replace(" ", "%20")).into(imgBanda);
        }
        listView = (ListView) findViewById(R.id.listViewMusicas);
        listView.setOnItemClickListener(this);
        itens = new ArrayList<>();
        int quantfaixas = Integer.parseInt(Banda.getFaixas());
        if (comnome.size() == 0) {
            for (int a = 1; a <= quantfaixas; a++) {
                if (a < 10) {
                    item = new ItemListView_Musicas(Banda.getPrefixo() + "0" + a + "" + Banda.getSufixo());
                } else {
                    item = new ItemListView_Musicas(Banda.getPrefixo() + "" + a + "" + Banda.getSufixo());
                }
                itens.add(item);
            }
        } else {
            for (int a2 = 0; a2 < quantfaixas; a2++) {
                itens.add(new ItemListView_Musicas(comnome.get(a2)));
            }
        }
        adapterListView = new AdapterListView_Musicas(this, itens);
        listView.setAdapter(adapterListView);
        listView.setCacheColorHint(0);
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
                    ListaMusicasActivity.this.redirect_buscar1(edtBuscar.getText().toString());
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
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("fb://page/159031938031848")));
                return true;
            } catch (Exception e) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/plugmusica1/")));
                return true;
            }
        } else if (id != R.id.instagram) {
            return super.onOptionsItemSelected(item);
        } else {
            try {
                Intent iIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                iIntent.setComponent(new ComponentName("com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
                iIntent.setData(Uri.parse("https://www.instagram.com/plugmusica1/"));
                startActivity(iIntent);
                return true;
            } catch (Exception e2) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.instagram.com/plugmusica1/")));
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
                ListaMusicasActivity.this.dialog.dismiss();
                ListaMusicasActivity.this.redirect_buscar2();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void redirect_buscar2() {
        startActivity(new Intent(this, BuscarActivity.class));
        finish();
    }

    public void onItemClick(AdapterView<?> adapterView, View arg1, final int arg2, long arg3) {
        final Dialog dialog3 = new Dialog(this);
        dialog3.setContentView(R.layout.dialog_musica);
        dialog3.setTitle("Selecione a opção...");
        ((TextView) dialog3.findViewById(R.id.text)).setText("");
        ((ImageButton) dialog3.findViewById(R.id.imgbtnPlay)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog3.dismiss();
                ItemListView_Musicas item = ListaMusicasActivity.adapterListView.getItem(arg2);
                new ClassDownloads();
                if (ListaMusicasActivity.comnome.size() > 0) {
                    ListaMusicasActivity.this.startActivity(new Intent("android.intent.action.VIEW").setDataAndType(Uri.parse(ListaMusicasActivity.Banda.getUrl().replace(" ", "%20") + "/" + ListaMusicasActivity.comnome.get(arg2).replace(" ", "%20") + ""), "audio/mp3"));
                    return;
                }
                ListaMusicasActivity.this.startActivity(new Intent("android.intent.action.VIEW").setDataAndType(Uri.parse(ListaMusicasActivity.Banda.getUrl().replace(" ", "%20") + "/" + item.getTexto().replace(" ", "%20") + ""), "audio/mp3"));
            }
        });
        ((ImageButton) dialog3.findViewById(R.id.imgbtnBaixar)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog3.dismiss();
                ListaMusicasActivity.this.chama_intersetial();
                if (ListaMusicasActivity.comnome.size() == 0) {
                    int indice = arg2 + 1;
                    if (indice < 10) {
                        ListaMusicasActivity.this.baixar(ListaMusicasActivity.Banda.getPrefixo() + "0" + indice + "" + ListaMusicasActivity.Banda.getSufixo(), false, indice, indice);
                    } else {
                        ListaMusicasActivity.this.baixar(ListaMusicasActivity.Banda.getPrefixo() + "" + indice + "" + ListaMusicasActivity.Banda.getSufixo(), false, indice, indice);
                    }
                } else {
                    int indice2 = arg2;
                    ListaMusicasActivity.this.baixar_comnome(ListaMusicasActivity.comnome.get(indice2), false, indice2, indice2);
                }
            }
        });
        dialog3.show();
    }

    /* access modifiers changed from: package-private */
    public void mediaplayer(String stream, String faixa) {
        final Dialog dialog3 = new Dialog(this);
        dialog3.setContentView(R.layout.dialog_player);
        dialog3.setTitle("Click 'X' para PARAR...");
        ((TextView) dialog3.findViewById(R.id.text)).setText("Reproduzindo!\n\nExecutando: " + faixa.toUpperCase() + "\nDisco:" + Banda.getTexto());
        ((ImageButton) dialog3.findViewById(R.id.imgbtnFechar)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog3.dismiss();
                try {
                    ListaMusicasActivity.this.mediaPlayer.stop();
                } catch (Exception e) {
                }
            }
        });
        dialog3.show();
        try {
            this.mediaPlayer.stop();
        } catch (Exception e) {
        }
        this.mediaPlayer = MediaPlayer.create(this, Uri.parse(stream));
        this.mediaPlayer.start();
    }

    /* access modifiers changed from: package-private */
    public void intentplay() {
        startActivity(new Intent("android.intent.action.VIEW").setDataAndType(Uri.parse("http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3"), "audio/mp3"));
    }

    public void onBackPressed() {
        try {
            this.mediaPlayer.stop();
        } catch (Exception e) {
        }
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        try {
            this.mediaPlayer.stop();
        } catch (Exception e) {
        }
        finish();
        startActivity(new Intent(this, MainActivity.class));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void forceMediaScan(String nome) {
        String path = "/sdcard/PlugMusica/" + Banda.getDiretorio() + "/" + nome;
        new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE").setData(Uri.fromFile(new File(path)));
        MediaScannerConnection.scanFile(this, new String[]{path}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
    }

    public void onMediaScannerConnected() {
        Log.d("onMediaScannerConnected", "success" + this.conn);
        this.conn.scanFile(this.SCAN_PATH, FILE_TYPE);
    }

    public void onScanCompleted(String path, Uri uri) {
        try {
            Log.d("onScanCompleted", uri + "success" + this.conn);
            System.out.println("URI " + uri);
            if (uri != null) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        } catch (Exception e) {
            this.conn.disconnect();
            this.conn = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            this.mediaPlayer.stop();
        } catch (Exception e) {
        }
        finish();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
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

    /* access modifiers changed from: package-private */
    public void redirectplayer() {
        startActivity(new Intent(this, PlayerActivity.class));
    }
}
