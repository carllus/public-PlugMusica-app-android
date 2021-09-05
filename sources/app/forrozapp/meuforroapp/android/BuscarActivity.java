package app.forrozapp.meuforroapp.android;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.plugmusica.application1.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BuscarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {

    /* renamed from: ANÚNCIO_ID  reason: contains not printable characters */
    private static final String f947ANNCIO_ID = "ca-app-pub-3491610413834288/4900950854";
    private static final String INTERCALAR_ID = "ca-app-pub-3491610413834288/6377684059";
    private static AdView adView1;
    static AdapterListView adapterListView;
    /* access modifiers changed from: private */
    public static ProgressDialog dialog2;
    static boolean fecha = false;
    static ArrayList<ItemListView> itens;
    static ListView listView;
    static List<ObjectItens> lista;
    static String query;
    /* access modifiers changed from: private */
    public ProgressDialog dialog;
    private InterstitialAd interstitial;
    /* access modifiers changed from: private */
    public Handler mhandler;
    /* access modifiers changed from: private */
    public ProgressDialog mprogressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_buscar);
        try {
            ((TextView) findViewById(R.id.txvSubtituloBusca)).setText(query.toUpperCase());
            this.mhandler = new Handler();
            preenche_listview();
            adView1 = (AdView) findViewById(R.id.adView);
            adView1.loadAd(new AdRequest.Builder().build());
            Intent i = new Intent(this, service.class);
            i.putExtra("ServiceForroZappBuscar", "ForroZappBuscar");
            startService(i);
        } catch (Exception e) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    /* access modifiers changed from: package-private */
    public void compartilhar_forrozapp() {
        Toast.makeText(this, "Compartilhar Aplicativo!", 1).show();
        this.mprogressDialog = new ProgressDialog(this);
        this.mprogressDialog.setCancelable(true);
        this.mprogressDialog.setIcon(R.mipmap.ic_launcher);
        this.mprogressDialog.setTitle("Compartilhar nas redes sociais...");
        this.mprogressDialog.setMessage("Compartilhar APLICATIVO! :-)");
        this.mprogressDialog.setProgressStyle(0);
        this.mprogressDialog.show();
        new Thread() {
            public void run() {
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.TEXT", "PlugMusica.com \nSeu site de conteúdo musical. \nhttps://plugmusica.com");
                    BuscarActivity.this.startActivity(Intent.createChooser(intent, "Share with"));
                } catch (Exception e) {
                }
                BuscarActivity.this.mhandler.post(new Runnable() {
                    public void run() {
                    }
                });
                BuscarActivity.this.mprogressDialog.dismiss();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void preenche_listview() {
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        itens = new ArrayList<>();
        for (ObjectItens unidade : lista) {
            itens.add(new ItemListView(unidade.getNome(), unidade.getDiretorio(), unidade.getFaixas(), Environment.getExternalStorageDirectory() + "/MeuForro/temp/" + unidade.getDiretorio() + ".jpg", unidade.getdowns(), unidade.getData(), unidade.getCd_cover(), unidade.getUrl(), unidade.getPrefixo(), unidade.getSufixo(), unidade.getId(), unidade.getGostei(), unidade.getViews()));
        }
        adapterListView = new AdapterListView(this, itens);
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
                    BuscarActivity.this.redirect_buscar1(edtBuscar.getText().toString());
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
    public void redirect_buscar1(final String query2) {
        this.dialog = ProgressDialog.show(this, "PlugMusica.com", "Carregando dados...", false, true);
        this.dialog.setIcon(R.mipmap.ic_launcher);
        this.dialog.setCancelable(true);
        new Thread() {
            public void run() {
                BuscarActivity.query = query2;
                BuscarActivity.lista = new ClassJSoup().buscar(query2);
                BuscarActivity.this.dialog.dismiss();
                BuscarActivity.this.redirect_buscar2();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void redirect_buscar2() {
        startActivity(new Intent(this, BuscarActivity.class));
        finish();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ListaMusicasActivity.Banda = adapterListView.getItem(position);
        dialog2 = ProgressDialog.show(this, "Carregando...", "Aguarde...", false, true);
        dialog2.setIcon(R.mipmap.ic_launcher);
        dialog2.setCancelable(true);
        new Thread() {
            public void run() {
                try {
                    Elements urls = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_musicas.php?q=" + ListaMusicasActivity.Banda.getId()).timeout(99999).get().select("musica");
                    ListaMusicasActivity.comnome.clear();
                    Iterator<Element> it = urls.iterator();
                    while (it.hasNext()) {
                        ListaMusicasActivity.comnome.add(it.next().select("nomemusica").text());
                    }
                } catch (Exception e) {
                }
                BuscarActivity.dialog2.dismiss();
                BuscarActivity.this.redirect();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void redirect() {
        finish();
        startActivity(new Intent(this, ListaMusicasActivity.class));
    }

    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        finish();
        startActivity(new Intent(this, MainActivity.class));
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        finish();
        super.onDestroy();
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
        fecha = true;
        new Thread() {
            public void run() {
                try {
                    Log.e("service", "log");
                    Thread.sleep(40000);
                    if (BuscarActivity.fecha) {
                        BuscarActivity.this.finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        fecha = false;
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
