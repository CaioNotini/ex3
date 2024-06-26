package service;

import spark.*;
import dao.*;
import model.*;
import java.security.*;
import java.time.*;
import java.math.*;
import static spark.Spark.halt;


public class UserService {
	private UserDAO userDAO = new UserDAO();


	public User getUserById(int id_usuario){
        return userDAO.getById(id_usuario);
    }

	public Object register(Request request, Response response) {
		String nome = request.queryParams("nome");
        String email = request.queryParams("email");
		String senha = criptografia(request.queryParams("senha"));

		User user = new User(nome, email, senha);

		if (userDAO.insert(user) == true) {
        request.session().attribute("flash", "Usuário cadastrado com sucesso!");
			response.redirect("/login");
		} else {
        request.session().attribute("flash", "Erro ao cadastrar usuário");
		response.redirect("/register");
		}

		return response;
	}

	public Object login(Request request, Response response) {
		String email = request.queryParams("email");
		String senha = criptografia(request.queryParams("senha"));
	
		User user = userDAO.get(email, senha);
	
		if (user != null) {
			// Cria uma sessão para o usuário
			Session session = request.session(true);
			session.attribute("currentUser", user); // Armazena o objeto do usuário na sessão
	
			response.redirect("/index");
		} else {
			response.status(401);
			response.redirect("/login");
		}
	
		return response;
	}


public void logout(Request request, Response response) {
    request.session().invalidate();
    response.redirect("/register");
    halt();
}   

	public Object delete(Request request, Response response){
    UserDAO dao = new UserDAO();
    Session session = request.session(false);
    if (session != null) {
        User currentUser = session.attribute("currentUser");
        if (currentUser != null) {
            try {
                dao.delete(currentUser);
                session.invalidate(); 
				request.session().attribute("flash", "Conta excluída com sucesso!");
                response.redirect("/register"); 
            } catch (Exception e) {
                response.status(500);
                System.err.println("Erro ao deletar o usuário: " + e.getMessage());
    
            }
        } else {
            response.status(400); 
        }
    } else {
        response.status(403);
    }
	return response;
}



	
	
	private String criptografia(String senha) {
		String senhaHash = "";
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");

			m.update(senha.getBytes(), 0, senha.length());
			senhaHash = new BigInteger(1, m.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			System.err.println(e);
		}

		return senhaHash;
	}


	public Object perfil(Request request, Response response) {
		Session session = request.session(false); // Obtém a sessão existente sem criar uma nova
		User currentUser = session.attribute("currentUser");
	
		if (currentUser == null) {
			response.redirect("/login");
			return null; 
		}
	
		// Recupera dados do perfil do formulário
		String sexo = request.queryParams("sexo");
		String idade = request.queryParams("idade");
		int altura = Integer.parseInt(request.queryParams("altura"));
		int peso = Integer.parseInt(request.queryParams("peso"));
		int nivelAtividade = Integer.parseInt(request.queryParams("nivelatividade"));
		
		LocalDate data = LocalDate.parse(idade);
		

		Perfil perfil = new Perfil(currentUser.getId(), data, sexo, altura, peso, nivelAtividade);
		boolean insertORupdate = false;
	
		boolean existe = userDAO.checkProfileExists(currentUser.getId());
		if (existe) {
			insertORupdate = userDAO.updateProfile(perfil);
		} else {
			insertORupdate = userDAO.insertProfile(perfil);
		}
	
		if (insertORupdate) {
			response.status(200);
		} else {
			response.status(500); // 
			return response; 
		}

		//Calculando a idade
			LocalDate dataUsuario = perfil.getIdade();
			LocalDate dataAtual = LocalDate.now();
			Period period = Period.between(dataUsuario, dataAtual);
			int age = period.getYears();

	
		// Calcula as calorias
		int calories = calculateCalories(perfil.getSexo(), perfil.getPeso(), perfil.getAltura(), age, perfil.getNivelAtividade());
		Calorias calorias = new Calorias(currentUser.getId(), calories);
		boolean KGInsertORupdate;
	
		boolean existeCal = userDAO.checkCaloriesExists(currentUser.getId());
		if (existeCal) {
			KGInsertORupdate = userDAO.updateCalories(calorias);
		} else {
			KGInsertORupdate = userDAO.insertCalories(calorias);
			request.session().attribute("flash", "Informações atualizadas com sucesso!");
			response.redirect("/profile");
		}
	
		if (!KGInsertORupdate) {
			response.status(500); 
		}
	
		return response; 
	}
	

	private int calculateCalories(String gender, double weight, double height, int age, int activityLevel) {

		double activity = 0.0;
		if(activityLevel == 1){
			activity = 1.2;
		} else if(activityLevel == 2){
			activity = 1.37;
		}  else if(activityLevel == 3){
			activity = 1.55;
		} else if(activityLevel == 4){
			activity = 1.725;
		} else if (activityLevel == 5) {
			activity = 1.9;
		}
		double tmb;
		if (gender.equals("M")) {
			// Fórmula para homens
			tmb = (10 * weight) + (6.25 * height) - (5 * age) + 5;
		} else {
			// Fórmula para mulheres
			tmb = (10 * weight) + (6.25 * height) - (5 * age) - 161;
		}
		double result =  (tmb * activity);
		return (int) result;
	}


	public String getNome(int id){
		return userDAO.getNome(id);
	}

	public Calorias getCalorias(int id){
		return userDAO.getCalorias(id);
	}
}
