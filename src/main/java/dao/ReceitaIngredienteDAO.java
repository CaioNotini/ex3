package dao;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import org.eclipse.jetty.http.MetaData.Request;
import org.eclipse.jetty.http.MetaData.Response;

import model.*;
public class ReceitaIngredienteDAO extends DAO{
    public ReceitaIngredienteDAO(){
        super();
        conectar();
    }

        public boolean insert (ReceitaIngrediente receitaIngrediente) {
            boolean status = false;


        try {

            String sql = "INSERT INTO receitaingrediente (id_receita, id_ingrediente, quantidadereceita, porcao) VALUES (?, ?, ?, ?)";


            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, receitaIngrediente.getId_receita());
            ps.setInt(2, receitaIngrediente.getidIngredientes());
            ps.setFloat(3, receitaIngrediente.getQuantidade());
            ps.setString(4, receitaIngrediente.getObservacao());

            
            ps.executeUpdate();
            ps.close();

            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
}
