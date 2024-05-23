package service;

import spark.*;
import dao.*;
import model.*;

import java.time.LocalDate;
import java.util.*;

public class VideoCurtoService {
    UserDAO userDAO = new UserDAO();
    VideoCurtoDAO videoCurtoDAO = new VideoCurtoDAO();

    public List<VideoCurto> exibirVideos(){
        return videoCurtoDAO.getVideos();
    }


    public Object register(Request request, Response response){
        Session session = request.session(false);
        User currentUser = session.attribute("currentUser");

        if(currentUser == null){
            response.redirect("/login");
            return  null;
        }

        String titulo = request.queryParams("titulo");
        String data = request.queryParams("data");
        String codigo = request.queryParams("codigoVideocurto");
        String autor = request.queryParams("autor");
        String plataforma = request.queryParams("plataforma");
        String descricao = request.queryParams("desc");

        LocalDate dataPublicacao = LocalDate.parse(data);


        VideoCurto videoCurto = new VideoCurto(plataforma, codigo, autor, titulo, descricao, dataPublicacao, currentUser.getId());

        boolean inserted = videoCurtoDAO.insert(videoCurto);
     if(inserted){
        request.session().attribute("flash", "Vídeo inserido com sucesso!");
        response.redirect("/receitas");

     } else{
        response.status(500);
     }

        return response;
        }
}

