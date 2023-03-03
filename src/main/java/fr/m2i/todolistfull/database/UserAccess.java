package fr.m2i.todolistfull.database;

import fr.m2i.todolistfull.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAccess {
    private final DatabaseAccess db;
    private final String INSERT = "INSERT INTO User(lastNameUser, firstNameUser) VALUES(?, ?)";
    private final String GETBYID = "SELECT idUser, lastNameUser, firstNameUser FROM User where idUser = ?";
    private final String DELETE = "DELETE FROM User WHERE idUser= ?";
    private final String UPDATE = "UPDATE User SET lastNameUser = ? , firstNameUser = ? WHERE idUser = ?";

    private final String GETBYNAME = "SELECT idUser FROM User WHERE firstNameUser =?, lastNameUser = ? ";


    public UserAccess(DatabaseAccess db) {
        this.db = db;
    }


    public int userAdd(String lastNameUser, String firstNameUser) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, lastNameUser);
            statement.setString(2, firstNameUser);
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


    public User getUserById(int idUser) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(GETBYID);
        ) {
            statement.setInt(1, idUser);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                User user = new User(result.getInt(1), result.getString(2), result.getString(3));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean deleteUserById(int idUser) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(DELETE);
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

    public int userUpdate(int userId, String lastNameUser, String firstNameUser) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(UPDATE);
        ) {
            statement.setString(1, lastNameUser);
            statement.setString(2, firstNameUser);
            statement.setInt(3, userId);
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

    public int getUserByName(String lastNameUser, String firstNameUser){
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(GETBYID);
        ) {
            statement.setString(1, lastNameUser);
            statement.setString(2, firstNameUser);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int user = result.getInt(1);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}

