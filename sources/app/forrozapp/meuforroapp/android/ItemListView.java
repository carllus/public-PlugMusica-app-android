package app.forrozapp.meuforroapp.android;

public class ItemListView {
    private String Cd_cover;
    private String Data;
    private String Diretorio;
    private String Downs;
    private String faixas;
    private String gostei;
    private String iconeRid;

    /* renamed from: id */
    private String f27id;
    private String prefixo;
    private String sufixo;
    private String texto;
    private String url;
    private String views;

    public ItemListView() {
    }

    public ItemListView(String texto2, String diretorio, String faixas2, String iconeRid2, String Downs2, String data, String Cd_cover2, String url2, String prefixo2, String sufixo2, String id, String gostei2, String views2) {
        this.texto = texto2;
        this.Diretorio = diretorio;
        this.iconeRid = iconeRid2;
        this.Downs = Downs2;
        this.faixas = faixas2;
        this.Data = data;
        this.Cd_cover = Cd_cover2;
        this.url = url2;
        this.prefixo = prefixo2;
        this.sufixo = sufixo2;
        this.f27id = id;
        this.gostei = gostei2;
        this.views = views2;
    }

    public String getIconeRid() {
        return this.iconeRid;
    }

    public void setIconeRid(String iconeRid2) {
        this.iconeRid = iconeRid2;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto2) {
        this.texto = texto2;
    }

    public String getDowns() {
        return this.Downs;
    }

    public void setDowns(String Downs2) {
        this.Downs = Downs2;
    }

    public String getFaixas() {
        return this.faixas;
    }

    public void setFaixas(String faixas2) {
        this.faixas = faixas2;
    }

    public String getData() {
        return this.Data;
    }

    public void setData(String data) {
        this.Data = data;
    }

    public String getDiretorio() {
        return this.Diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.Diretorio = diretorio;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url2) {
        this.url = url2;
    }

    public String getPrefixo() {
        return this.prefixo;
    }

    public void setPrefixo(String prefixo2) {
        this.prefixo = prefixo2;
    }

    public String getSufixo() {
        return this.sufixo;
    }

    public void setSufixo(String sufixo2) {
        this.sufixo = sufixo2;
    }

    public String getId() {
        return this.f27id;
    }

    public void setId(String id) {
        this.f27id = id;
    }

    public String getCd_cover() {
        if (this.Cd_cover == "" || this.Cd_cover == null) {
            return "http://meuforroapp.com/cd_cover.jpg";
        }
        return this.Cd_cover;
    }

    public void setCd_cover(String cd_cover) {
        this.Cd_cover = cd_cover;
    }

    public String getGostei() {
        return this.gostei;
    }

    public void setGostei(String gostei2) {
        this.gostei = gostei2;
    }

    public String getViews() {
        return this.views;
    }

    public void setViews(String views2) {
        this.views = views2;
    }
}
