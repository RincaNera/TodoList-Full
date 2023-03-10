package fr.m2i.todolistfull.models;

import java.sql.Date;

public class Todo {
    private int idTodo;
    private String titleTodo;
    private String descriptionTodo;
    private Date dateTodo;

    private Urgency urgency;
    private User user;


    public Todo(int idTodo, String titleTodo, String descriptionTodo, Date dateTodo, Urgency urgency, User user) {
        this.idTodo = idTodo;
        this.titleTodo = titleTodo;
        this.descriptionTodo = descriptionTodo;
        this.dateTodo = dateTodo;
        this.urgency = urgency;
        this.user = user;
    }

    public Todo() {
    }

    public int getIdTodo() {
        return idTodo;
    }

    public void setIdTodo(int idTodo) {
        this.idTodo = idTodo;
    }

    public String getTitleTodo() {
        return titleTodo;
    }

    public void setTitleTodo(String titleTodo) {
        this.titleTodo = titleTodo;
    }

    public String getDescriptionTodo() {
        return descriptionTodo;
    }

    public void setDescriptionTodo(String descriptionTodo) {
        this.descriptionTodo = descriptionTodo;
    }

    public Date getDateTodo() {
        return dateTodo;
    }

    public void setDateTodo(Date dateTodo) {
        this.dateTodo = dateTodo;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
