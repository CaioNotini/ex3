package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import model.User;
import model.Perfil;
import model.Calorias;
import model.Ingredientes;

public class IngredientesDAO extends DAO{
    public IngredientesDAO(){
        super();
        conectar();
    }

    public boolean insert(Ingredientes ingredientes) {
    boolean status = false;

    try {
        String sql = "INSERT INTO ingrediente (nome, calorias, proteinas, gorduras, carboidratos) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);

        preparedStatement.setString(1, ingredientes.getNome());
        preparedStatement.setFloat(2, ingredientes.getCalorias());
        preparedStatement.setFloat(3, ingredientes.getProteinas());
        preparedStatement.setFloat(4, ingredientes.getGordura());
        preparedStatement.setFloat(5, ingredientes.getCarboidratos());

        preparedStatement.executeUpdate();
        preparedStatement.close();

        status = true;
    } catch (SQLException e) {
        System.err.println("Erro ao inserir ingrediente: " + e.getMessage());
    }
    return status;
}

public List<Ingredientes> getIngrediente(){
    List<Ingredientes> ingredientes = new ArrayList<>();

    try{
        String sql = "SELECT * FROM ingrediente"; //trocar o SELECT *
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Ingredientes ingrediente = new Ingredientes();
            ingrediente.setIdIngredientes(rs.getInt("id_ingrediente"));
            ingrediente.setNome(rs.getString("nome"));
            ingrediente.setCalorias(rs.getFloat("calorias"));
            ingrediente.setProteinas(rs.getFloat("proteinas"));
            ingrediente.setGordura(rs.getFloat("gorduras"));
            ingrediente.setCarboidratos(rs.getFloat("carboidratos"));

            ingredientes.add(ingrediente);
        }

    } catch (SQLException e) {
        System.err.println(e);
    }

    return ingredientes;
}

public float getCalorias(String nome){
            float calorias = 0;

            try{
                String sql = "SELECT calorias FROM ingrediente WHERE nome = ?";

                PreparedStatement ps = conexao.prepareStatement(sql);

                ps.setString(1, nome);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
            calorias = rs.getFloat("calorias");

                }
            } catch (SQLException e){
                System.err.println(e);
            }

            return calorias;
        }

    public int getId(String nome){
        String sql = "SELECT id_ingrediente FROM ingrediente WHERE nome = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, nome);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id_ingrediente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return 0; 
    }

        public String getNome(int id){
        String sql = "SELECT nome FROM ingrediente WHERE id_ingrediente = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("nome");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null; 
    }
    

}

