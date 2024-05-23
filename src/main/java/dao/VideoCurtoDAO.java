package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;


public class VideoCurtoDAO extends DAO {
    public VideoCurtoDAO(){
        super();
        conectar();
    }

    public boolean insert(VideoCurto videoCurto){
        boolean status = false;

        try{
            String sql = "INSERT INTO videocurto (plataforma, codigovideo, autor, titulo, descricao, data_publicacao, id_usuario) VALUES (?,?,?,?,?,?,?)";
        
        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setString(1, videoCurto.getPlataforma());
        ps.setString(2, videoCurto.getCodigo());
        ps.setString(3, videoCurto.getAutor());
        ps.setString(4, videoCurto.getTitulo());
        ps.setString(5, videoCurto.getDescricao());
        ps.setDate(6, Date.valueOf(videoCurto.getData()));
        ps.setInt(7, videoCurto.getId_usuario());

        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            status = true;
        }

        ps.close();


        } catch(SQLException e){
            e.printStackTrace();
        }

        return status;
    }

    public List<VideoCurto> getVideos(){
        String sql = "SELECT * FROM videocurto";
        List<VideoCurto> videos = new ArrayList<>();

        try{
            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                VideoCurto video = new VideoCurto();
                video.setId_video(rs.getInt("id_video"));
                video.setPlataforma(rs.getString("plataforma"));
                video.setAutor(rs.getString("autor"));
                video.setCodigo(rs.getString("codigovideo"));
                video.setTitulo(rs.getString("titulo"));
                video.setDescricao(rs.getString("descricao"));
                Date sqlDate = rs.getDate("data_publicacao");
                    if (sqlDate != null) {
                        video.setData(sqlDate.toLocalDate());
                    } 
                videos.add(video);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return videos;
    }
    
    public String getUrl(VideoCurto video) {
        switch (video.getPlataforma()) {
            case "YouTube":
                return "https://www.youtube.com/embed/" + video.getCodigo();
            case "TikTok":
                return "https://www.tiktok.com/embed/v2/" + video.getCodigo();
            case "Instagram":
                return "https://www.instagram.com/p/" + video.getCodigo() + "/embed";
            default:
                return "";
        }
    }
}
