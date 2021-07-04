package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Utils {

    public static int getRowCount(ResultSet rs) throws SQLException {
        int count =0;
        while(rs.next())
        {
            count++;
        }
        return count;
    }


}
