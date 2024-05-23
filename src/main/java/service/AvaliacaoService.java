package service;

import spark.*;
import dao.*;
import model.*;
;


public class AvaliacaoService {
    private AvaliacoesDAO avaliacoesDAO = new AvaliacoesDAO();

    public Object register(Request request, Response response, int id){
            Session session = request.session(false);
            User currentUser = session.attribute("currentUser");

            if (currentUser == null) {
			response.redirect("/login");
			return null; 
		}


        int nota = Integer.parseInt(request.queryParams("rating"));
        String descricao = request.queryParams("descricao");

        Avaliacoes avaliacoes = new Avaliacoes(currentUser.getId(), id, nota, descricao);
        boolean insertORupdate = false;

        boolean existe = avaliacoesDAO.check(currentUser.getId(), id);
		if (existe) {
			insertORupdate = avaliacoesDAO.update(avaliacoes);
		} else {
			insertORupdate = avaliacoesDAO.insert(avaliacoes);
		}
	
		if (insertORupdate) {
			response.status(200);
            request.session().attribute("flash", "Avaliação registrada com sucesso!");
            response.redirect("/detalhes/"+id); 
		} else {
			response.status(500); 
			return response; 
		}

        return response;
    }

	public int total(int id){
		return avaliacoesDAO.contador(id);
	}
}
