package JDBC;

import Entity.iOT_VideoInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {
    private String DB_Url="jdbc:mysql://127.0.0.1:3306/easyiot?characterEncoding=gbk&useSSL=false&serverTimezone=UTC";
    private String DB_USER="root";
    private String DB_PASS="lqh135158";
    public ArrayList<iOT_VideoInfo> sources() throws ClassNotFoundException {
        ArrayList<iOT_VideoInfo> list = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement()){
            String sql = "select * from iot_video_info";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                list.add(new iOT_VideoInfo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("scene_id"),
                        rs.getString("app_name"),
                        rs.getInt("status"),
                        rs.getInt("image_id"),
                        rs.getInt("delete_flag"),
                        rs.getInt("video_type"),
                        rs.getInt("seq"),
                        rs.getInt("relate_id"),
                        rs.getString("rtmp_url_high"),
                        rs.getString("rtmp_url_common"),
                        rs.getString("hls_url"),
                        rs.getInt("camera_type"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("ip"),
                        rs.getString("port"),
                        rs.getString("codectype"),
                        rs.getString("channel"),
                        rs.getString("subtype"),
                        rs.getInt("aid"),
                        rs.getString("atime"),
                        rs.getInt("mid"),
                        rs.getString("mtime")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
