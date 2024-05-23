package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Passos> getPassos(int id){
        List<Passos> passoApasso = new ArrayList<>();

        try{
            String sql = "SELECT * FROM passos WHERE id_receita = ?";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Passos passos = new Passos();
                passos.setNumero(rs.getInt("numero_passo"));
                passos.setDescricaoPasso(rs.getString("descricao_passo"));
                passoApasso.add(passos);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return passoApasso;
    }


}
