package dao;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import org.eclipse.jetty.http.MetaData.Request;
import org.eclipse.jetty.http.MetaData.Response;

import model.*;
public class ReceitaIngredienteDAO extends DAO{
    public ReceitaIngredienteDAO(){
        super();
        conectar();
    }
}
