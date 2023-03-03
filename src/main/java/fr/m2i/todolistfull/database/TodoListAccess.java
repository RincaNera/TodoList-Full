package fr.m2i.todolistfull.database;

public class TodoListAccess {
    private final DatabaseAccess db;


    private final String GETBYUSER = "SELECT idTodo, titleTodo, descriptionTodo, dateTodo, idUrgency, idUser, nameUrgency, lastNameUser, firstNameUser " +
            "FROM Todo WHERE idUser = ? INNER JOIN Urgency " +
            "ON Urgency.idUrgency = Todo.idUrgency INNER JOIN User ON User.idUser= Todo.idUser";
    private final String GETBYURGENCY = "SELECT idTodo, titleTodo, descriptionTodo, dateTodo, idUrgency, idUser, nameUrgency, lastNameUser, firstNameUser " +
            "FROM Todo WHERE idUrgency = ? INNER JOIN Urgency " +
            "ON Urgency.idUrgency = Todo.idUrgency INNER JOIN User ON User.idUser= Todo.idUser";
    private final String GETBYURGENCYANDUSER = "SELECT idTodo, titleTodo, descriptionTodo, dateTodo, idUrgency, idUser, nameUrgency, lastNameUser, firstNameUser " +
            "FROM Todo WHERE idUrgency = ? , idUser = ? INNER JOIN Urgency" +
            "ON Urgency.idUrgency = Todo.idUrgency INNER JOIN User ON User.idUser= Todo.idUser";

    public TodoListAccess(DatabaseAccess db) {
        this.db = db;
    }



}
