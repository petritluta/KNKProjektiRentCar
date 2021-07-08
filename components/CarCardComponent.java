package components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import models.Car;
import models.User;

public class CarCardComponent {
//    public Node getContent(Car car, EventHandler<ActionEvent> editHandler, EventHandler<ActionEvent> deleteHandler, EventHandler<ActionEvent> activeHandler) throws Exception {
    public Node getContent(EventHandler<ActionEvent> editHandler, EventHandler<ActionEvent> deleteHandler, EventHandler<ActionEvent> activeHandler) throws Exception {

    FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/partials/car-card.fxml"));
        Node node = loader.load();

//        UserCardController controller = loader.getController();
//        controller.setUser(user);
//        controller.setOnEditAction(editHandler);
//        controller.setOnDeleteAction(deleteHandler);
//        controller.setOnActiveAction(activeHandler);

        return node;
    }
}
