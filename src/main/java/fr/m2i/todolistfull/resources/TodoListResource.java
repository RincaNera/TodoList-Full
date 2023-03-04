package fr.m2i.todolistfull.resources;

import fr.m2i.todolistfull.database.DatabaseAccess;
import fr.m2i.todolistfull.database.TodoAccess;
import fr.m2i.todolistfull.database.TodoListAccess;
import fr.m2i.todolistfull.models.TodoList;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("todoList")
public class TodoListResource {
    @GET
    @Path("todoListByUser")

    public Response getTodoListByUser(@QueryParam("userId") int idUser){
        TodoListAccess todoListAccess = new TodoListAccess(DatabaseAccess.getInstance());
        TodoList todoList = todoListAccess.getTodoListByUser(idUser);
        if (todoList.getTodoList().size() != 0) {
            return Response.status(Response.Status.OK).entity(todoList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("L'utilisateur n'a pas de todo! ").build();
        }

    }
    @GET
    @Path("todoListByUrgency")
    public Response getTodoListByUrgency(@QueryParam("urgencyId") int idUrgency){
        TodoListAccess todoListAccess = new TodoListAccess(DatabaseAccess.getInstance());
        TodoList todoList = todoListAccess.getTodoListByUser(idUrgency);
        if (todoList.getTodoList().size() != 0) {
            return Response.status(Response.Status.OK).entity(todoList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Il n'y pas de todo pour ce type d'urgence ").build();
        }

    }
    @GET
    @Path("todoListByUrgencyAndByUser")
    public Response getTodoListByUrgencyAndByUser(@QueryParam("urgencyId") int idUrgency, @QueryParam("userId") int idUser){
        TodoListAccess todoListAccess = new TodoListAccess(DatabaseAccess.getInstance());
        TodoList todoListByUrgencyAndUser = todoListAccess.getTodoListByUrgencyAndByUser(idUrgency, idUser);

        if (todoListByUrgencyAndUser.getTodoList().size() != 0) {
            return Response.status(Response.Status.OK).entity(todoListByUrgencyAndUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Il n'y pas de todo pour cette recherche ").build();
        }

    }

}
