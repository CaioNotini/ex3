package service;

import spark.*;
import dao.*;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
;

public class DietaService {
    DietaDAO dietaDAO = new DietaDAO();
    ReceitasDAO receitasDAO = new ReceitasDAO();
    DietaReceitaDAO dietaReceitaDAO = new DietaReceitaDAO();

    public Object register(Request request, Response response){
        Session session = request.session(false);
        User currentUser = session.attribute("currentUser");

        if(currentUser == null){
            response.redirect("/login");
            return null;
        }

        String nome = request.queryParams("nome");
        String tipo = request.queryParams("tipo");
        int calorias = Integer.parseInt(request.queryParams("calories"));
        int refeicoes = Integer.parseInt(request.queryParams("meals"));

        LocalDate hoje = LocalDate.now();

        Dieta dieta = new Dieta(nome, currentUser.getId(), calorias, tipo, hoje);
        boolean dietaInserida = dietaDAO.insert(dieta);
                if (!dietaInserida) {
            response.status(500);
            response.body("Erro ao inserir receita.");
            return response;
        }

        int idDieta = dietaDAO.getId(nome, currentUser.getId());
        int[] idsReceitas = getIdReceitas(refeicoes, tipo, calorias);

        for (int id : idsReceitas) {
    System.out.println("ID da receita: " + id);
}


        for (int idReceita : idsReceitas) {
            DietaReceita dietaReceita = new DietaReceita(idDieta, idReceita);
            boolean dietaReceitaInserida = dietaReceitaDAO.insert(dietaReceita);
            if (!dietaReceitaInserida) {
                response.status(500);
                response.body("Erro ao inserir dieta-receita.");
                return response;
            }
        }

        response.redirect("/dieta/"+ idDieta);
        return response;
    }
        


public int[] getIdReceitas(int refeicoes, String tipo, double calorias) {
    List<Integer> idReceitas = new ArrayList<>();

    double cafe = 0.2;
    double almoco = 0.35;
    double jantar = 0.35;
    double lanche1 = 0.05;
    double lanche2 = 0.05;
    double sobremesa = 0.1;

    if (refeicoes == 4) {
        cafe = 0.2;
        almoco = 0.35;
        jantar = 0.3;
        lanche1 = 0.1;
    } else if (refeicoes == 5) {
        cafe = 0.2;
        almoco = 0.3;
        jantar = 0.3;
        lanche1 = 0.1;
        lanche2 = 0.1;
    } else if (refeicoes == 6) {
        cafe = 0.2;
        almoco = 0.25;
        jantar = 0.25;
        lanche1 = 0.1;
        lanche2 = 0.1;
        sobremesa = 0.1;
    }

    idReceitas.add(receitasDAO.getCafe(tipo, calorias * cafe * 0.7, calorias * cafe * 1.3));
    idReceitas.add(receitasDAO.getAlmoco(tipo, calorias * almoco * 0.7, calorias * almoco * 1.3));
    idReceitas.add(receitasDAO.getJantar(tipo, calorias * jantar * 0.7, calorias * jantar * 1.3));

    if (refeicoes >= 4) {
        idReceitas.add(receitasDAO.getLanche(tipo, calorias * lanche1 * 0.7, calorias * lanche1 * 1.3));
    }
    if (refeicoes >= 5) {
        idReceitas.add(receitasDAO.getLanche(tipo, calorias * lanche2 * 0.7, calorias * lanche2 * 1.3));
    }
    if (refeicoes == 6) {
        idReceitas.add(receitasDAO.getSobremesa(tipo, calorias * sobremesa * 0.7, calorias * sobremesa * 1.3));
    }

    

    return idReceitas.stream().mapToInt(Integer::intValue).toArray();
}

    public Dieta exibirDieta(int id){
        return dietaDAO.getDieta(id);
    }

    public List<Dieta> exibirDietas(int id){
        return dietaDAO.getDietas(id);
    }

    public List<DietaReceita> getIdsReceitas(int id){
        return dietaReceitaDAO.getId(id);
    }

    


    }
