package model;

public class ReceitaIngrediente {
    private int id_receita;
    private int idIngredientes;
    private float quantidade;
    private String observacao;

    public ReceitaIngrediente() {
    }

    public ReceitaIngrediente(int id_receita, int idIngredientes, float quantidade, String observacao) {
        this.id_receita = id_receita;
        this.idIngredientes = idIngredientes;
        this.quantidade = quantidade;
        this.observacao = observacao;
    }

    public int getId_receita() {
        return id_receita;
    }

    public void setId_receita(int id_receita) {
        this.id_receita = id_receita;
    }

    public int getidIngredientes() {
        return idIngredientes;
    }

    public void setidIngredientes(int idIngredientes) {
        this.idIngredientes = idIngredientes;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}

