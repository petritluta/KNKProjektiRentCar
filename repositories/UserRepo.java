package repositories;

import Utils.DbHelper;
import com.mysql.cj.protocol.Resultset;
import models.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRepo {
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

        ResultSet rs = stmt.getGeneratedKeys();
        if(rs.next())
        {
            PreparedStatement getLastUser=conn.prepareStatement("SELECT * FROM users where id=?");
            getLastUser.setInt(1,rs.getInt(1));
            ResultSet res=getLastUser.executeQuery();
        }



        return null;
    }


    private static User parseRes(ResultSet res) throws Exception {
        int id =res.getInt("id");

        return null;
    }

}
