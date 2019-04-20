package domain;

import java.sql.Date;

public class Admin extends Person{
    private String degree;
    private Date graduation;

    public Admin() {
    }

    public Admin(Integer id, String name, String surname, Date birth, User user, String degree, Date graduation) {
        super(id, name, surname, birth, user);
        this.degree = degree;
        this.graduation = graduation;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getGraduation() {
        return graduation;
    }

    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    public static class AdminBuilder {
        private Integer id;
        private String name;
        private String surname;
        private Date birth;
        private User user;
        private String degree;
        private Date graduation;

        public AdminBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public AdminBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AdminBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public AdminBuilder setBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public AdminBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public AdminBuilder setDegree(String degree) {
            this.degree = degree;
            return this;
        }

        public AdminBuilder setGraduation(Date graduation) {
            this.graduation = graduation;
            return this;
        }

        public Admin createAdmin() {
            return new Admin(id, name, surname, birth, user, degree, graduation);
        }
    }
}
