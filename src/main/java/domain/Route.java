package domain;

import java.io.Serializable;

public class Route implements Serializable {
    private Integer id;
    private String number;
    private String title;
    private Integer distance;
    private String status;
    private String departure;
    private String arrival;

    public Route() {
    }

    public Route(Integer id, String number, String title, Integer distance, String status, String departure, String arrival) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.distance = distance;
        this.status = status;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public static class RouteBuilder {
        private Integer id;
        private String number;
        private String title;
        private Integer distance;
        private String status;
        private String departure;
        private String arrival;

        public RouteBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public RouteBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public RouteBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public RouteBuilder setDistance(Integer distance) {
            this.distance = distance;
            return this;
        }

        public RouteBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public RouteBuilder setDeparture(String departure) {
            this.departure = departure;
            return this;
        }

        public RouteBuilder setArrival(String arrival) {
            this.arrival = arrival;
            return this;
        }

        public Route createRoute() {
            return new Route(id, number, title, distance, status, departure, arrival);
        }
    }
}
