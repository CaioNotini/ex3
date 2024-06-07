package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class DietaDAO extends DAO{
      public DietaDAO() {
		super();
		conectar();
	}

    public boolean insert(Dieta dieta){
        boolean status = false;

        try{
            String sql = "INSERT INTO dieta (nome, id_usuario,calorias,tipoalimentacao,data) VALUES (?,?,?,?,?)";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, dieta.getNome());
            ps.setInt(2, dieta.getID_Usuario());
            ps.setInt(3, dieta.getCalorias());
            ps.setString(4,dieta.getTipoAlimentacao());
            ps.setDate(5, Date.valueOf(dieta.getData()));

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

    
public boolean delete(int id) {
    boolean status = false;

    try {
        String sql = "DELETE FROM dieta WHERE id_dieta = ?";

        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);

        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            status = true;
        }

        ps.close();
    } catch (SQLException e) {
        System.err.println("Erro ao deletar dieta: " + e.getMessage());
    }

    return status;
}

public int getId(String nome, int id){
    String sql = "SELECT id_dieta FROM dieta WHERE nome = ? AND id_usuario = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, nome);
                ps.setInt(2, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id_dieta");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return 0; 
}

public Dieta getDieta(int id){
    Dieta dietas = null;

    try{
        String sql = "SELECT * FROM dieta WHERE id_dieta = ?";

        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            dietas = new Dieta();
            dietas.setID_Dieta(rs.getInt("id_dieta"));
            dietas.setNome(rs.getString("nome"));
            dietas.setID_Usuario(rs.getInt("id_usuario"));
            dietas.setCalorias(rs.getInt("calorias"));
            dietas.setTipoAlimentacao(rs.getString("tipoalimentacao"));
            dietas.setData(rs.getDate("data").toLocalDate());
        }

    } catch(SQLException e){
        e.printStackTrace();
    }

    System.out.println(dietas.getTipoAlimentacao());
    return dietas;
}

public List<Dieta> getDietas(int id){
    List<Dieta> dietas = new ArrayList<>();
    Dieta dieta = null;

    try{
        String sql = "SELECT * FROM dieta WHERE id_usuario = ?";

        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            dieta = new Dieta();
            dieta.setID_Dieta(rs.getInt("id_dieta"));
            dieta.setNome(rs.getString("nome"));
            dieta.setID_Usuario(rs.getInt("id_usuario"));
            dieta.setCalorias(rs.getInt("calorias"));
            dieta.setTipoAlimentacao(rs.getString("tipoalimentacao"));
            dieta.setData(rs.getDate("data").toLocalDate());
            dietas.add(dieta);
        }

    } catch(SQLException e){
        e.printStackTrace();
    }

    System.out.println(dieta.getTipoAlimentacao());
    return dietas;
}


}
