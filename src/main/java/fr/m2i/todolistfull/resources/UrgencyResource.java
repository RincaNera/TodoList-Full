package fr.m2i.todolistfull.resources;

import fr.m2i.todolistfull.database.DatabaseAccess;
import fr.m2i.todolistfull.database.TodoListAccess;
import fr.m2i.todolistfull.database.UrgencyAccess;
import fr.m2i.todolistfull.models.TodoList;
import fr.m2i.todolistfull.models.Urgency;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("urgency")
public class UrgencyResource {

    @PUT
    @Path("createUrgency")
    public Response createUrgency(@FormParam("name") String nameUrgency) {
        UrgencyAccess urgencyAccess = new UrgencyAccess(DatabaseAccess.getInstance());

        int idUrgency = urgencyAccess.urgencyAdd(nameUrgency);
        if (idUrgency == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Couldn't create urgency").build();
        }
        Urgency urgency = new Urgency(idUrgency, nameUrgency);
        return Response.status(Response.Status.CREATED).entity(urgency).build();
    }

    @GET
    @Path("getUrgencyById")
    public Response getUrgency(@QueryParam("idUrgency") int idUrgency) {
            UrgencyAccess urgency = new UrgencyAccess(DatabaseAccess.getInstance());
            Urgency urgency1 = urgency.getUrgencyById(idUrgency);
            if (urgency1 != null){
                return Response.status(Response.Status.OK).entity(urgency1).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("L'urgence n'existe pas ! ").build();
            }
    }

    @DELETE
    @Path("deleteUrgency")
    public Response deleteUrgency(@QueryParam("idUrgency") int idUrgency) {
        TodoListAccess todoListAccess = new TodoListAccess(DatabaseAccess.getInstance());
        TodoList todoListToDelete = todoListAccess.getTodoListByUrgency(idUrgency);
        if (todoListToDelete.getTodoList().size() > 0){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Il existe encore des todo pour cette urgence").build();
        }
        UrgencyAccess urgency = new UrgencyAccess(DatabaseAccess.getInstance());
        boolean urgencySuccess = urgency.deleteUrgencyById(idUrgency);
        if (urgencySuccess){
            return Response.status(Response.Status.OK).entity(true).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("L'urgence n'a pas été supprimée ! ").build();
        }
    }
    @PUT // POST c'est pour créer, PUT pour update
    @Path("updateUrgency")
    public Response updateUrgency(@FormParam("idUrgency") int idUrgency, @FormParam("nameUrgency") String nameUrgency){
        UrgencyAccess urgency = new UrgencyAccess(DatabaseAccess.getInstance());
        boolean urgencySuccess = urgency.urgencyUpdate(idUrgency, nameUrgency);
        if (urgencySuccess){
            return Response.status(Response.Status.OK).entity(true).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("L'urgence n'a pas été mise à jour !").build();
        }
    }
}


