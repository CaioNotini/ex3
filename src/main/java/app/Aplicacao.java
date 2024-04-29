package app;

import spark.*;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import static spark.Spark.*;


import dao.*;
import service.*;
import model.*;

public class Aplicacao {
    private static UserService userService;
    private static ReceitasService receitasService;

    public static void main(String[] args) {
        
        
        VelocityTemplateEngine engine = new VelocityTemplateEngine();
        staticFiles.location("/public");
        
        port(5050);
        get("/login",(request,response) -> login(request,response), engine);
		post("/login", (request, response) -> userService.login(request, response));

		get("/register",(request,response)-> cadastro(request, response), engine);
		post("/register", (request, response) -> userService.register(request, response));

        get("/index",(request,response)-> index(request, response), engine);
        post("/index", (request, response) -> userService.perfil(request, response));

        get("/profile", (request,response)-> profile(request,response), engine);
        get("/logout", (request,response)-> { userService.logout(request, response); return null;});
        post("/delete", (request, response) -> userService.delete(request, response));

        get("/mercado", (request,response)-> mercado(request,response), engine);


        get("/receitas", (request,response)-> receitas(request,response), engine);


        get("/gerador", (request,response)-> gerador(request,response), engine);
   
    }

    public static ModelAndView cadastro(Request request, Response response) {
        String message = request.session().attribute("message");
		HashMap<String, Object> model = new HashMap<>();
        if(message != null){
            model.put(message, message);
            request.session().removeAttribute(message);
        }

		return new ModelAndView(model, "templates/register.vm");
	}

	public static ModelAndView login(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "paginas/login.html");
	}

    public static ModelAndView index(Request request, Response response){
        HashMap<String,Object> model=new HashMap<>();
        
        return new ModelAndView(model, "templates/index.vm");
    }

    public static ModelAndView profile(Request request, Response response) {
        HashMap<String, Object> model = new HashMap<>();    
        Session session = request.session(false);
        if (session != null) {
            User currentUser = session.attribute("currentUser");
            if (currentUser != null) {
                UserDAO userDAO = new UserDAO();
                Calorias calorias = userDAO.getCalorias(currentUser.getId());

                model.put("user", currentUser);
                model.put("calorias", calorias);
            }
        }
        return new ModelAndView(model, "templates/profile.vm");
    }


    
    public static ModelAndView mercado(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "paginas/mercado.html");
	}

     
    public static ModelAndView receitas(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "paginas/receitas.html");
	}

    public static ModelAndView gerador(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "paginas/gerador.html");
	}
}