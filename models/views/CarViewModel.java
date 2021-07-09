package models.views;

import javafx.beans.property.IntegerProperty;

import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import models.Car;
import Utils.DateHelper;
import models.Transmission;
import models.Type;

public class CarViewModel {
    private IntegerProperty id;
    private IntegerProperty publisher;
    private IntegerProperty manufacture;
    private StringProperty model;
    private DoubleProperty price_per_day;
    private DoubleProperty avg_fuel_km;
    private StringProperty transmission;
    private DoubleProperty speed_limit;
    private StringProperty type;
    private IntegerProperty seat_num;
    private IntegerProperty door_num;
    private StringProperty inserted_at;
    private StringProperty updated_at;
    private StringProperty car_img;

    public CarViewModel() {
        id = new SimpleIntegerProperty();
        publisher = new SimpleIntegerProperty();
        manufacture = new SimpleIntegerProperty();
        model = new SimpleStringProperty();
        price_per_day = new SimpleDoubleProperty();
        avg_fuel_km = new SimpleDoubleProperty();
        transmission = new SimpleStringProperty();
        speed_limit = new SimpleDoubleProperty();
        type = new SimpleStringProperty();
        seat_num = new SimpleIntegerProperty();
        door_num = new SimpleIntegerProperty();
        inserted_at = new SimpleStringProperty();
        updated_at = new SimpleStringProperty();
        car_img = new SimpleStringProperty();
    }

    public CarViewModel(Car model) {
        this();
        this.setId(model.getId());
        this.setPublisher(model.getPublisher());
        this.setManufacture(model.getManufacture());
        this.setmodel(model.getModel());
        this.setPrice_per_day(model.getPrice_per_day());
        this.setAvg_fuel_km(model.getAvg_fuel_km());
        this.setTransmission(model.getTransmission().name());
        this.setSpeed_limit(model.getSpeed_limit());
        this.setType(model.getType().name());
        this.setSeat_num(model.getSeat_num());
        this.setDoor_num(model.getDoor_num());
        this.setInserted_at(model.getInserted_at());
        this.setUpdated_at(model.getUpdated_at());
        this.setCar_img(model.getCar_img());
    }

    public IntegerProperty idProperty() {
        return id;
    }
    public int getId() {
        return id.getValue();
    }
    public void setId(int value) {
        id.setValue(value);
    }

    public IntegerProperty publisherProperty() {
        return publisher;
    }
    public int getPublisher() { return publisher.getValue(); }
    public void setPublisher(int publisher) { this.publisher.setValue(publisher); }

    public IntegerProperty manufactureProperty() {
        return manufacture;
    }
    public int getManufacture() { return manufacture.getValue(); }
    public void setManufacture(int manufacture) { this.manufacture.setValue(manufacture); }

    public StringProperty modelProperty() {
        return model;
    }
    public String getmodel() {
        return model.getValue();
    }
    public void setmodel(String value) {
        model.setValue(value);
    }

    public DoubleProperty price_per_dayProperty() {
        return price_per_day;
    }

    public double getPrice_per_day() {
        return price_per_day.get();
    }

    public void setPrice_per_day(double price_per_day) {
        this.price_per_day.set(price_per_day);
    }

    public DoubleProperty avg_fuel_kmProperty() {
        return avg_fuel_km;
    }

    public double getAvg_fuel_km() {
        return avg_fuel_km.get();
    }

    public void setAvg_fuel_km(double avg_fuel_km) {
        this.avg_fuel_km.set(avg_fuel_km);
    }

    public StringProperty transmissionProperty() {
        return transmission;
    }

    public String getTransmission() {
        return transmission.get();
    }

    public void setTransmission(String transmission) {
        this.transmission.set(transmission);
    }

    public DoubleProperty speed_limitProperty() {
        return speed_limit;
    }

    public double getSpeed_limit() {
        return speed_limit.get();
    }

    public void setSpeed_limit(double speed_limit) {
        this.speed_limit.set(speed_limit);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public IntegerProperty seat_numProperty() {
        return seat_num;
    }

    public int getSeat_num() {
        return seat_num.get();
    }

    public void setSeat_num(int seat_num) {
        this.seat_num.set(seat_num);
    }

    public IntegerProperty door_numProperty() {
        return door_num;
    }

    public int getDoor_num() {
        return door_num.get();
    }

    public void setDoor_num(int door_num) {
        this.door_num.set(door_num);
    }

    public StringProperty inserted_atProperty() {
        return inserted_at;
    }

    public Date getInserted_at() {
        try {
            return DateHelper.fromSql(inserted_at.getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public void setInserted_at(String value) {
        inserted_at.setValue(value);
    }

    public void setInserted_at(Date value) {
        inserted_at.setValue(DateHelper.toSqlFormat(value));
    }


    public StringProperty updated_atProperty() {
        return updated_at;
    }

    public Date getUpdated_at() {
        try {
            return DateHelper.fromSql(updated_at.getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public void setUpdated_at(String value) {
        updated_at.setValue(value);
    }

    public void setUpdated_at(Date value) {
        updated_at.setValue(DateHelper.toSqlFormat(value));
    }

    public String getCar_img() {
        return car_img.get();
    }

    public void setCar_img(String car_img) {
        this.car_img.set(car_img);
    }

    public Car getModel() {
        return new Car(getId(), getPublisher(), getManufacture(), getmodel(), getPrice_per_day(), getAvg_fuel_km(), Transmission.valueOf(getTransmission()), getSpeed_limit(), Type.valueOf(getType()), getSeat_num(), getDoor_num(), getInserted_at(), getUpdated_at(), getCar_img());
    }
}
