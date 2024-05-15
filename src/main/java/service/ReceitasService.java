package service;

import spark.*;
import dao.*;
import model.*;
import java.util.*;

public class ReceitasService {
    ReceitasDAO receitasDAO = new ReceitasDAO();

    public List<Receitas> exibirReceitas(){
        return receitasDAO.getReceita();

    }

    public Receitas exibirReceitas(int id){
        return receitasDAO.getReceita(id);

    }
    

    public Object register(Request request, Response response){
        String nome = request.queryParams("nome");
        String video = request.queryParams("video");
        int tempo = Integer.parseInt(request.queryParams("tempo"));
        String tipo = request.queryParams("tipo");
        String horario = request.queryParams("horario");

        return response;
    }
}
