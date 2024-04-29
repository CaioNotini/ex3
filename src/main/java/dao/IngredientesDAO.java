package dao;

import java.sql.*;
import model.User;
import model.Perfil;
import model.Calorias;
import model.Ingredientes;

public class IngredientesDAO extends DAO{
    public IngredientesDAO(){
        super();
        conectar();
    }

    public boolean insert(Ingredientes ingredientes){
        boolean status = false;

        try{
            String sql = "INSERT INTO ingrediente (id_ingrediente,nome,calorias,proteinas,gorduras,carboidratos) VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, ingredientes.getIdIngredientes());
            preparedStatement.setString(2, ingredientes.getNome());
            preparedStatement.setFloat(3, ingredientes.getCalorias());;
            preparedStatement.setFloat(4, ingredientes.getProteinas());
            preparedStatement.setFloat(5, ingredientes.getGordura());
            preparedStatement.setFloat(6, ingredientes.getCarboidratos());

            preparedStatement.executeUpdate();
			preparedStatement.close();

            status = true;
        }  catch (SQLException e) {
			System.err.println(e);
		}
        return status;
    }
}

