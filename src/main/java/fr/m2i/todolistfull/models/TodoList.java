package fr.m2i.todolistfull.models;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List <Todo> todoList;

    public TodoList() {
        todoList = new ArrayList<>();
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
