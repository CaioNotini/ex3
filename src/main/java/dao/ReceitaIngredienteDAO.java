package dao;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;



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

    public List<ReceitaIngrediente> getId(int id){
        List<ReceitaIngrediente> receitaIngredientes = new ArrayList<>();

        try{
            String sql = "SELECT id_ingrediente, quantidadereceita, porcao FROM receitaingrediente WHERE id_receita = ?";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ReceitaIngrediente ri = new ReceitaIngrediente();

                ri.setidIngredientes(rs.getInt("id_ingrediente"));
                ri.setQuantidade(rs.getFloat("quantidadereceita"));
                ri.setObservacao(rs.getString("porcao"));

                receitaIngredientes.add(ri);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return receitaIngredientes;
    }


}
