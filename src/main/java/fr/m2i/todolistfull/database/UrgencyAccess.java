package fr.m2i.todolistfull.database;

import fr.m2i.todolistfull.models.Urgency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UrgencyAccess {
    private final DatabaseAccess db;


    private final String INSERT = "INSERT INTO Urgency(nameUrgency) VALUES(?)";
    private final String GETBYID = "SELECT idUrgency, nameUrgency FROM Urgency where idUrgency = ?";
    private final String DELETE = "DELETE FROM Urgency WHERE idUrgency= ?";

    private final String UPDATE = "UPDATE Urgency SET nameUrgency = ? WHERE idUrgency = ?";


    public UrgencyAccess(DatabaseAccess db) {
        this.db = db;
    }


    public int urgencyAdd(String urgencyName) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, urgencyName);
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


    public Urgency getUrgencyById(int idUrgency) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(GETBYID);
        ) {
            statement.setInt(1, idUrgency);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Urgency urgency = new Urgency(result.getInt(1), result.getString(2));
                return urgency;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean deleteUrgencyById(int idUrgency) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(DELETE);
        ) {
            statement.setInt(1, idUrgency);
            int deletedLinesNumber = statement.executeUpdate();
            if (deletedLinesNumber > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean urgencyUpdate(int urgencyId, String urgencyName) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(UPDATE);
        ) {
            statement.setString(1, urgencyName);
            statement.setInt(2, urgencyId);
            int updatedLinesNumber = statement.executeUpdate();
            if (updatedLinesNumber > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }
}
