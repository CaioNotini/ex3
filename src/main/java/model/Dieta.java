package model;

import java.time.LocalDate;

public class Dieta {
    private int id_dieta;
    private String nome;
    private int id_usuario;
    private int calorias;
    private String tipoAlimentacao;
    private LocalDate data;
    private String nomeUsuario;

    //Construtor
    public Dieta(){

    }

    public Dieta(int id_dieta, String nome, int id_usuario, int calorias, String tipoAlimentacao, LocalDate data){
        this.id_dieta = id_dieta;
        this.nome= nome;
        this.id_usuario = id_usuario;
        this.calorias = calorias;
        this.tipoAlimentacao = tipoAlimentacao;
        this.data = data;
    }

     public Dieta(String nome, int id_usuario, int calorias, String tipoAlimentacao, LocalDate data){
        this.nome= nome;
        this.id_usuario = id_usuario;
        this.calorias = calorias;
        this.tipoAlimentacao = tipoAlimentacao;
        this.data = data;
    }

    // Getters
    public int getID_Dieta() {
        return id_dieta;
    }

    public String getNome() {
        return nome;
    }

    public int getID_Usuario() {
        return id_usuario;
    }

    public int getCalorias() {
        return calorias;
    }

    public String getTipoAlimentacao() {
        return tipoAlimentacao;
    }

    public LocalDate getData() {
        return data;
    }

    // Setters
    public void setID_Dieta(int ID_Dieta) {
        this.id_dieta = ID_Dieta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.id_usuario = ID_Usuario;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public void setTipoAlimentacao(String tipoAlimentacao) {
        this.tipoAlimentacao = tipoAlimentacao;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomeUsuario(){
       return nomeUsuario; 
    }

    public void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
    }
    
}
