package model;

import java.time.LocalDate;


public class VideoCurto {
    private int id_video;
    private String plataforma;
    private String codigo;
    private String autor;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private int id_usuario;
    private String url;

    public VideoCurto() {
    }

    public VideoCurto(int id_video, String plataforma, String codigo, String autor, String titulo, String descricao, LocalDate data, int id_usuario) {
        this.id_video = id_video;
        this.plataforma = plataforma;
        this.codigo = codigo;
        this.autor = autor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.id_usuario = id_usuario;
    }

       public VideoCurto( String plataforma, String codigo, String autor, String titulo, String descricao, LocalDate data, int id_usuario) {
        this.plataforma = plataforma;
        this.codigo = codigo;
        this.autor = autor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.id_usuario = id_usuario;
    }

    public int getId_video() {
        return id_video;
    }

    public void setId_video(int id_video) {
        this.id_video = id_video;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}


