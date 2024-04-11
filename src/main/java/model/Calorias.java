package model;

public class Calorias {
    private int id_usuario;
    private int calorias;

    public Calorias(int idUsuario, int calorias){
        this.id_usuario = idUsuario;
        this.calorias = calorias;
    }

    public int getid_usuario(){
        return id_usuario;
    }

    public void setid_suario(int idUsuario){
        this.id_usuario= idUsuario;
    }

    public int getCalorias(){
        return calorias;
    }

    public void setCalorias(int calorias){
        this.calorias = calorias;
    }
}
