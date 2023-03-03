package fr.m2i.todolistfull.models;

public class User {
    private int idUser;
    private String lastNameUser;
    private String firstNameUser;


    public User(int idUser, String lastNameUser, String firstNameUser) {
        this.idUser = idUser;
        this.lastNameUser = lastNameUser;
        this.firstNameUser = firstNameUser;
    }

    public User(){

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getFirstNameUser() {
        return firstNameUser;
    }

    public void setFirstNameUser(String firstNameUser) {
        this.firstNameUser = firstNameUser;
    }
}
