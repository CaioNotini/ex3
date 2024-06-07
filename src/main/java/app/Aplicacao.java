package app;

import spark.*;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;
import java.util.*;


import dao.*;
import service.*;
import model.*;

public class Aplicacao {
    private static UserService userService = new UserService();
    private static ReceitasService receitasService = new ReceitasService();
    private static IngredientesService ingredientesService = new IngredientesService();
    private static AvaliacaoService avaliacaoService = new AvaliacaoService();
    private static VideoCurtoService videoCurtoService = new VideoCurtoService();
    private static DietaService dietaService = new DietaService();

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
        get("/t-logout", (request,response)-> { userService.logout(request, response); return null;});
        post("/delete", (request, response) -> userService.delete(request, response));

        get("/mercado", (request,response)-> mercado(request,response), engine);

        get("/dieta/:id", (request,response)-> dieta(request,response), engine);


        get("/detalhes/:id", (request,response)-> detalhes(request,response), engine);
        post("/avalie/:id", (request, response) -> {
            int id = Integer.parseInt(request.params(":id")); 
            return avaliacaoService.register(request, response, id);
        });



        get("/gerador", (request,response)-> gerador(request,response), engine);
        post("/gerador", (request, response) -> dietaService.register(request, response));
 

        get("/alimentos", (request,response)-> alimentos(request,response), engine);
        post("/alimentos", (request, response) -> ingredientesService.register(request, response));

        
        get("/receitas", (request,response)-> receitas(request,response), engine);
        post("/receitas", (request,response)-> receitasService.register(request, response));
        post("videos", (request,response) -> videoCurtoService.register(request, response));


        


        
   
    }

    public static ModelAndView cadastro(Request request, Response response) {
        Session session = request.session(false);
        String message = null;
        if (session != null) {
        message = session.attribute("flash");
        session.removeAttribute("flash");
        }

        HashMap<String, Object> model = new HashMap<>();


        model.put("flash", message);

        
		return new ModelAndView(model, "templates/register.vm");
	}

	public static ModelAndView login(Request request, Response response) {
         Session session = request.session(false);
         String flashMessage = null;
            if (session != null) {
            flashMessage = session.attribute("flash");
            session.removeAttribute("flash");
            }

         HashMap<String, Object> model = new HashMap<>();
         model.put("flash", flashMessage);

		return new ModelAndView(model, "templates/login.vm");
	}

        public static ModelAndView index(Request request, Response response){
            HashMap<String,Object> model=new HashMap<>();
            ReceitasDAO receitasDAO = new ReceitasDAO();


            List<Receitas> rc = receitasService.exibirAvaliadas();
            model.put("receita", rc);

            return new ModelAndView(model, "templates/index.vm");
        }

    
     
    public static ModelAndView detalhes(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();
        ReceitasDAO receitasDAO = new ReceitasDAO();
        AvaliacoesDAO avaliacoesDAO = new AvaliacoesDAO();
        PassosDAO passosDAO = new PassosDAO();
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();

        int id = Integer.parseInt(request.params(":id"));

        List<ReceitaIngrediente> ri = receitasService.getReceitaIngrediente(id);
        Receitas rc = receitasService.exibirReceitas(id);
        int av = avaliacaoService.total(id);
        List<Passos> ps = receitasService.getPassos(id);
        List<Avaliacoes> nt = avaliacaoService.exibir(id);

        for(ReceitaIngrediente in : ri ){
            String nome = ingredientesService.getNome(in.getidIngredientes());
            in.setNomeIngrediente(nome);
        }

        for(Avaliacoes avaliacoes : nt){
            String nome = userService.getNome(avaliacoes.getId_usuario());
            avaliacoes.setNome(nome);
        }

        model.put("ingredientes", ri);
        model.put("passos", ps);
        model.put("total", av);
        model.put("receita", rc);
        model.put("avaliacoes", nt);

        Session session = request.session(false);
         String flashMessage = null;
            if (session != null) {
            flashMessage = session.attribute("flash");
            session.removeAttribute("flash");
            }
        model.put("flash", flashMessage);

		return new ModelAndView(model, "templates/detalhes.vm");
	}

    public static ModelAndView profile(Request request, Response response) {
        HashMap<String, Object> model = new HashMap<>();    
        Session session = request.session(false);
        Session session2 = request.session(false);

         String message = null;
        if (session != null) {
        message = session2.attribute("flash");
        session2.removeAttribute("flash");
        }


        if (session != null) {
           
            User currentUser = session.attribute("currentUser");
            if (currentUser != null) {
                UserDAO userDAO = new UserDAO();
                DietaDAO dietaDAO = new DietaDAO();
                Calorias calorias = userDAO.getCalorias(currentUser.getId());
                List<Dieta> dietas = dietaDAO.getDietas(currentUser.getId());

                for(Dieta di : dietas){
                    System.out.println(di.getID_Dieta());
                }

                model.put("user", currentUser);
                model.put("calorias", calorias);
                model.put("flash", message);
                model.put("dietas", dietas);
            }
        }
        return new ModelAndView(model, "templates/profile.vm");
    }


    
    public static ModelAndView mercado(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "paginas/mercado.html");
	}


    public static ModelAndView gerador(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "paginas/gerador.html");
	}

    public static ModelAndView dieta(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();

        ReceitasDAO receitasDAO = new ReceitasDAO();
        DietaDAO dietaDAO = new DietaDAO();
        DietaReceitaDAO dietaReceitaDAO = new DietaReceitaDAO();

        int id = Integer.parseInt(request.params(":id"));

        Dieta di = dietaService.exibirDieta(id);
        List<DietaReceita> dr = dietaService.getIdsReceitas(id);
        List<Receitas> re = receitasService.getReceitas(dr);

       System.out.println(di.getTipoAlimentacao());

        String nome = userService.getNome(di.getID_Usuario());
            di.setNomeUsuario(nome);

        

        model.put("dieta", di);
        model.put("receita", re);

		return new ModelAndView(model, "templates/dieta.vm");
	}

     public static ModelAndView alimentos(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        List<Ingredientes> in = ingredientesService.exibir();
        model.put("ingrediente", in);

         Session session = request.session(false);
         String flashMessage = null;
            if (session != null) {
            flashMessage = session.attribute("flash");
            session.removeAttribute("flash");
            }
        model.put("flash", flashMessage);


		return new ModelAndView(model, "templates/alimentos.vm");
	}

    public static ModelAndView receitas(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();
       
        ReceitasDAO receitasDAO = new ReceitasDAO();
        List<Receitas> rc = receitasService.exibirAvaliadas();
        List<Receitas> rc2 = receitasService.exibirReceitas();
        model.put("receita", rc);
        model.put("todasReceitas", rc2);


        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        List<Ingredientes> in = ingredientesService.exibir();
        model.put("ingrediente", in);

        VideoCurtoDAO videoCurtoDAO = new VideoCurtoDAO();
        List<VideoCurto> vc = videoCurtoService.exibirVideos();

         for (VideoCurto video : vc) {
        video.setUrl(videoCurtoDAO.getUrl(video));
        }
        model.put("videos", vc);

         Session session = request.session(false);
         String flashMessage = null;
            if (session != null) {
            flashMessage = session.attribute("flash");
            session.removeAttribute("flash");
            }
        model.put("flash", flashMessage);

		return new ModelAndView(model, "templates/receitas.vm");
	}

   


}