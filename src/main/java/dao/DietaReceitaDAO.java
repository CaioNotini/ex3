package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class DietaReceitaDAO extends DAO {
    public DietaReceitaDAO(){
        super();
        conectar();
    }

    public boolean insert(DietaReceita dietaReceita){
        boolean status = false;

        try{
            String sql = "INSERT INTO dietareceita (id_dieta, id_receita) VALUES (?,?)";


            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, dietaReceita.getId_dieta());
            ps.setInt(2, dietaReceita.getId_receita());

            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0)
                status = true;

            ps.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return status;
    }

    public boolean delete(int id){
        boolean status = false;

        try{
            String sql = "DELETE FROM dietareceita WHERE id_dieta = ?";

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if(affectedRows>0){
                status = true;
            }

            ps.close();

        } catch(SQLException e){
            e.printStackTrace();
        }

        return status;
    }


    public List<DietaReceita> getId(int id){
        List<DietaReceita> idsReceitas = new ArrayList<>();

        try{
            String sql = "SELECT id_receita FROM dietareceita WHERE id_dieta = ?";

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DietaReceita dr = new DietaReceita();
                dr.setId_receita(rs.getInt("id_receita"));
                idsReceitas.add(dr);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return idsReceitas;
    }
}
