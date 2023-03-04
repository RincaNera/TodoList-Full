package fr.m2i.todolistfull.resources;

import fr.m2i.todolistfull.database.DatabaseAccess;
import fr.m2i.todolistfull.database.TodoListAccess;
import fr.m2i.todolistfull.database.UserAccess;
import fr.m2i.todolistfull.models.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("user")
public class UserResource {

    @PUT
    @Path("createUser")
    public Response createUser(@FormParam("lastName") String lastNameUser, @FormParam("firstName") String firstNameUser) {
        UserAccess userAccess = new UserAccess(DatabaseAccess.getInstance());

        int idUser = userAccess.userAdd(lastNameUser, firstNameUser);
        if (idUser == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Couldn't create user").build();
        }
        User user = new User(idUser, lastNameUser, firstNameUser);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Path("getUserById")
    public Response getUser(@QueryParam("idUser") int idUser) {
        UserAccess user = new UserAccess(DatabaseAccess.getInstance());
        User user1 = user.getUserById(idUser);
        if (user1 != null) {
            return Response.status(Response.Status.OK).entity(user1).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("L'user n'existe pas ! ").build();
        }
    }

    @DELETE
    @Path("deleteUser")
    public Response deleteUser(@QueryParam("idUser") int idUser) {
        try {
            DatabaseAccess.getInstance().getConnection().setAutoCommit(false);
            TodoListAccess todoListAccess = new TodoListAccess(DatabaseAccess.getInstance());
            todoListAccess.deleteTodoByUser(idUser);
            UserAccess user = new UserAccess(DatabaseAccess.getInstance());
            boolean userSuccess = user.deleteUserById(idUser);
            if (userSuccess) {
                DatabaseAccess.getInstance().getConnection().commit();
                DatabaseAccess.getInstance().getConnection().setAutoCommit(true);
                return Response.status(Response.Status.OK).entity(true).build();
            } else {
                DatabaseAccess.getInstance().getConnection().rollback();
                DatabaseAccess.getInstance().getConnection().setAutoCommit(true);
                return Response.status(Response.Status.NOT_FOUND).entity("L'user n'a pas été supprimé ! ").build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PUT // POST c'est pour créer, PUT pour update
    @Path("updateUser")
    public Response updateUser(@FormParam("idUser") int idUser, @FormParam("lastNameUser") String lastNameUser, @FormParam("firstNameUser") String firstNameUser) {
        UserAccess user = new UserAccess(DatabaseAccess.getInstance());
        boolean userSuccess = user.userUpdate(idUser, lastNameUser, firstNameUser);
        if (userSuccess) {
            return Response.status(Response.Status.OK).entity(true).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("L'user n'a pas été mise à jour !").build();
        }
    }
}


