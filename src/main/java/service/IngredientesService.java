package service;

import spark.*;
import java.util.*;
import model.*;
import dao.*;

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
