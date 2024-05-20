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
    Session session = request.session(false); // Obtém a sessão existente sem criar uma nova
    User currentUser = session.attribute("currentUser");
    IngredientesDAO ingredientesDAO = new IngredientesDAO();
    ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();
    ReceitasDAO receitasDAO = new ReceitasDAO();
    
    if (currentUser == null) {
        response.redirect("/login");
        return null; 
    }

    String nome = request.queryParams("nome");
    String video = request.queryParams("video");
    int tempo = Integer.parseInt(request.queryParams("tempo"));
    String tipo = request.queryParams("tipo");
    String horario = request.queryParams("horario");
    String descricao = request.queryParams("descricao");

    String[] ingredientes = request.queryParamsValues("ingrediente");
    String[] quantidades = request.queryParamsValues("quantidade");
    String[] observacoes = request.queryParamsValues("observacao");

    float totalCalorias = 0;
    try {
        for (int i = 0; i < ingredientes.length; i++) {
            float calorias = ingredientesDAO.getCalorias(ingredientes[i]);
            float quantidade = Float.parseFloat(quantidades[i]);
            float tmp = (calorias / 100) * quantidade; 
            totalCalorias += tmp;
        }

        Receitas receita = new Receitas(nome, video, descricao, tempo, tipo, horario, totalCalorias, currentUser.getId());

        boolean receitaInserida = receitasDAO.insert(receita);
        if (!receitaInserida) {
            response.status(500);
            response.body("Erro ao inserir receita.");
            return response;
        }

        boolean allInserted = true;

        for (int i = 0; i < ingredientes.length; i++) {
            String nomeIngrediente = ingredientes[i];
            float quantidade = Float.parseFloat(quantidades[i]);
            String obs = observacoes[i];

            int idIngrediente = ingredientesDAO.getId(nomeIngrediente);
            int idReceita = receitasDAO.getId(nome);

            ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente(idReceita, idIngrediente, quantidade, obs);

            boolean status = receitaIngredienteDAO.insert(receitaIngrediente);
            if (!status) {
                allInserted = false;
                break; 
            }
        }

        if (allInserted) {
            response.redirect("/receitas");
        } else {
            response.status(500);
            response.body("Erro ao inserir ingredientes da receita.");
        }
    } catch (Exception e) {
        response.status(500);
        response.body("Erro no servidor: " + e.getMessage());
        e.printStackTrace();
    }

    return response;
}

}
