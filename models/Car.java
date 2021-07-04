package models;

import java.util.Date;

public class Car {
        private int id;
        private String publisher;
        private int manufacture;
        private String model;
        private double price_per_day;
        private double avg_fuel_km;
        private Transmission transmission;
        private double speed_limit;
        private Type type;
        private int seat_num;
        private int door_num;
        private Date inserted_at;
        private Date updated_at;
        private String car_img;

        public Car(  int id,String publisher, int manufacture, String model, double price_per_day, double avg_fuel_km, Transmission transmission, double speed_limit, Type type, int seat_num, int door_num,Date inserted_at, Date updated_at,String car_img) {
            this.id=id;
            this.publisher=publisher;
            this.manufacture=manufacture;
            this.model=model;
            this.price_per_day=price_per_day;
            this.avg_fuel_km=avg_fuel_km;
            this.transmission=transmission;
            this.speed_limit=speed_limit;
            this.type=type;
            this.seat_num=seat_num;
            this.door_num=door_num;
            this.car_img=car_img;
        }
        public Car() {
            this(-1,"",0,"",0,0,Transmission.Auto,0,Type.SUV,0,0,new Date(), new Date(),"");
        }
        public int getId() {
            return id;
        }

        public String getPublisher() {
            return publisher;
        }

    public int getManufacture() {
        return manufacture;
    }

    public void setManufacture(int manufacture) {
        this.manufacture = manufacture;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(double price_per_day) {
        this.price_per_day = price_per_day;
    }

    public double getAvg_fuel_km() {
        return avg_fuel_km;
    }

    public void setAvg_fuel_km(double avg_fuel_km) {
        this.avg_fuel_km = avg_fuel_km;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public double getSpeed_limit() {
        return speed_limit;
    }

    public void setSpeed_limit(double speed_limit) {
        this.speed_limit = speed_limit;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public int getDoor_num() {
        return door_num;
    }
    public void setDoor_num(int door_num) {
        this.door_num = door_num;
    }

    public Date getInserted_at() {
        return inserted_at;
    }

    public void setInserted_at(Date inserted_at) {
        this.inserted_at = inserted_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getCar_img() {
        return car_img;
    }

    public void setCar_img(String car_img) {
        this.car_img = car_img;
    }
}
