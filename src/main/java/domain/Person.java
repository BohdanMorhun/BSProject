package domain;

import java.io.Serializable;
import java.sql.Date;

public class Person implements Serializable {
    private Integer id;
    private String name;
    private String surname;
    private Date birth;
    private User user;

    public Person() {
    }

    public Person(Integer id, String name, String surname, Date birth, User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
