package by.bookstore.entity;

import java.util.Objects;

public class User {

    private long id;
    private String name;
    private String login;
    private String password;
    private String picture;
    private TypeOfUser typeOfUser;


    public User(String name, String login, String password, String picture, TypeOfUser typeOfUser) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.picture = picture;
        this.typeOfUser = typeOfUser;
    }

    public User(long id, String name, String login, String password, String picture, TypeOfUser typeOfUser) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.picture = picture;
        this.typeOfUser = typeOfUser;
    }

    public User(long id, String name, String picture, TypeOfUser typeOfUser) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.typeOfUser = typeOfUser;
    }

    public User(long id) {
        this.id = id;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(TypeOfUser typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
