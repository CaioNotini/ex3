package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import dao.*;

public class Ingredientes {
    private int idIngredientes;
    private String nome;
    private float calorias;
    private float proteinas;
    private float gordura;
    private float carboidratos;

    public Ingredientes(){

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

      public Float getCalorias(){
        return calorias;
    }

    public void setCalorias(Float calorias){
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
		String vetorStr[] = new String[6];
		for(int i=0; i<linha.length(); i++){
			if(linha.charAt(i) == c) {
				vetorStr[j] =linha.substring(tmp, i);
				tmp = i+1;
				j++;
			}
		}
		vetorStr[5] =linha.substring(tmp, linha.length());

		setIdIngredientes(Integer.parseInt(vetorStr[0]));
		setNome(vetorStr[1]);
		setCalorias(Float.parseFloat(vetorStr[2]));
		setProteinas(Float.parseFloat(vetorStr[3]));
        setGordura(Float.parseFloat(vetorStr[4]));
		setCarboidratos(Float.parseFloat(vetorStr[5]));
		
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

    public static void main(String args[]){
                 IngredientesDAO dao = new IngredientesDAO();

        ArrayList<Ingredientes> ingredientes = lerArquivo();
        for(Ingredientes ingrediente : ingredientes){
            dao.insert(ingrediente);
        }
    }
}

