package dao;


import java.sql.*;
import java.util.*;
import model.*;

public class ReceitasDAO extends DAO {
    public ReceitasDAO() {
		super();
		conectar();
	}


public boolean insert(Receitas receita){
    boolean status = false;

    try {
        String sql = "INSERT INTO receita (nome, codigovideo, descricao, tempo, tipo, horario, totalcalorias, id_usuario, porcao) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setString(1, receita.getNome());
        ps.setString(2, receita.getCodigoVideo());
        ps.setString(3, receita.getDescricao());
        ps.setInt(4, receita.getTempo());
        ps.setString(5, receita.getTipoReceita());
        ps.setString(6, receita.getHorario());
        ps.setFloat(7, receita.getTotalCalorias());
        ps.setInt(8, receita.getId_usuario());
        ps.setFloat(9, receita.getPorcao());


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
            receita.setPorcao(rs.getFloat("porcao"));

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
            receita.setPorcao(rs.getFloat("porcao"));

        }
    } catch (SQLException e) {
			System.err.println(e);
		}

    return receita;
}

public int getId(String nome){
    String sql = "SELECT id_receita FROM receita WHERE nome = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, nome);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id_receita");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return 0; 
}

public List<Receitas> getAvaliadas(){
    AvaliacoesDAO avaliacoesDAO = new AvaliacoesDAO();

    List<Receitas> receitas = new ArrayList<>();
    Map<Integer, Double> media = avaliacoesDAO.media();

    List<Map.Entry<Integer, Double>> mediasOrdenadas = new ArrayList<>(media.entrySet());
    mediasOrdenadas.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

    String sql = "SELECT * FROM receita WHERE id_receita = ?";
    try{
        PreparedStatement ps = conexao.prepareStatement(sql);
        for(Map.Entry<Integer, Double> entry : mediasOrdenadas){
            int id = entry.getKey();
            double mediaAvaliacao = entry.getValue();

            ps.setInt(1, id);
            try{
                ResultSet rs =ps.executeQuery();
                if(rs.next()){
                    Receitas receita = new Receitas();
                    receita.setId_receita(rs.getInt("id_receita"));
                    receita.setNome(rs.getString("nome"));
                    receita.setCodigoVideo(rs.getString("codigoVideo"));
                    receita.setDescricao(rs.getString("descricao"));
                    receita.setNota(mediaAvaliacao);
                    receitas.add(receita);
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

    } catch (SQLException e){
        e.printStackTrace();
    }
    return receitas;
}
 
}

