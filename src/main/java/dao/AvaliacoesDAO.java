package dao;

import java.sql.*;
import java.util.*;
import model.*;

public class AvaliacoesDAO extends DAO {
    public AvaliacoesDAO(){
        super();
        conectar();
    }


    public boolean insert(Avaliacoes avaliacoes){
        boolean status = false;

        try{
            String sql = "INSERT INTO avaliacao (id_usuario, id_receita, nota, descricao) VALUES (?,?,?,?)";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, avaliacoes.getId_usuario());
            ps.setInt(2, avaliacoes.getId_receita());
            ps.setInt(3, avaliacoes.getNota());
            ps.setString(4, avaliacoes.getComentario());

            ps.executeUpdate();
            ps.close();

            status = true;
        } catch (SQLException e){
            System.err.println(e);
        }

        return status;
    }

    public boolean update(Avaliacoes avaliacoes){
        boolean status = false;

        try{
            String sql = "UPDATE avaliacao SET nota = ?, descricao = ? WHERE id_usuario = ? AND id_receita = ?";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, avaliacoes.getNota());
            ps.setString(2, avaliacoes.getComentario());
            ps.setInt(3, avaliacoes.getId_usuario());
            ps.setInt(4, avaliacoes.getId_receita());


            int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				status = true;
			}
			ps.close();
        } catch (SQLException e){
            System.err.println(e);
        }

        return status;
    }


    public boolean check(int id_usuario, int id_receita) {
    String sql = "SELECT COUNT(1) FROM avaliacao WHERE id_usuario = ? AND id_receita = ?";

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, id_usuario);
        stmt.setInt(2, id_receita);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                return true; 
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao verificar a existência da avaliação: " + e.getMessage());
    }

    return false;
}

public Map<Integer, Double> media(){
    Map<Integer , Double> media = new HashMap<>();

    String sql = "SELECT id_receita, AVG(nota) as media_avaliacao FROM avaliacao GROUP BY id_receita";

    try{
    PreparedStatement ps = conexao.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        int id = rs.getInt("id_receita");
        double mediaAvaliacao = rs.getDouble("media_avaliacao");
        media.put(id , mediaAvaliacao);
    }
    ps.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return media;
}

public int contador(int id){
    String sql = "SELECT COUNT(*) as total FROM avaliacao WHERE id_receita = ?";
    int total = 0;

    try{
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
            
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            total = rs.getInt("total");
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return total;
}

}
