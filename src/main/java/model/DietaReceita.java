package model;

public class DietaReceita {
    private int id_dieta;
    private int id_receita;

    public DietaReceita() {
    }

    public DietaReceita(int id_dieta, int id_receita) {
        this.id_dieta = id_dieta;
        this.id_receita = id_receita;
    }

    public int getId_dieta() {
        return id_dieta;
    }

    public void setId_dieta(int id_dieta) {
        this.id_dieta = id_dieta;
    }

    public int getId_receita() {
        return id_receita;
    }

    public void setId_receita(int id_receita) {
        this.id_receita = id_receita;
    }
}
