package app.forrozapp.meuforroapp.android;

import android.database.sqlite.SQLiteDatabase;
import com.google.analytics.tracking.android.HitTypes;
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

public class ClassJSoup {

    /* renamed from: db */
    SQLiteDatabase f26db;

    /* access modifiers changed from: package-private */
    public List<ObjectItens> destaques() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_destaque.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
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
    public List<ObjectItens> topbimestre() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_top_bimestre.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
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
    public List<ObjectItens> topmes() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_top_mes.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
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
    public List<ObjectItens> topsemana() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_top_semana.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
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
    public List<ObjectItens> top48h() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_top_48h.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
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
    public List<ObjectItens> maisrecentes() {
        List<ObjectItens> retorno = new ArrayList<>();
        try {
            Iterator<Element> it = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/consulta_mais_recentes.php").timeout(99999).get().select(HitTypes.ITEM).iterator();
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
                item.setFaixas(url.select("faixas").text());
                item.setdowns(url.select("downs").text());
                item.setData(url.select("data").text());
                item.setUrl(url.select("url").text());
                item.setPrefixo(url.select("prefixo").text().replace("_", " "));
                item.setSufixo(url.select("sufixo").text().replace("_", " "));
                item.setId(url.select("id").text());
                item.setGostei(url.select("gostei").text());
                item.setViews(url.select("views").text());
                Iterator<Element> it2 = url.select("musicas").iterator();
                while (it2.hasNext()) {
                    item.nomesfaixas.add(it2.next().select("musica").text());
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
    public void mais_um(String nomemusica, String idcd) {
        try {
            Document document = Jsoup.connect("https://plugmusica.com/01-app-plugmusica/com.plugmusica.application/insert_um_down.php?nomemusica=" + nomemusica + "&idcd=" + idcd).timeout(99999).get();
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
                item.setFaixas(url.select("faixas").text());
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
