package domain;


import java.io.Serializable;
import java.sql.Date;

public class Bus implements Serializable {
    private Integer id;
    private String plate;
    private String model;
    private Integer mileage;
    private Date inspection;
    private Integer consumption;
    private Date release;
    private String status;
    private Integer seats;
    private Schedule schedule;
    private Route route;

    public Bus() {
    }

    public Bus(Integer id, String plate, String model, Integer mileage, Date inspection, Integer consumption, Date release, String status, Integer seats, Schedule schedule, Route route) {
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.mileage = mileage;
        this.inspection = inspection;
        this.consumption = consumption;
        this.release = release;
        this.status = status;
        this.seats = seats;
        this.schedule = schedule;
        this.route = route;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Date getInspection() {
        return inspection;
    }

    public void setInspection(Date inspection) {
        this.inspection = inspection;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public static class BusBuilder {
        private Integer id;
        private String plate;
        private String model;
        private Integer mileage;
        private Date inspection;
        private Integer consumption;
        private Date release;
        private String status;
        private Integer seats;
        private Schedule schedule;
        private Route route;

        public BusBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public BusBuilder setPlate(String plate) {
            this.plate = plate;
            return this;
        }

        public BusBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder setMileage(Integer mileage) {
            this.mileage = mileage;
            return this;
        }

        public BusBuilder setInspection(Date inspection) {
            this.inspection = inspection;
            return this;
        }

        public BusBuilder setConsumption(Integer consumption) {
            this.consumption = consumption;
            return this;
        }

        public BusBuilder setRelease(Date release) {
            this.release = release;
            return this;
        }

        public BusBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public BusBuilder setSeats(Integer seats) {
            this.seats = seats;
            return this;
        }

        public BusBuilder setSchedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public BusBuilder setRoute(Route route) {
            this.route = route;
            return this;
        }

        public Bus createBus() {
            return new Bus(id, plate, model, mileage, inspection, consumption, release, status, seats, schedule, route);
        }
    }
}
