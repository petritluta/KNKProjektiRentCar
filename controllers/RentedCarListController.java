package controllers;

import Utils.DbHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RentedCarListController  extends  ChildController{
    @FXML
    private TableView tableView;

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        try {
            this.loadRentedCars();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRentedCars() throws Exception {
        Connection conn=DbHelper.getConnection();
        ResultSet res=conn.prepareStatement("SELECT * FROM rentedcar INNER JOIN car on rentedcar.car = car.id").executeQuery();
        while(res.next())
        {
            String fullCarName=res.getString("manufacture")+" "+res.getString("model");
            TableColumn id=new TableColumn("ID");
            TableColumn person=new TableColumn("Person id");
            TableColumn car=new TableColumn("Car");
            TableColumn sDate=new TableColumn("Start Date");
            TableColumn eDate=new TableColumn("End Date");

            id.setCellValueFactory(new PropertyValueFactory<>(res.getString(1)));
            person.setCellValueFactory(new PropertyValueFactory<>(res.getString("person_id")));
            car.setCellValueFactory(new PropertyValueFactory<>(fullCarName));
            sDate.setCellValueFactory(new PropertyValueFactory<>(res.getString("start_date")));
            eDate.setCellValueFactory(new PropertyValueFactory<>(res.getString("end_date")));

            tableView.getColumns().addAll(id,person,car,sDate,eDate);

            System.out.println(res.getInt(1)+" "+fullCarName);

        }
    }
}
