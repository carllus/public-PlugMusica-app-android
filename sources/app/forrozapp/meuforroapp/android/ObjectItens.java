package app.forrozapp.meuforroapp.android;

import java.util.ArrayList;

public class ObjectItens {
    private String Cd_cover;
    private String data;
    private String diretorio;
    private String downs;
    private String faixas;
    private String gostei;

    /* renamed from: id */
    private String f30id;
    private String imagem;
    private String nome;
    public ArrayList<String> nomesfaixas = new ArrayList<>();
    private String prefixo;
    private String sufixo;
    private String url;
    private String views;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome2) {
        this.nome = nome2;
    }

    public String getImagem() {
        return this.imagem;
    }

    public void setImagem(String imagem2) {
        this.imagem = imagem2;
    }

    public String getdowns() {
        return this.downs;
    }

    public void setdowns(String downs2) {
        this.downs = downs2;
    }

    public String getDiretorio() {
        return this.diretorio;
    }

    public void setDiretorio(String diretorio2) {
        this.diretorio = diretorio2;
    }

    public String getFaixas() {
        return this.faixas;
    }

    public void setFaixas(String faixas2) {
        this.faixas = faixas2;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data2) {
        this.data = data2;
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
        return this.f30id;
    }

    public void setId(String id) {
        this.f30id = id;
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
