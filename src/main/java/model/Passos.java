package model;

public class Passos {
    private int id_receita;
    private int numero;
    private String descricaoPasso;

    public Passos(){
    }

    public Passos(int id_receita, int numero, String descricaoPasso){
        this.id_receita = id_receita;
        this.numero = numero;
        this.descricaoPasso = descricaoPasso;
    }

    public int getId_receita(){
        return id_receita;
    }

    public void setId_receita(int id_receita){ 
        this.id_receita = id_receita;
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public String getDescricaoPasso(){
        return descricaoPasso;
    }

    public void setDescricaoPasso(String descricaoPasso){
        this.descricaoPasso = descricaoPasso;
    }

}
