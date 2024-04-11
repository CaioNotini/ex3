package model;

public class Perfil {
    private int idUsuario;
    private int idade;
    private String sexo;
    private int altura;
    private int nivelAtividade;
    private int peso;

    public Perfil(int idUsuario, int idade, String sexo, int altura, int peso, int nivelAtividade) {
        this.idUsuario = idUsuario;
        this.idade = idade;
        this.sexo = sexo;
        this.peso=peso;
        this.altura = altura;
        this.nivelAtividade = nivelAtividade;
    }

    public int getid_usuario(){
        return idUsuario;
    }

    public void setid_usuario(int idUsuario){
        this.idUsuario= idUsuario;
    }

    public int getPeso(){
        return peso;
    }

    public void setPeso(int peso){
        this.peso=peso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getNivelAtividade() {
        return nivelAtividade;
    }

    public void setNivelAtividade(int nivelAtividade) {
        this.nivelAtividade = nivelAtividade;
    }
}
