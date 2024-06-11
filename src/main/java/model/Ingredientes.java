package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ingredientes {
    private int idIngredientes;
    private String nome;
    private float calorias;
    private float proteinas;
    private float gordura;
    private float carboidratos;

    public Ingredientes(){

    }

        public Ingredientes(String nome, float calorias, float proteinas, float gordura, float carboidratos) {
        this.nome = nome;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.gordura = gordura;
        this.carboidratos = carboidratos;
    }

    public Ingredientes(int idIngredientes,String nome, float calorias, int gordura, int carboidratos){
        this.idIngredientes = idIngredientes;
        this.nome = nome;
        this.calorias = calorias;
        this.gordura = gordura;
        this.carboidratos = carboidratos;
    }

    public String toString() {
    return 
            idIngredientes + " " +
             nome + " " +
           " " + calorias +
           " " + proteinas +
           " " + gordura +
           " " + carboidratos ;
}


    public int getIdIngredientes(){
        return idIngredientes;
    }

    public void setIdIngredientes(int idIngredientes){
        this.idIngredientes = idIngredientes;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

      public float getCalorias(){
        return calorias;
    }

    public void setCalorias(float calorias){
        this.calorias=calorias;
    }

    
      public float getProteinas(){
        return proteinas;
    }

    public void setProteinas(float proteinas){
        this.proteinas = proteinas;
    }

        public float getGordura(){
        return gordura;
    }

    public void setGordura(float gordura){
        this.gordura = gordura;
    }

        public float getCarboidratos(){
        return carboidratos;
    }

    public void setCarboidratos(float carboidratos){
        this.carboidratos=carboidratos;
    }

    
	public void Ler(String linha){
    int j = 0;
    char c = ';';
    int tmp = 0;
    String vetorStr[] = new String[5];
    for(int i=0; i<linha.length(); i++){
        if(linha.charAt(i) == c) {
            vetorStr[j] =linha.substring(tmp, i);
            tmp = i+1;
            j++;
        }
    }
    vetorStr[4] =linha.substring(tmp, linha.length());

    if (vetorStr[0] != null && vetorStr[1] != null && vetorStr[2] != null && vetorStr[3] != null && vetorStr[4] != null) {
        setNome(vetorStr[0]);
        setCalorias(Float.parseFloat(vetorStr[1]));
        setProteinas(Float.parseFloat(vetorStr[2]));
        setGordura(Float.parseFloat(vetorStr[3]));
        setCarboidratos(Float.parseFloat(vetorStr[4]));
    } else {
        System.out.println("Erro: linha incompleta");
    }
}


    public static ArrayList<Ingredientes> lerArquivo(){
         ArrayList<Ingredientes> Ingredientes = new ArrayList<>();

        try {
            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream("Taco.csv"), "UTF-8"));

            arq.readLine();

            while (arq.ready()) {
                Ingredientes ingrediente = new Ingredientes();
                ingrediente.Ler(arq.readLine());
                Ingredientes.add(ingrediente);
            }
            arq.close();

        } catch (Exception e) {
            System.out.println("Erro para abrir o arquivo");
            e.printStackTrace();
        }

        return Ingredientes;
    }
    
/* 
        public static void main(String args[]){
                 IngredientesDAO dao = new IngredientesDAO();

        ArrayList<Ingredientes> ingredientes = lerArquivo();
        for(Ingredientes ingrediente : ingredientes){
            dao.insert(ingrediente);
        }
    }
*/

}


