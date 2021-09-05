package app.forrozapp.meuforroapp.android;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.widget.TextView;
import com.plugmusica.application1.R;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SplashBuscaLinkIdActivity extends AppCompatActivity {
    ObjectItens bandaresult;
    ClassJSoup getbandaid;
    String varid;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash);
        Intent i = new Intent(this, service.class);
        i.putExtra("ServiceForroZappMain", "ForroZappMain");
        startService(i);
        ConnectivityManager manager = (ConnectivityManager) getSystemService("connectivity");
        boolean is3g = manager.getNetworkInfo(0).isConnectedOrConnecting();
        boolean isWifi = manager.getNetworkInfo(1).isConnectedOrConnecting();
        TextView txvaviso = (TextView) findViewById(R.id.txvAviso);
        if (is3g || isWifi) {
            if (is3g) {
                txvaviso.setText("USANDO REDE DE DADOS");
                txvaviso.setTextColor(Color.parseColor("#ffffff"));
            } else {
                txvaviso.setText("CARREGANDO LINK DA BANDA!");
                txvaviso.setTextColor(Color.parseColor("#ffffff"));
            }
            this.varid = getIntent().getData().toString().replace("https://plugmusica.com/", "");
            if (this.varid == null || this.varid == "") {
                redirectsplash();
            } else {
                espera2();
            }
        } else {
            txvaviso.setText("CONECTE A INTERNET\ne tente novamente!");
            txvaviso.setTextColor(Color.parseColor("#ff0000"));
        }
    }

    /* access modifiers changed from: package-private */
    public void espera2() {
        new Thread() {
            public void run() {
                try {
                    ObjectItens bandaresult = new ClassJSoup().buscarBandaById(SplashBuscaLinkIdActivity.this.varid);
                    if (bandaresult == null) {
                        SplashBuscaLinkIdActivity.this.redirectsplash();
                    }
                    ItemListView item = new ItemListView();
                    item.setTexto(bandaresult.getNome());
                    item.setDiretorio(bandaresult.getDiretorio());
                    item.setFaixas(bandaresult.getFaixas());
                    item.setViews(bandaresult.getViews());
                    item.setDowns(bandaresult.getdowns());
                    item.setData(bandaresult.getData());
                    item.setCd_cover(bandaresult.getCd_cover());
                    item.setUrl(bandaresult.getUrl());
                    item.setPrefixo(bandaresult.getPrefixo());
                    item.setSufixo(bandaresult.getSufixo());
                    item.setId(bandaresult.getId());
                    item.setGostei(bandaresult.getGostei());
                    ListaMusicasActivity.Banda = item;
                    try {
                        Elements urls = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_musicas.php?q=" + ListaMusicasActivity.Banda.getId()).timeout(99999).get().select("musica");
                        ListaMusicasActivity.comnome.clear();
                        Iterator<Element> it = urls.iterator();
                        while (it.hasNext()) {
                            ListaMusicasActivity.comnome.add(it.next().select("nomemusica").text());
                        }
                    } catch (Exception e) {
                    }
                    SplashBuscaLinkIdActivity.this.redirectlista(item);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    SplashBuscaLinkIdActivity.this.redirectsplash();
                }
            }
        }.start();
    }

    public void redirectsplash() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    public void redirectlista(ItemListView item) {
        startActivity(new Intent(this, ListaMusicasActivity.class));
        finish();
    }
}
