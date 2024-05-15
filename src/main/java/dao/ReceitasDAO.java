package dao;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import org.eclipse.jetty.http.MetaData.Request;
import org.eclipse.jetty.http.MetaData.Response;

import model.*;

public class ReceitasDAO extends DAO {
    public ReceitasDAO() {
		super();
		conectar();
	}


public boolean insert(Receitas receita){
    boolean status = false;

    try {
        String sql = "INSERT INTO receita (nome, codigoVideo, descricao, tempo, tipo, horario, totalCalorias, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setString(1, receita.getNome());
        ps.setString(2, receita.getCodigoVideo());
        ps.setString(3, receita.getDescricao());
        ps.setInt(4, receita.getTempo());
        ps.setString(5, receita.getTipoReceita());
        ps.setString(6, receita.getHorario());
        ps.setFloat(7, receita.getTotalCalorias());
        ps.setInt(8, receita.getId_usuario());


        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            status = true;
        }

        ps.close();
    } catch (SQLException e) {
        System.err.println("Erro ao inserir receita: " + e.getMessage());
    }

    return status;

}

public boolean deleteReceita(int id) {
    boolean status = false;

    try {
        String sql = "DELETE FROM receita WHERE id_receita = ?";

        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);

        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            status = true;
        }

        ps.close();
    } catch (SQLException e) {
        System.err.println("Erro ao deletar receita: " + e.getMessage());
    }

    return status;
}



public List<Receitas> getReceita(){
    List<Receitas> receitas = new ArrayList<>();
    

    try{
        String sql = "SELECT * FROM receita";

        PreparedStatement ps = conexao.prepareStatement(sql);


        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Receitas receita = new Receitas();
            receita.setId_receita(rs.getInt("id_receita"));
            receita.setNome(rs.getString("nome"));
            receita.setCodigoVideo(rs.getString("codigoVideo"));
            receita.setDescricao(rs.getString("descricao"));
            receita.setTempo(rs.getInt("tempo"));
            receita.setTipoReceita(rs.getString("tipo"));
            receita.setHorario(rs.getString("horario"));
            receita.setTotalCalorias(rs.getFloat("totalcalorias"));
            receita.setId_usuario(rs.getInt("id_usuario"));

            receitas.add(receita);
        }
    } catch (SQLException e) {
			System.err.println(e);
		}

    return receitas;
}

public Receitas getReceita(int id){
    Receitas receita = null;

    try{
        String sql = "SELECT * FROM receita WHERE id_receita = ?";

        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            receita = new Receitas();
            receita.setId_receita(rs.getInt("id_receita"));
            receita.setNome(rs.getString("nome"));
            receita.setCodigoVideo(rs.getString("codigoVideo"));
            receita.setDescricao(rs.getString("descricao"));
            receita.setTempo(rs.getInt("tempo"));
            receita.setTipoReceita(rs.getString("tipo"));
            receita.setHorario(rs.getString("horario"));
            receita.setTotalCalorias(rs.getFloat("totalcalorias"));
            receita.setId_usuario(rs.getInt("id_usuario"));
        }
    } catch (SQLException e) {
			System.err.println(e);
		}

    return receita;
}
}

