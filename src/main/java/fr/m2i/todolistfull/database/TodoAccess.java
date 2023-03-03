package fr.m2i.todolistfull.database;

import fr.m2i.todolistfull.models.Todo;
import fr.m2i.todolistfull.models.Urgency;
import fr.m2i.todolistfull.models.User;

import java.sql.*;

public class TodoAccess {
    private final DatabaseAccess db;
    private final String INSERT = "INSERT INTO Todo(titleTodo, descriptionTodo, dateTodo, idUrgency, idUser) VALUES(?, ?, ?, ?, ?)";
    private final String GETBYID = "SELECT idTodo, titleTodo, descriptionTodo, dateTodo, idUrgency, idUser, nameUrgency, lastNameUser, firstNameUser FROM Todo where idTodo = ? INNER JOIN Urgency " +
            "ON Urgency.idUrgency = Todo.idUrgency INNER JOIN User ON User.idUser= Todo.idUser";
    private final String DELETE = "DELETE FROM Todo WHERE idTodo= ?";
    private final String DELETEBYUSER = "DELETE FROM Todo WHERE idUser= ?";

    private final String UPDATE = "UPDATE Todo SET titleTodo= ?, descriptionTodo = ?, dateTodo = ? , idUrgency = ? , idUser = ? WHERE idTodo = ?";


    public TodoAccess(DatabaseAccess db) {
        this.db = db;
    }


    public int todoAdd(String titleTodo, String descriptionTodo, Date dateTodo, int idUrgency, int idUser) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, titleTodo);
            statement.setString(2, descriptionTodo);
            statement.setDate(3, dateTodo);
            statement.setInt(4, idUrgency);
            statement.setInt(5, idUser);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    public Todo getTodoById(int idTodo) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(GETBYID);
        ) {
            statement.setInt(1, idTodo);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Urgency urgency = new Urgency(result.getInt(5), result.getString(7));
                User user = new User(result.getInt(6), result.getString(8), result.getString(9));
                Todo Todo = new Todo(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDate(4),
                        urgency,
                        user
                );
                return Todo;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean deleteTodoById(int idTodo) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(DELETE);
        ) {
            statement.setInt(1, idTodo);
            int deletedLinesNumber = statement.executeUpdate();
            if (deletedLinesNumber > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean deleteTodoByUser(int idUser) {
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


    public int todoUpdate(int todoId, String titleTodo, String descriptionTodo, Date dateTodo, int idUrgency, int idUser) {
        try(
                PreparedStatement statement = db.getConnection().prepareStatement(UPDATE);
        ) {
            statement.setString(1, titleTodo);
            statement.setString(2, descriptionTodo);
            statement.setInt(6, todoId);
            statement.setDate(3, dateTodo );
            statement.setInt(4, idUrgency);
            statement.setInt(5, idUser);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}

