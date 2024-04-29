package dao;


import java.sql.*;

import org.eclipse.jetty.http.MetaData.Request;
import org.eclipse.jetty.http.MetaData.Response;

import model.*;

public class ReceitasDAO extends DAO {
    public ReceitasDAO() {
		super();
		conectar();
	}


public boolean insert(Receitas receitas){
    boolean status = false;


    return status;
}

public boolean delete(Receitas receitas){
    boolean status = false;


    return status;
}


public Receitas getReceitaById(int id){
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

