package service;

import spark.*;
import java.util.*;
import dao.IngredientesDAO;
import dao.UserDAO;
import model.Calorias;
import model.Ingredientes;
import model.Perfil;
import model.Calorias;
import model.User;
import java.security.*;
import java.time.*;


import org.eclipse.jetty.server.LocalConnector;

import static spark.Spark.halt;
import static spark.Spark.redirect;

import java.math.*;

public class IngredientesService {
    private IngredientesDAO ingredientesDAO = new IngredientesDAO();

        public List<Ingredientes> exibir(){
            return ingredientesDAO.getIngrediente();

        }
    
    	public Object register(Request request, Response response) {
		 String nome = request.queryParams("nome");
        float calorias = Float.parseFloat(request.queryParams("calorias"));
        float proteinas = Float.parseFloat(request.queryParams("proteinas"));
        float gorduras = Float.parseFloat(request.queryParams("gorduras"));
        float carboidrato = Float.parseFloat(request.queryParams("carboidratos"));


        Ingredientes ingrediente = new Ingredientes(nome,calorias,proteinas,gorduras,carboidrato);

        if (ingredientesDAO.insert(ingrediente)  == true) {
			response.redirect("/alimentos");
		} else {
		}

		return response;
	}


}
