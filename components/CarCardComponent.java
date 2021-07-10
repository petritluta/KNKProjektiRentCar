package components;

import controllers.partials.CarCardController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import models.Car;
import models.User;

public class CarCardComponent {
    public Node getContent(Car car, EventHandler<ActionEvent> editHandler, EventHandler<ActionEvent> deleteHandler,EventHandler<ActionEvent> showHandler) throws Exception {

    FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/partials/car-card.fxml"));
        Node node = loader.load();

        CarCardController controller = loader.getController();
        controller.setCar(car);

        controller.setOnEditAction(editHandler);
        controller.setOnDeleteAction(deleteHandler);
        controller.setOnShowButton(showHandler);

        return node;
    }
}
