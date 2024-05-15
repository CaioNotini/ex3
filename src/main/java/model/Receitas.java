package model;

public class Receitas {
    private int id_receita;
    private String nome;
    private String codigoVideo;
    private String descricao;
    private int tempo;
    private String tipoReceita; 
    private String horario;
    private float totalCalorias;
    private int id_usuario;

    public Receitas() {
    }

    public Receitas(int id_receita, String nome, String codigoVideo, String descricao, int tempo, String tipoReceita, String horario, float totalCalorias, int id_usuario) {
        this.id_receita = id_receita;
        this.nome = nome;
        this.codigoVideo = codigoVideo;
        this.descricao = descricao;
        this.tempo = tempo;
        this.tipoReceita = tipoReceita;
        this.horario = horario;
        this.totalCalorias = totalCalorias;
        this.id_usuario = id_usuario;
    }

    public int getId_receita() {
        return id_receita;
    }

    public void setId_receita(int id_receita) {
        this.id_receita = id_receita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoVideo() {
        return codigoVideo;
    }

    public void setCodigoVideo(String codigoVideo) {
        this.codigoVideo = codigoVideo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(String tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public float getTotalCalorias(){
        return totalCalorias;
    }

    public void setTotalCalorias(float totalCalorias){
        this.totalCalorias = totalCalorias;
    }

    public int getId_usuario(){
        return id_usuario;
    }

    public void setId_usuario(int id_usuario){
        this.id_usuario = id_usuario;
    }
}
