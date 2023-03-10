package fr.m2i.todolistfull.database;

import fr.m2i.todolistfull.models.Todo;
import fr.m2i.todolistfull.models.TodoList;
import fr.m2i.todolistfull.models.Urgency;
import fr.m2i.todolistfull.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoListAccess {
    private final DatabaseAccess db;


    private final String GETBYUSER = "SELECT idTodo, titleTodo, descriptionTodo, dateTodo, Todo.idUrgency, Todo.idUser, nameUrgency, lastNameUser, firstNameUser " +
            "FROM Todo INNER JOIN Urgency ON Urgency.idUrgency = Todo.idUrgency INNER JOIN User ON User.idUser = Todo.idUser WHERE Todo.idUser = ?";
    private final String GETBYURGENCY = "SELECT idTodo, titleTodo, descriptionTodo, dateTodo, Todo.idUrgency, Todo.idUser, nameUrgency, lastNameUser, firstNameUser " +
            "FROM Todo INNER JOIN Urgency ON Urgency.idUrgency = Todo.idUrgency INNER JOIN User ON User.idUser = Todo.idUser WHERE Todo.idUrgency = ? ";
    private final String GETBYURGENCYANDUSER = "SELECT idTodo, titleTodo, descriptionTodo, dateTodo, Todo.idUrgency, Todo.idUser, nameUrgency, lastNameUser, firstNameUser " +
            "FROM Todo INNER JOIN Urgency ON Urgency.idUrgency = Todo.idUrgency INNER JOIN User ON User.idUser = Todo.idUser WHERE Todo.idUrgency = ? AND Todo.idUser = ? ";

    private final  String DELETEBYUSER  = "DELETE FROM Todo WHERE idUser = ?";

    public TodoListAccess(DatabaseAccess db) {
        this.db = db;
    }

    public TodoList getTodoListByUser(int idUser) {
        ArrayList<Todo> toDoeslist = new ArrayList<>();
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(GETBYUSER);
        ) {
            statement.setInt(1, idUser);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Urgency urgency = new Urgency(result.getInt(5), result.getString(7));
                User user = new User(result.getInt(6), result.getString(8), result.getString(9));
                Todo todo = new Todo(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDate(4),
                        urgency,
                        user
                );
                toDoeslist.add(todo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new TodoList(toDoeslist);
    }

    public TodoList getTodoListByUrgency(int idUrgency) {
        ArrayList<Todo> toDoeslist = new ArrayList<>();
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(GETBYURGENCY);
        ) {
            statement.setInt(1, idUrgency);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Urgency urgency = new Urgency(result.getInt(5), result.getString(7));
                User user = new User(result.getInt(6), result.getString(8), result.getString(9));
                Todo todo = new Todo(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDate(4),
                        urgency,
                        user
                );
                toDoeslist.add(todo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new TodoList(toDoeslist);
    }

    public TodoList getTodoListByUrgencyAndByUser(int idUrgency, int idUser) {
        ArrayList<Todo> toDoeslist = new ArrayList<>();
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(GETBYURGENCYANDUSER);
        ) {
            statement.setInt(1, idUrgency);
            statement.setInt(2, idUser);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Urgency urgency = new Urgency(result.getInt(5), result.getString(7));
                User user = new User(result.getInt(6), result.getString(8), result.getString(9));
                Todo todo = new Todo(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDate(4),
                        urgency,
                        user
                );
                toDoeslist.add(todo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new TodoList(toDoeslist);
    }

    public boolean deleteTodoByUser(int idUser){
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(DELETEBYUSER);
        ) {
            statement.setInt(1, idUser);
            int deletedLinesNumber = statement.executeUpdate();
            if (deletedLinesNumber > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}


