//package repositories;
//
//
//import Utils.DbHelper;
//import Utils.DateHelper;
//import models.Car;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class CarRepo {
//    public static int count() throws Exception {
//        Connection conn = DbHelper.getConnection();
//        ResultSet res = conn.createStatement().executeQuery("SELECT COUNT(*) FROM car");
//        res.next();
//        return res.getInt(1);
//    }
//
//    public static List<Car> getAll(int pageSize, int page) throws Exception {
//        ArrayList<Car> list = new ArrayList<>();
//
//        Connection conn = DbHelper.getConnection();
//        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM car ORDER BY id ASC LIMIT ? OFFSET ?");
//        stmt.setInt(1, pageSize);
//        stmt.setInt(2, pageSize * page);
//        ResultSet res = stmt.executeQuery();
//        while (res.next()) {
//            list.add(parseRes(res));
//        }
//        return list;
//    }
//
//    public static Car find(int id) throws Exception {
//        Connection conn = DbHelper.getConnection();
//        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM car WHERE id = ? LIMIT 1");
//        stmt.setInt(1, id);
//
//        ResultSet res = stmt.executeQuery();
//        if (!res.next()) return null;
//        return parseRes(res);
//    }
//
//    public static List<Car> find(String text) throws Exception {
//        Connection conn = DbHelper.getConnection();
//        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM car WHERE manufacture LIKE ? ORDER BY id ASC");
//        stmt.setString(1, text + "%");
//
//        ResultSet res = stmt.executeQuery();
//        ArrayList<Car> list = new ArrayList<>();
//        while (res.next()) {
//            list.add(parseRes(res));
//        }
//        return list;
//    }
//
//    public static Car create(Car model) throws Exception {
//        Connection conn = DbHelper.getConnection();
//        String query = "INSERT INTO car (publisher,manufacture,model,price_per_day,avg_fuel_km,transmission,speed_limit,type,seat_num,door_num,inserted_at,updated_at) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setString(1, model.getPublisher());
//        stmt.setInt(2, model.getManufacture());
//        stmt.setString(3, model.getModel());
//        stmt.setDouble(4, model.getPrice_per_day());
//        stmt.setDouble(5, model.getAvg_fuel_km());
//        stmt.setInt(6, model.getTransmission().getValue());
//        stmt.setDouble(7, model.getSpeed_limit());
//        stmt.setInt(8, model.getType().getValue());
//        stmt.setInt(9, model.getSeat_num());
//        stmt.setInt(10, model.getDoor_num());
//        stmt.setTimestamp(11, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
//        stmt.setTimestamp(12, java.sql.Timestamp.valueOf(DateHelper.toSqlFormat(model.getUpdated_at())));
//
//        if (stmt.executeUpdate() != 1)
//            throw new Exception("ERR_NO_ROW_CHANGE");
//
//        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM products ORDER BY createdAt DESC LIMIT 1");
//        res.next();
//        return parseRes(res);
//    }
//
//    public static Car update(Car model) throws Exception {
//        Connection conn = DbHelper.getConnection();
//        String query = "UPDATE products SET title = ?, description = ?, image = ?, price = ?, qty = ?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?";
//        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setString(1, model.getTitle());
//        stmt.setString(2, model.getDescription());
//        stmt.setString(3, model.getImage());
//        stmt.setDouble(4, model.getPrice());
//        stmt.setDouble(5, model.getQty());
//        stmt.setInt(6, model.getId());
//
//        if (stmt.executeUpdate() != 1)
//            throw new Exception("ERR_NO_ROW_CHANGE");
//
//        return find(model.getId());
//    }
//
//    public static boolean remove(int id) throws Exception {
//        String query = "DELETE FROM products WHERE id = ?";
//        PreparedStatement stmt = DbHelper.getConnection().prepareStatement(query);
//        stmt.setInt(1, id);
//        return stmt.executeUpdate() == 1;
//    }
//
//    private static Car parseRes(ResultSet res) throws Exception {
//        int id = res.getInt("id");
//        String title = res.getString("title");
//        String description = res.getString("description");
//        String image = res.getString("image");
//        double price = res.getDouble("price");
//        double qty = res.getDouble("qty");
//        Date createdAt = DateHelper.fromSql(res.getString("createdAt"));
//        Date updatedAt = DateHelper.fromSql(res.getString("updatedAt"));
//
//        return new Car(
//                id, title, description, image, price, qty, createdAt, updatedAt
//        );
//    }
//
//    public static EnumToString(Enum enum){
//
//    }
//}
