package fr.m2i.todolistfull.resources;

import fr.m2i.todolistfull.database.DatabaseAccess;

import fr.m2i.todolistfull.database.TodoAccess;
import fr.m2i.todolistfull.database.UserAccess;
import fr.m2i.todolistfull.models.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.sql.Date;
import java.util.ArrayList;

@Path("todo")
public class TodoResource {

    @POST
    @Path("createTodoWithId")
    public Response addTodoById(@FormParam("titleTodo") String titleTodo,
                                @FormParam("descriptionTodo") String descriptionTodo,
                                @FormParam("dateTodo") Date dateTodo,
                                @FormParam("urgencyId") int idUrgency,
                                @FormParam("userId") int idUser) {
        TodoAccess todoAccess = new TodoAccess(DatabaseAccess.getInstance());
        int todo = todoAccess.todoAdd(titleTodo, descriptionTodo, dateTodo, idUrgency, idUser);
        if (todo != 0) {
            return Response.status(Response.Status.OK).entity(todo).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Le todo n'a pas été crée! ").build();
        }

    }

    @POST
    @Path("createTodoWithUser")
    public Response addTodoByUserName(@FormParam("titleTodo") String titleTodo,
                                      @FormParam("descriptionTodo") String descriptionTodo,
                                      @FormParam("dateTodo") Date dateTodo,
                                      @FormParam("urgencyId") int idUrgency,
                                      @FormParam("userLastNameTodo") String lastName,
                                      @FormParam("userFirstNameTodo") String firstName) {
        TodoAccess todoAccess = new TodoAccess(DatabaseAccess.getInstance());
        UserAccess userAccess = new UserAccess(DatabaseAccess.getInstance());
        int result = userAccess.getUserByName(lastName, firstName);
        if (result > 0) {
            int todo = todoAccess.todoAdd(titleTodo, descriptionTodo, dateTodo, idUrgency, result);
            return Response.status(Response.Status.OK).entity(todo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("L'utilisateur spécifié n'existe pas ! ").build();
        }
    }

    @PUT
    @Path("updateTodoWithId")
    public Response updateTodoById(@FormParam("id") int idTodo,
                                   @FormParam("titleTodo") String titleTodo,
                                   @FormParam("descriptionTodo") String descriptionTodo,
                                   @FormParam("dateTodo") Date dateTodo,
                                   @FormParam("urgencyId") int idUrgency,
                                   @FormParam("userId") int idUser) {
        TodoAccess todoAccess = new TodoAccess(DatabaseAccess.getInstance());
        boolean todo = todoAccess.todoUpdate(idTodo, titleTodo, descriptionTodo, dateTodo, idUrgency, idUser);
        if (todo) {
            return Response.status(Response.Status.OK).entity(todo).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Le todo n'a pas été modifié ! ").build();
        }

    }

    @DELETE
    @Path("deleteTodoWithId")
    public Response deleteTodoById(@QueryParam("id") int idTodo)
    {
        TodoAccess todoAccess = new TodoAccess(DatabaseAccess.getInstance());
        boolean todo = todoAccess.deleteTodoById(idTodo);
        if (todo) {
            return Response.status(Response.Status.OK).entity(todo).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Le todo n'a pas été supprimé ! ").build();
        }

    }


}
