package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Passos;

public class PassosDAO extends DAO {
    public PassosDAO(){
        super();
        conectar();
    }

    public boolean insert(Passos passos){
        boolean status = false;

        try{
            String sql = "INSERT INTO passos (id_receita, numero_passo, descricao_passo) VALUES (?,?,?)";
        
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, passos.getId_receita());
            ps.setInt(2, passos.getNumero());
            ps.setString(3, passos.getDescricaoPasso());

                int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            status = true;
        }

        ps.close();

        } catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
}
