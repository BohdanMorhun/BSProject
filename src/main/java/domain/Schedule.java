package domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Schedule implements Serializable {
    private Integer id;
    private String departure;
    private String arrival;

    public Schedule() {
    }

    public Schedule(Integer id, String departure, String arrival) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public static class ScheduleBuilder {
        private Integer id;
        private String departure;
        private String arrival;

        public ScheduleBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ScheduleBuilder setDeparture(String departure) {
            this.departure = departure;
            return this;
        }

        public ScheduleBuilder setArrival(String arrival) {
            this.arrival = arrival;
            return this;
        }

        public Schedule createSchedule() {
            return new Schedule(id, departure, arrival);
        }
    }
}
