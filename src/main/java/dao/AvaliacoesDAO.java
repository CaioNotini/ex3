package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


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

}
