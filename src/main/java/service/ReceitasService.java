package service;

import spark.*;
import dao.*;
import model.*;

import java.security.*;
import java.time.*;
import org.eclipse.jetty.server.LocalConnector;
import static spark.Spark.halt;
import static spark.Spark.redirect;
import java.math.*;

public class ReceitasService {
    ReceitasDAO receitasDAO = new ReceitasDAO();

    public Receitas exibirReceitas(int id){
        return receitasDAO.getReceitaById(id);

    }
    
}
