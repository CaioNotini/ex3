package model;

public class Avaliacoes {
    private int id_avaliacao;
    private int id_usuario;
    private int id_receita;
    private int nota;
    public String comentario;

    public Avaliacoes(){

    }

    public Avaliacoes(int id_avaliacao, int id_usuario, int id_receita, int nota, String comentario){
        this.id_avaliacao = id_avaliacao;
        this.id_usuario = id_usuario;
        this.id_receita = id_receita;
        this.nota = nota;
        this.comentario = comentario;
    }

     public Avaliacoes( int id_usuario, int id_receita, int nota, String comentario){
        this.id_usuario = id_usuario;
        this.id_receita = id_receita;
        this.nota = nota;
        this.comentario = comentario;
    }

    public int getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(int id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_receita() {
        return id_receita;
    }

    public void setId_receita(int id_receita) {
        this.id_receita = id_receita;
    }
    
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario(){
        return comentario;
    }

    public void setComentario(String comentario){
        this.comentario = comentario;
    }



    
}
