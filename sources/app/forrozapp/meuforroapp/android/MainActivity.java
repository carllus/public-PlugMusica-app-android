package app.forrozapp.meuforroapp.android;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentPagerAdapter;
import android.support.p000v4.view.ViewPager;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.SearchView;
import android.support.p003v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    /* renamed from: ANÚNCIO_ID  reason: contains not printable characters */
    private static final String f949ANNCIO_ID = "ca-app-pub-3491610413834288/4900950854";
    private static final String INTERCALAR_ID = "ca-app-pub-3491610413834288/6377684059";
    static String Vardestaque1;
    static String Vardestaque2;
    static String Vardestaque3;
    /* access modifiers changed from: private */
    public static AdView adView1;
    /* access modifiers changed from: private */
    public static AdView adView2;
    /* access modifiers changed from: private */
    public static AdView adView3;
    /* access modifiers changed from: private */
    public static AdView adView4;
    /* access modifiers changed from: private */
    public static AdView adView5;
    /* access modifiers changed from: private */
    public static AdView adView6;
    static AdapterListView_Destaques adapterListView1;
    static AdapterListView adapterListView2;
    static AdapterListView adapterListView3;
    static AdapterListView adapterListView4;
    static AdapterListView adapterListView5;
    static AdapterListView adapterListView6;

    /* renamed from: db */
    static SQLiteDatabase f29db;
    static boolean fecha = false;
    static ArrayList<ItemListView> itens;
    static ListView listView;
    static List<ObjectItens> lista1;
    static List<ObjectItens> lista2;
    static List<ObjectItens> lista3;
    static List<ObjectItens> lista4;
    static List<ObjectItens> lista5;
    static List<ObjectItens> lista6;
    /* access modifiers changed from: private */
    public ProgressDialog dialog;
    private InterstitialAd mInterstitialAd;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    /* access modifiers changed from: private */
    public Handler mhandler;
    /* access modifiers changed from: private */
    public ProgressDialog mprogressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) findViewById(R.id.container);
        this.mViewPager.setAdapter(this.mSectionsPagerAdapter);
        ((TabLayout) findViewById(R.id.tabs)).setupWithViewPager(this.mViewPager);
        f29db = openOrCreateDatabase("db_plugmusica", 0, (SQLiteDatabase.CursorFactory) null);
        this.mhandler = new Handler();
    }

    private void showInterstitial() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
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
                    MainActivity.this.startActivity(Intent.createChooser(intent, "Share with"));
                } catch (Exception e) {
                }
                MainActivity.this.mhandler.post(new Runnable() {
                    public void run() {
                    }
                });
                MainActivity.this.mprogressDialog.dismiss();
            }
        }.start();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setQueryHint("cidade ou banda");
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pesquisar) {
            final Dialog dialog2 = new Dialog(this);
            dialog2.setContentView(R.layout.dialog_busca);
            dialog2.setTitle("Pesquisar!");
            ((TextView) dialog2.findViewById(R.id.text)).setText("Nome da banda ou cidade...");
            final EditText edtBuscar = (EditText) dialog2.findViewById(R.id.edtBuscar);
            ((ImageButton) dialog2.findViewById(R.id.imgbtnBuscar)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog2.dismiss();
                    MainActivity.this.redirect_buscar1(edtBuscar.getText().toString());
                }
            });
            dialog2.show();
            return true;
        } else if (id == R.id.compartilhar) {
            compartilhar_forrozapp();
            return true;
        } else if (id == R.id.sobrenos) {
            Dialog dialog3 = new Dialog(this);
            dialog3.setContentView(R.layout.dialog_quemsomos);
            dialog3.setTitle("Reivindicações e termos...");
            dialog3.show();
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
        this.dialog = ProgressDialog.show(this, "PlugMusica.com", "Carregando dados...", false, true);
        this.dialog.setIcon(R.mipmap.ic_launcher);
        this.dialog.setCancelable(true);
        new Thread() {
            public void run() {
                BuscarActivity.query = query;
                BuscarActivity.lista = new ClassJSoup().buscar(query);
                MainActivity.this.dialog.dismiss();
                MainActivity.this.redirect_buscar2();
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void redirect_buscar2() {
        startActivity(new Intent(this, BuscarActivity.class));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        public int getCount() {
            return 6;
        }

        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return MainActivity.this.getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return MainActivity.this.getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return MainActivity.this.getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return MainActivity.this.getString(R.string.title_section4).toUpperCase(l);
                case 4:
                    return MainActivity.this.getString(R.string.title_section5).toUpperCase(l);
                case 5:
                    return MainActivity.this.getString(R.string.title_section6).toUpperCase(l);
                default:
                    return null;
            }
        }
    }

    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener {
        private static final String ARG_SECTION_NUMBER = "section_number";
        /* access modifiers changed from: private */
        public static ProgressDialog dialog2;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int codAba = getArguments().getInt(ARG_SECTION_NUMBER);
            if (codAba == 1) {
                View rootView = inflater.inflate(R.layout.fragment_main_maisbaixados, container, false);
                MainActivity.listView = (ListView) rootView.findViewById(R.id.list);
                MainActivity.listView.setOnItemClickListener(this);
                MainActivity.itens = new ArrayList<>();
                Cursor c = MainActivity.f29db.rawQuery("SELECT * FROM destaques", (String[]) null);
                while (c.moveToNext()) {
                    String id = c.getString(0);
                    String nome = c.getString(1);
                    String string = c.getString(2);
                    String string2 = c.getString(3);
                    String string3 = c.getString(4);
                    String cd_cover = c.getString(5);
                    String faixas = c.getString(6);
                    String downs = c.getString(7);
                    String views = c.getString(8);
                    String data = c.getString(9);
                    String diretorio = c.getString(10);
                    String string4 = c.getString(11);
                    String url = c.getString(12);
                    String prefixo = c.getString(13);
                    String sufixo = c.getString(14);
                    String gostei = c.getString(15);
                    String string5 = c.getString(16);
                    String string6 = c.getString(17);
                    String string7 = c.getString(18);
                    MainActivity.itens.add(new ItemListView(nome, diretorio, faixas, "", downs, data, cd_cover, url, prefixo, sufixo, id, gostei, views));
                }
                if (MainActivity.itens.size() == 0) {
                    volta_splash();
                }
                MainActivity.adapterListView1 = new AdapterListView_Destaques(getActivity(), MainActivity.itens);
                MainActivity.listView.setAdapter(MainActivity.adapterListView1);
                MainActivity.listView.setCacheColorHint(0);
                AdView unused = MainActivity.adView1 = (AdView) rootView.findViewById(R.id.adView);
                MainActivity.adView1.loadAd(new AdRequest.Builder().build());
                return rootView;
            } else if (codAba == 2) {
                View rootView2 = inflater.inflate(R.layout.fragment_main_maisbaixados, container, false);
                MainActivity.listView = (ListView) rootView2.findViewById(R.id.list);
                MainActivity.listView.setOnItemClickListener(this);
                MainActivity.itens = new ArrayList<>();
                Cursor c2 = MainActivity.f29db.rawQuery("SELECT * FROM topbimestre", (String[]) null);
                while (c2.moveToNext()) {
                    String id2 = c2.getString(0);
                    String nome2 = c2.getString(1);
                    String string8 = c2.getString(2);
                    String string9 = c2.getString(3);
                    String string10 = c2.getString(4);
                    String cd_cover2 = c2.getString(5);
                    String faixas2 = c2.getString(6);
                    String downs2 = c2.getString(7);
                    String views2 = c2.getString(8);
                    String data2 = c2.getString(9);
                    String diretorio2 = c2.getString(10);
                    String string11 = c2.getString(11);
                    String url2 = c2.getString(12);
                    String prefixo2 = c2.getString(13);
                    String sufixo2 = c2.getString(14);
                    String gostei2 = c2.getString(15);
                    String string12 = c2.getString(16);
                    String string13 = c2.getString(17);
                    String string14 = c2.getString(18);
                    MainActivity.itens.add(new ItemListView(nome2, diretorio2, faixas2, "", downs2, data2, cd_cover2, url2, prefixo2, sufixo2, id2, gostei2, views2));
                }
                if (MainActivity.itens.size() == 0) {
                    volta_splash();
                }
                MainActivity.adapterListView2 = new AdapterListView(getActivity(), MainActivity.itens);
                MainActivity.listView.setAdapter(MainActivity.adapterListView2);
                MainActivity.listView.setCacheColorHint(0);
                AdView unused2 = MainActivity.adView2 = (AdView) rootView2.findViewById(R.id.adView);
                MainActivity.adView2.loadAd(new AdRequest.Builder().build());
                return rootView2;
            } else if (codAba == 3) {
                View rootView3 = inflater.inflate(R.layout.fragment_main_recentes, container, false);
                MainActivity.listView = (ListView) rootView3.findViewById(R.id.list);
                MainActivity.listView.setOnItemClickListener(this);
                MainActivity.itens = new ArrayList<>();
                Cursor c3 = MainActivity.f29db.rawQuery("SELECT * FROM topmes", (String[]) null);
                while (c3.moveToNext()) {
                    String id3 = c3.getString(0);
                    String nome3 = c3.getString(1);
                    String string15 = c3.getString(2);
                    String string16 = c3.getString(3);
                    String string17 = c3.getString(4);
                    String cd_cover3 = c3.getString(5);
                    String faixas3 = c3.getString(6);
                    String downs3 = c3.getString(7);
                    String views3 = c3.getString(8);
                    String data3 = c3.getString(9);
                    String diretorio3 = c3.getString(10);
                    String string18 = c3.getString(11);
                    String url3 = c3.getString(12);
                    String prefixo3 = c3.getString(13);
                    String sufixo3 = c3.getString(14);
                    String gostei3 = c3.getString(15);
                    String string19 = c3.getString(16);
                    String string20 = c3.getString(17);
                    String string21 = c3.getString(18);
                    MainActivity.itens.add(new ItemListView(nome3, diretorio3, faixas3, "", downs3, data3, cd_cover3, url3, prefixo3, sufixo3, id3, gostei3, views3));
                }
                if (MainActivity.itens.size() == 0) {
                    volta_splash();
                }
                MainActivity.adapterListView3 = new AdapterListView(getActivity(), MainActivity.itens);
                MainActivity.listView.setAdapter(MainActivity.adapterListView3);
                MainActivity.listView.setCacheColorHint(0);
                AdView unused3 = MainActivity.adView3 = (AdView) rootView3.findViewById(R.id.adView);
                MainActivity.adView3.loadAd(new AdRequest.Builder().build());
                return rootView3;
            } else if (codAba == 4) {
                View rootView4 = inflater.inflate(R.layout.fragment_main_recentes, container, false);
                MainActivity.listView = (ListView) rootView4.findViewById(R.id.list);
                MainActivity.listView.setOnItemClickListener(this);
                MainActivity.itens = new ArrayList<>();
                Cursor c4 = MainActivity.f29db.rawQuery("SELECT * FROM topsemana", (String[]) null);
                while (c4.moveToNext()) {
                    String id4 = c4.getString(0);
                    String nome4 = c4.getString(1);
                    String string22 = c4.getString(2);
                    String string23 = c4.getString(3);
                    String string24 = c4.getString(4);
                    String cd_cover4 = c4.getString(5);
                    String faixas4 = c4.getString(6);
                    String downs4 = c4.getString(7);
                    String views4 = c4.getString(8);
                    String data4 = c4.getString(9);
                    String diretorio4 = c4.getString(10);
                    String string25 = c4.getString(11);
                    String url4 = c4.getString(12);
                    String prefixo4 = c4.getString(13);
                    String sufixo4 = c4.getString(14);
                    String gostei4 = c4.getString(15);
                    String string26 = c4.getString(16);
                    String string27 = c4.getString(17);
                    String string28 = c4.getString(18);
                    MainActivity.itens.add(new ItemListView(nome4, diretorio4, faixas4, "", downs4, data4, cd_cover4, url4, prefixo4, sufixo4, id4, gostei4, views4));
                }
                if (MainActivity.itens.size() == 0) {
                    volta_splash();
                }
                MainActivity.adapterListView4 = new AdapterListView(getActivity(), MainActivity.itens);
                MainActivity.listView.setAdapter(MainActivity.adapterListView4);
                MainActivity.listView.setCacheColorHint(0);
                AdView unused4 = MainActivity.adView4 = (AdView) rootView4.findViewById(R.id.adView);
                MainActivity.adView4.loadAd(new AdRequest.Builder().build());
                return rootView4;
            } else if (codAba == 5) {
                View rootView5 = inflater.inflate(R.layout.fragment_main_recentes, container, false);
                MainActivity.listView = (ListView) rootView5.findViewById(R.id.list);
                MainActivity.listView.setOnItemClickListener(this);
                MainActivity.itens = new ArrayList<>();
                Cursor c5 = MainActivity.f29db.rawQuery("SELECT * FROM top48h", (String[]) null);
                while (c5.moveToNext()) {
                    String id5 = c5.getString(0);
                    String nome5 = c5.getString(1);
                    String string29 = c5.getString(2);
                    String string30 = c5.getString(3);
                    String string31 = c5.getString(4);
                    String cd_cover5 = c5.getString(5);
                    String faixas5 = c5.getString(6);
                    String downs5 = c5.getString(7);
                    String views5 = c5.getString(8);
                    String data5 = c5.getString(9);
                    String diretorio5 = c5.getString(10);
                    String string32 = c5.getString(11);
                    String url5 = c5.getString(12);
                    String prefixo5 = c5.getString(13);
                    String sufixo5 = c5.getString(14);
                    String gostei5 = c5.getString(15);
                    String string33 = c5.getString(16);
                    String string34 = c5.getString(17);
                    String string35 = c5.getString(18);
                    MainActivity.itens.add(new ItemListView(nome5, diretorio5, faixas5, "", downs5, data5, cd_cover5, url5, prefixo5, sufixo5, id5, gostei5, views5));
                }
                if (MainActivity.itens.size() == 0) {
                    volta_splash();
                }
                MainActivity.adapterListView5 = new AdapterListView(getActivity(), MainActivity.itens);
                MainActivity.listView.setAdapter(MainActivity.adapterListView5);
                MainActivity.listView.setCacheColorHint(0);
                AdView unused5 = MainActivity.adView5 = (AdView) rootView5.findViewById(R.id.adView);
                MainActivity.adView5.loadAd(new AdRequest.Builder().build());
                return rootView5;
            } else {
                View rootView6 = inflater.inflate(R.layout.fragment_main_recentes, container, false);
                MainActivity.listView = (ListView) rootView6.findViewById(R.id.list);
                MainActivity.listView.setOnItemClickListener(this);
                MainActivity.itens = new ArrayList<>();
                Cursor c6 = MainActivity.f29db.rawQuery("SELECT * FROM maisrecentes", (String[]) null);
                while (c6.moveToNext()) {
                    String id6 = c6.getString(0);
                    String nome6 = c6.getString(1);
                    String string36 = c6.getString(2);
                    String string37 = c6.getString(3);
                    String string38 = c6.getString(4);
                    String cd_cover6 = c6.getString(5);
                    String faixas6 = c6.getString(6);
                    String downs6 = c6.getString(7);
                    String views6 = c6.getString(8);
                    String data6 = c6.getString(9);
                    String diretorio6 = c6.getString(10);
                    String string39 = c6.getString(11);
                    String url6 = c6.getString(12);
                    String prefixo6 = c6.getString(13);
                    String sufixo6 = c6.getString(14);
                    String gostei6 = c6.getString(15);
                    String string40 = c6.getString(16);
                    String string41 = c6.getString(17);
                    String string42 = c6.getString(18);
                    MainActivity.itens.add(new ItemListView(nome6, diretorio6, faixas6, "", downs6, data6, cd_cover6, url6, prefixo6, sufixo6, id6, gostei6, views6));
                }
                if (MainActivity.itens.size() == 0) {
                    volta_splash();
                }
                MainActivity.adapterListView6 = new AdapterListView(getActivity(), MainActivity.itens);
                MainActivity.listView.setAdapter(MainActivity.adapterListView6);
                MainActivity.listView.setCacheColorHint(0);
                AdView unused6 = MainActivity.adView6 = (AdView) rootView6.findViewById(R.id.adView);
                MainActivity.adView6.loadAd(new AdRequest.Builder().build());
                return rootView6;
            }
        }

        /* access modifiers changed from: package-private */
        public void volta_splash() {
            startActivity(new Intent(getActivity(), SplashActivity.class));
            getActivity().finish();
        }

        public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
            ItemListView item;
            int codAba = getArguments().getInt(ARG_SECTION_NUMBER);
            if (codAba == 1) {
                item = MainActivity.adapterListView1.getItem(arg2);
            } else if (codAba == 2) {
                item = MainActivity.adapterListView2.getItem(arg2);
            } else if (codAba == 3) {
                item = MainActivity.adapterListView3.getItem(arg2);
            } else if (codAba == 4) {
                item = MainActivity.adapterListView4.getItem(arg2);
            } else if (codAba == 5) {
                item = MainActivity.adapterListView5.getItem(arg2);
            } else {
                item = MainActivity.adapterListView6.getItem(arg2);
            }
            ListaMusicasActivity.Banda = item;
            dialog2 = ProgressDialog.show(getContext(), "Carregando...", "Aguarde...", false, true);
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
                    PlaceholderFragment.dialog2.dismiss();
                    PlaceholderFragment.this.redirect();
                }
            }.start();
        }

        /* access modifiers changed from: package-private */
        public void redirect() {
            startActivity(new Intent(getActivity(), ListaMusicasActivity.class));
            getActivity().finish();
        }
    }

    public void onBackPressed() {
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        finish();
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
                    if (MainActivity.fecha) {
                        MainActivity.this.finish();
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
