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
    

}

