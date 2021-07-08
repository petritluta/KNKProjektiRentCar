package repositories;

import Utils.DbHelper;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    public static User getLastUser(ResultSet rs,Connection conn) throws Exception {
        int lastId=0;
        if(rs.next())
        {

            lastId=rs.getInt(1);

        }

        PreparedStatement getLastUser=conn.prepareStatement("SELECT * FROM users where id=?");
        getLastUser.setInt(1,lastId);
        ResultSet res=getLastUser.executeQuery();
        res.next();

        return getUserFromRes(res);
    }

    public static User find(int id) throws  Exception{
        Connection conn=DbHelper.getConnection();
        PreparedStatement stmt=conn.prepareStatement("SELECT * FROM users where id =? ");
        stmt.setInt(1,id);

        ResultSet res=stmt.executeQuery();
        if (!res.next()) return null;

        return getUserFromRes(res);
    }

    public static User find(String email) throws  Exception{
        Connection conn=DbHelper.getConnection();
        PreparedStatement stmt=conn.prepareStatement("SELECT * FROM users where email =? ");
        stmt.setString(1,email);

        ResultSet res=stmt.executeQuery();
        if (!res.next()) return null;

        return getUserFromRes(res);
    }

    public static List<User> getAll() throws Exception {
        Connection conn=DbHelper.getConnection();
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM users");
        ArrayList<User> users=new ArrayList<>();
        while (rs.next())
        {
            users.add(getUserFromRes(rs));
        }

        return users;
    }

    public static User create(User user) throws Exception {

        Connection conn= DbHelper.getConnection();

        PreparedStatement stmt=conn.prepareStatement("INSERT into users(first_name,last_name,email,password,salt,is_admin) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1,user.getFirst_name());
        stmt.setString(2,user.getLast_name());
        stmt.setString(3,user.getEmail());
        stmt.setString(4,user.getPassword());
        stmt.setString(5,user.getSalt());
        stmt.setBoolean(6,user.isIs_admin());

        stmt.executeUpdate();

        return getLastUser(stmt.getGeneratedKeys(),conn);
    }


    public static User update(User user) throws  Exception{
        Connection conn=DbHelper.getConnection();
        PreparedStatement stmt=conn.prepareStatement("UPDATE users SET first_name=?,last_name=?,email=?,password=?,salt=?,is_admin=? WHERE id=?");

        stmt.setString(1,user.getFirst_name());
        stmt.setString(2,user.getLast_name());
        stmt.setString(3,user.getEmail());
        stmt.setString(4,user.getPassword());
        stmt.setString(5,user.getSalt());
        stmt.setBoolean(6,user.isIs_admin());
        stmt.setInt(7,user.getId());

        if(stmt.executeUpdate()!=1)
            throw  new Exception("NO ROWS AFFECTED");

        return find(user.getId());
    }

    public static boolean delete(User user) throws Exception{
        Connection conn=DbHelper.getConnection();
        PreparedStatement stmt=conn.prepareStatement("DELETE FROM users WHERE id =? ");
        stmt.setInt(1,user.getId());

        return stmt.executeUpdate() == 1;
    }


    private static User getUserFromRes(ResultSet res) throws Exception {
        int id =res.getInt("id");
        String first_name=res.getString("first_name");
        String last_name=res.getString("last_name");
        String email=res.getString("email");
        String password=res.getString("password");
        String salt=res.getString("salt");
        boolean is_admin=res.getBoolean("is_admin");

        return new User(id,first_name,last_name,email,password,salt,is_admin);
    }

    public static int count() throws Exception {
        Connection conn = DbHelper.getConnection();
        ResultSet res = conn.createStatement().executeQuery("SELECT COUNT(*) FROM users");
        res.next();
        return res.getInt(1);
    }

}
