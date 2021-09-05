package app.forrozapp.meuforroapp.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import com.google.analytics.tracking.android.HitTypes;
import com.plugmusica.application1.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SplashActivity extends AppCompatActivity {

    /* renamed from: db */
    SQLiteDatabase f34db;
    private ProgressDialog dialog;
    TextView txvaviso;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().hide();
        ConnectivityManager manager = (ConnectivityManager) getSystemService("connectivity");
        boolean is3g = manager.getNetworkInfo(0).isConnectedOrConnecting();
        boolean isWifi = manager.getNetworkInfo(1).isConnectedOrConnecting();
        this.txvaviso = (TextView) findViewById(R.id.txvAviso);
        if (is3g || isWifi) {
            if (is3g) {
                this.txvaviso.setText("USANDO REDE DE DADOS");
                this.txvaviso.setTextColor(Color.parseColor("#ffffff"));
            } else {
                this.txvaviso.setText("CARREGANDO NOVIDADES!");
                this.txvaviso.setTextColor(Color.parseColor("#ffffff"));
            }
            if (MainActivity.lista1 == null || MainActivity.lista2 == null || MainActivity.lista3 == null) {
                espera();
            } else if (MainActivity.lista1.size() == 0 || MainActivity.lista2.size() == 0 || MainActivity.lista3.size() == 0) {
                espera();
            } else {
                tread();
            }
            this.f34db = openOrCreateDatabase("db_plugmusica", 0, (SQLiteDatabase.CursorFactory) null);
            try {
                this.f34db.execSQL("DELETE FROM destaques");
            } catch (Exception e) {
            }
            this.f34db.execSQL("CREATE TABLE IF NOT EXISTS destaques(id VARCHAR, nome VARCHAR, estado VARCHAR, cidade VARCHAR, showdata VARCHAR, cd_cover VARCHAR, faixas VARCHAR, downs VARCHAR, views VARCHAR, data VARCHAR, diretorio VARCHAR, destaque VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, gostei VARCHAR, infor_post VARCHAR, gerador_youtube VARCHAR, id_usuario VARCHAR);");
            try {
                this.f34db.execSQL("DELETE FROM topbimestre");
            } catch (Exception e2) {
            }
            this.f34db.execSQL("CREATE TABLE IF NOT EXISTS topbimestre(id VARCHAR, nome VARCHAR, estado VARCHAR, cidade VARCHAR, showdata VARCHAR, cd_cover VARCHAR, faixas VARCHAR, downs VARCHAR, views VARCHAR, data VARCHAR, diretorio VARCHAR, destaque VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, gostei VARCHAR, infor_post VARCHAR, gerador_youtube VARCHAR, id_usuario VARCHAR);");
            try {
                this.f34db.execSQL("DELETE FROM topmes");
            } catch (Exception e3) {
            }
            this.f34db.execSQL("CREATE TABLE IF NOT EXISTS topmes(id VARCHAR, nome VARCHAR, estado VARCHAR, cidade VARCHAR, showdata VARCHAR, cd_cover VARCHAR, faixas VARCHAR, downs VARCHAR, views VARCHAR, data VARCHAR, diretorio VARCHAR, destaque VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, gostei VARCHAR, infor_post VARCHAR, gerador_youtube VARCHAR, id_usuario VARCHAR);");
            try {
                this.f34db.execSQL("DELETE FROM topsemana");
            } catch (Exception e4) {
            }
            this.f34db.execSQL("CREATE TABLE IF NOT EXISTS topsemana(id VARCHAR, nome VARCHAR, estado VARCHAR, cidade VARCHAR, showdata VARCHAR, cd_cover VARCHAR, faixas VARCHAR, downs VARCHAR, views VARCHAR, data VARCHAR, diretorio VARCHAR, destaque VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, gostei VARCHAR, infor_post VARCHAR, gerador_youtube VARCHAR, id_usuario VARCHAR);");
            try {
                this.f34db.execSQL("DELETE FROM top48h");
            } catch (Exception e5) {
            }
            this.f34db.execSQL("CREATE TABLE IF NOT EXISTS top48h(id VARCHAR, nome VARCHAR, estado VARCHAR, cidade VARCHAR, showdata VARCHAR, cd_cover VARCHAR, faixas VARCHAR, downs VARCHAR, views VARCHAR, data VARCHAR, diretorio VARCHAR, destaque VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, gostei VARCHAR, infor_post VARCHAR, gerador_youtube VARCHAR, id_usuario VARCHAR);");
            try {
                this.f34db.execSQL("DELETE FROM maisrecentes");
            } catch (Exception e6) {
            }
            this.f34db.execSQL("CREATE TABLE IF NOT EXISTS maisrecentes(id VARCHAR, nome VARCHAR, estado VARCHAR, cidade VARCHAR, showdata VARCHAR, cd_cover VARCHAR, faixas VARCHAR, downs VARCHAR, views VARCHAR, data VARCHAR, diretorio VARCHAR, destaque VARCHAR, url VARCHAR, prefixo VARCHAR, sufixo VARCHAR, gostei VARCHAR, infor_post VARCHAR, gerador_youtube VARCHAR, id_usuario VARCHAR);");
            try {
                this.f34db.execSQL("DELETE FROM musicas");
            } catch (Exception e7) {
            }
            this.f34db.execSQL("CREATE TABLE IF NOT EXISTS musicas(idMusicas VARCHAR, id_cds VARCHAR, nome VARCHAR);");
            return;
        }
        this.txvaviso.setText("CONECTE A INTERNET\ne tente novamente!");
        this.txvaviso.setTextColor(Color.parseColor("#ff0000"));
    }

    /* access modifiers changed from: package-private */
    public void tread() {
        new Thread() {
            public void run() {
                try {
                    Log.e("service", "log");
                    Thread.sleep(10);
                    SplashActivity.this.redirect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void espera() {
        new Thread() {
            public void run() {
                new ClassJSoup();
                SplashActivity.this.destaques();
                SplashActivity.this.topbimestre();
                SplashActivity.this.topmes();
                SplashActivity.this.topsemana();
                SplashActivity.this.top48h();
                SplashActivity.this.maisrecentes();
                SplashActivity.this.redirect();
            }
        }.start();
    }

    public void redirect() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /* access modifiers changed from: package-private */
    public void destaques() {
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_destaque.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
            while (it.hasNext()) {
                Element url = it.next();
                this.f34db.execSQL("INSERT INTO destaques VALUES('" + url.select("id").text() + "','" + url.select("nome").text() + "','" + url.select("estado").text() + "','" + url.select("cidade").text() + "','" + url.select("showdata").text() + "','" + url.select("cd_cover").text() + "','" + url.select("faixas").text() + "','" + url.select("downs").text() + "','" + url.select("views").text() + "','" + url.select("data").text() + "','" + url.select("diretorio").text() + "','" + url.select("destaque").text() + "','" + url.select("url").text() + "','" + url.select("prefixo").text() + "','" + url.select("sufixo").text() + "','" + url.select("gostei").text() + "','" + url.select("infor_post").text() + "','" + url.select("gerador_youtube").text() + "','" + url.select("id_usuario").text() + "');");
                Iterator<Element> it2 = url.select("musicas").select("musica").iterator();
                while (it2.hasNext()) {
                    Element musica = it2.next();
                    this.f34db.execSQL("INSERT INTO musicas VALUES('" + musica.select("idMusica").text() + "','" + musica.select("id_cds").text() + "','" + musica.select("nomemusica").text() + "');");
                }
            }
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    public List<ObjectItens> topbimestre() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_top_bimestre.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
            while (it.hasNext()) {
                Element url = it.next();
                this.f34db.execSQL("INSERT INTO topbimestre VALUES('" + url.select("id").text() + "','" + url.select("nome").text() + "','" + url.select("estado").text() + "','" + url.select("cidade").text() + "','" + url.select("showdata").text() + "','" + url.select("cd_cover").text() + "','" + url.select("faixas").text() + "','" + url.select("downs").text() + "','" + url.select("views").text() + "','" + url.select("data").text() + "','" + url.select("diretorio").text() + "','" + url.select("destaque").text() + "','" + url.select("url").text() + "','" + url.select("prefixo").text() + "','" + url.select("sufixo").text() + "','" + url.select("gostei").text() + "','" + url.select("infor_post").text() + "','" + url.select("gerador_youtube").text() + "','" + url.select("id_usuario").text() + "');");
                Iterator<Element> it2 = url.select("musicas").select("musica").iterator();
                while (it2.hasNext()) {
                    Element musica = it2.next();
                    this.f34db.execSQL("INSERT INTO musicas VALUES('" + musica.select("idMusica").text() + "','" + musica.select("id_cds").text() + "','" + musica.select("nomemusica").text() + "');");
                }
            }
        } catch (Exception e) {
        }
        return retorno;
    }

    /* access modifiers changed from: package-private */
    public List<ObjectItens> topmes() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_top_mes.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
            while (it.hasNext()) {
                Element url = it.next();
                this.f34db.execSQL("INSERT INTO topmes VALUES('" + url.select("id").text() + "','" + url.select("nome").text() + "','" + url.select("estado").text() + "','" + url.select("cidade").text() + "','" + url.select("showdata").text() + "','" + url.select("cd_cover").text() + "','" + url.select("faixas").text() + "','" + url.select("downs").text() + "','" + url.select("views").text() + "','" + url.select("data").text() + "','" + url.select("diretorio").text() + "','" + url.select("destaque").text() + "','" + url.select("url").text() + "','" + url.select("prefixo").text() + "','" + url.select("sufixo").text() + "','" + url.select("gostei").text() + "','" + url.select("infor_post").text() + "','" + url.select("gerador_youtube").text() + "','" + url.select("id_usuario").text() + "');");
                Iterator<Element> it2 = url.select("musicas").select("musica").iterator();
                while (it2.hasNext()) {
                    Element musica = it2.next();
                    this.f34db.execSQL("INSERT INTO musicas VALUES('" + musica.select("idMusica").text() + "','" + musica.select("id_cds").text() + "','" + musica.select("nomemusica").text() + "');");
                }
            }
        } catch (Exception e) {
        }
        return retorno;
    }

    /* access modifiers changed from: package-private */
    public List<ObjectItens> topsemana() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_top_semana.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
            while (it.hasNext()) {
                Element url = it.next();
                this.f34db.execSQL("INSERT INTO topsemana VALUES('" + url.select("id").text() + "','" + url.select("nome").text() + "','" + url.select("estado").text() + "','" + url.select("cidade").text() + "','" + url.select("showdata").text() + "','" + url.select("cd_cover").text() + "','" + url.select("faixas").text() + "','" + url.select("downs").text() + "','" + url.select("views").text() + "','" + url.select("data").text() + "','" + url.select("diretorio").text() + "','" + url.select("destaque").text() + "','" + url.select("url").text() + "','" + url.select("prefixo").text() + "','" + url.select("sufixo").text() + "','" + url.select("gostei").text() + "','" + url.select("infor_post").text() + "','" + url.select("gerador_youtube").text() + "','" + url.select("id_usuario").text() + "');");
                Iterator<Element> it2 = url.select("musicas").select("musica").iterator();
                while (it2.hasNext()) {
                    Element musica = it2.next();
                    this.f34db.execSQL("INSERT INTO musicas VALUES('" + musica.select("idMusica").text() + "','" + musica.select("id_cds").text() + "','" + musica.select("nomemusica").text() + "');");
                }
            }
        } catch (Exception e) {
        }
        return retorno;
    }

    /* access modifiers changed from: package-private */
    public List<ObjectItens> top48h() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_top_48h.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
            while (it.hasNext()) {
                Element url = it.next();
                this.f34db.execSQL("INSERT INTO top48h VALUES('" + url.select("id").text() + "','" + url.select("nome").text() + "','" + url.select("estado").text() + "','" + url.select("cidade").text() + "','" + url.select("showdata").text() + "','" + url.select("cd_cover").text() + "','" + url.select("faixas").text() + "','" + url.select("downs").text() + "','" + url.select("views").text() + "','" + url.select("data").text() + "','" + url.select("diretorio").text() + "','" + url.select("destaque").text() + "','" + url.select("url").text() + "','" + url.select("prefixo").text() + "','" + url.select("sufixo").text() + "','" + url.select("gostei").text() + "','" + url.select("infor_post").text() + "','" + url.select("gerador_youtube").text() + "','" + url.select("id_usuario").text() + "');");
                Iterator<Element> it2 = url.select("musicas").select("musica").iterator();
                while (it2.hasNext()) {
                    Element musica = it2.next();
                    this.f34db.execSQL("INSERT INTO musicas VALUES('" + musica.select("idMusica").text() + "','" + musica.select("id_cds").text() + "','" + musica.select("nomemusica").text() + "');");
                }
            }
        } catch (Exception e) {
        }
        return retorno;
    }

    /* access modifiers changed from: package-private */
    public List<ObjectItens> maisrecentes() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_mais_recentes.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
            while (it.hasNext()) {
                Element url = it.next();
                this.f34db.execSQL("INSERT INTO maisrecentes VALUES('" + url.select("id").text() + "','" + url.select("nome").text() + "','" + url.select("estado").text() + "','" + url.select("cidade").text() + "','" + url.select("showdata").text() + "','" + url.select("cd_cover").text() + "','" + url.select("faixas").text() + "','" + url.select("downs").text() + "','" + url.select("views").text() + "','" + url.select("data").text() + "','" + url.select("diretorio").text() + "','" + url.select("destaque").text() + "','" + url.select("url").text() + "','" + url.select("prefixo").text() + "','" + url.select("sufixo").text() + "','" + url.select("gostei").text() + "','" + url.select("infor_post").text() + "','" + url.select("gerador_youtube").text() + "','" + url.select("id_usuario").text() + "');");
                Iterator<Element> it2 = url.select("musicas").select("musica").iterator();
                while (it2.hasNext()) {
                    Element musica = it2.next();
                    this.f34db.execSQL("INSERT INTO musicas VALUES('" + musica.select("idMusica").text() + "','" + musica.select("id_cds").text() + "','" + musica.select("nomemusica").text() + "');");
                }
            }
        } catch (Exception e) {
        }
        return retorno;
    }

    /* access modifiers changed from: package-private */
    public String pega_html_(String endereco) {
        String HTML = " ";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL(endereco).openStream()));
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    break;
                }
                HTML = HTML + " " + str;
            }
            in.close();
        } catch (IOException | MalformedURLException e) {
        }
        if (HTML == " ") {
            pega_html_(endereco);
        }
        return HTML;
    }

    /* access modifiers changed from: package-private */
    public List<ObjectItens> buscar(String query) {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_busca.php?q=" + query.replace(" ", "%20")).timeout(99999).get().select(HitTypes.ITEM).iterator();
            while (it.hasNext()) {
                Element url = it.next();
                ObjectItens item = new ObjectItens();
                item.setNome(url.select("nome").text());
                item.setCd_cover(url.select("cd_cover").text());
                item.setDiretorio(url.select("diretorio").text());
                item.setFaixas(url.select("faixa").text());
                item.setdowns(url.select("downs").text());
                item.setData(url.select("data").text());
                item.setUrl(url.select("url").text());
                item.setPrefixo(url.select("prefixo").text().replace("_", " "));
                item.setSufixo(url.select("sufixo").text().replace("_", " "));
                item.setId(url.select("id").text());
                item.setGostei(url.select("gostei").text());
                item.setViews(url.select("views").text());
                int indice = 0;
                Iterator<Element> it2 = url.select("musicas").iterator();
                while (it2.hasNext()) {
                    item.nomesfaixas.add(indice, it2.next().select("musica").text());
                    indice++;
                }
                retorno.add(item);
            }
        } catch (Exception e) {
        }
        return retorno;
    }

    /* access modifiers changed from: package-private */
    public List<String> musicas(String query) {
        List<String> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_musicas.php?q=" + query).timeout(999999999).get().select(HitTypes.ITEM).iterator();
            while (it.hasNext()) {
                retorno.add(it.next().select("nome").text());
            }
        } catch (Exception e) {
        }
        return retorno;
    }

    /* access modifiers changed from: package-private */
    public void mais_um(String query) {
        try {
            Document document = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/insert_um_down.php?q=" + query).timeout(99999).get();
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    public void mais_voto(String query) {
        try {
            Document document = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/insert_voto.php?q=" + query).timeout(99999).get();
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    public void mais_view(String query) {
        try {
            Document document = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/insert_view.php?q=" + query).timeout(99999).get();
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    public void mais_todos(String query) {
        try {
            Document document = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/insert_todos_down.php?q=" + query).timeout(99999).get();
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    public ObjectItens buscarBandaById(String query) {
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_buscabyid.php?q=" + query.replace(" ", "%20")).timeout(99999).get().select(HitTypes.ITEM).iterator();
            if (it.hasNext()) {
                Element url = it.next();
                ObjectItens item = new ObjectItens();
                item.setNome(url.select("nome").text());
                item.setCd_cover(url.select("cd_cover").text());
                item.setDiretorio(url.select("diretorio").text());
                item.setFaixas(url.select("faixa").text());
                item.setdowns(url.select("downs").text());
                item.setData(url.select("data").text());
                item.setUrl(url.select("url").text());
                item.setPrefixo(url.select("prefixo").text().replace("_", " "));
                item.setSufixo(url.select("sufixo").text().replace("_", " "));
                item.setId(url.select("id").text());
                item.setGostei(url.select("gostei").text());
                item.setViews(url.select("views").text());
                int indice = 0;
                Iterator<Element> it2 = url.select("musicas").iterator();
                while (it2.hasNext()) {
                    item.nomesfaixas.add(indice, it2.next().select("musica").text());
                    indice++;
                }
                return item;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
