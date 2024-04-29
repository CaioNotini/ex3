package dao;

import java.sql.*;
import model.User;
import model.Perfil;
import model.Calorias;

public class UserDAO extends DAO {

	public UserDAO() {
		super();
		conectar();
	}

	public boolean insert(User user) {
		boolean status = false;

		try {
			String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

			PreparedStatement preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getSenha());

			preparedStatement.executeUpdate();
			preparedStatement.close();

			status = true;
		} catch (SQLException e) {
			System.err.println(e);
		}

		return status;
	}

	public boolean delete(User user){
		boolean status = false;
		try{
			String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

			PreparedStatement preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, user.getId());

			preparedStatement.executeUpdate();
			preparedStatement.close();

			status=true;

		} catch (SQLException e){
			System.err.println(e);
		}

	return status;
	}

	public boolean insertProfile(Perfil perfil){
		boolean status = false;

		
		try{
        String sql = "UPDATE usuarios SET sexo = ?, datanascimento = ?, altura = ?, peso = ?, nivelfitnes = ? WHERE id_usuario = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			   stmt.setString(1, perfil.getSexo());
        		stmt.setDate(2, Date.valueOf(perfil.getIdade()));
       		 	stmt.setInt(3, perfil.getAltura());
        		stmt.setInt(4, perfil.getPeso());
        		stmt.setInt(5, perfil.getNivelAtividade());
        		stmt.setInt(6, perfil.getid_usuario()); 

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				status = true;
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir perfil: " + e.getMessage());
		}

		return status;

	}

	public boolean insertCalories(Calorias calories){
		boolean status = false; 
		try{
			String sql = "UPDATE usuarios SET caloriasdiarias = ?  WHERE id_usuario = ?";

			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(2, calories.getid_usuario());
			stmt.setInt(1, calories.getCalorias());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				status = true;
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir perfil: " + e.getMessage());
		}

		return status;

	}

	public User get(String email, String senha) {
		User user = null;

		try {
			String sql = "SELECT * FROM usuarios WHERE email=? AND senha=?";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, senha);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"),
						rs.getString("senha") );
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return user;
	}

	public User getById(int id) {
		User user = null;

		try {
			String sql = "SELECT * FROM usuarios WHERE id_usuario=?";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"),
						rs.getString("senha"));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return user;
	}

	public Calorias getCalorias(int id_usuario) {
		Calorias calorias = null;

		try {
			String sql = "SELECT caloriasdiarias FROM usuarios WHERE id_usuario=?";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id_usuario);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int caloriasDiarias = rs.getInt("caloriasdiarias");

				calorias = new Calorias(id_usuario, caloriasDiarias);

			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return calorias;
	}

	
	public boolean update(User user) {
		boolean status = false;
		try {
			String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?,  WHERE id_usuario = ?";
			PreparedStatement st = conexao.prepareStatement(sql);

			st.setString(2, user.getNome());
			st.setString(3, user.getEmail());
			st.setString(4, user.getSenha());
			st.setInt(1, user.getId());

			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}

	public boolean updateProfile(Perfil perfil){
		boolean status	 = false;
		try{
			String sql = "UPDATE usuarios SET sexo = ?, datanascimento = ?, altura = ?, peso = ?, nivelfitnes = ? WHERE id_usuario = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(2, perfil.getSexo());
			stmt.setInt(4, perfil.getAltura());
        	stmt.setDate(2, Date.valueOf(perfil.getIdade()));
			stmt.setInt(5, perfil.getPeso());
			stmt.setInt(6, perfil.getNivelAtividade());
			stmt.setInt(1, perfil.getid_usuario());

			stmt.executeUpdate();
			stmt.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}

	public boolean updateCalories(Calorias calories) {
		boolean status = false;
		try {
			String sql = "UPDATE calorias SET caloriasdiarias = ?,  WHERE id_usuario = ?";
			PreparedStatement st = conexao.prepareStatement(sql);

			st.setInt(2, calories.getCalorias());
			st.setInt(1, calories.getid_usuario());

			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}


	public boolean checkProfileExists(int id) {
		String sql = "SELECT COUNT(1) FROM perfilusuario WHERE id_usuario = ?";
	
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next() && rs.getInt(1) > 0) {
					return true; 
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao verificar a existência do perfil: " + e.getMessage());
		}
	
		return false;
	}

	
	public boolean checkCaloriesExists(int id) {
		String sql = "SELECT COUNT(1) FROM calorias WHERE id_usuario = ?";
	
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next() && rs.getInt(1) > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao verificar a existência do perfil: " + e.getMessage());
		}
	
		return false;
	}
	
	
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {
			String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}

}