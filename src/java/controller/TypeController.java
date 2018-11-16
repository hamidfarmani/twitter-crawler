package controller;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import entity.Ttype;
import javax.ejb.EJB;
import javax.json.JsonObject;
import service.TypeService;
import util.enums.Status;

/**
 *
 * @author Hamid
 */
@Stateless
@Path("types")
public class TypeController {

    @EJB
    private TypeService ts;

    @POST
    @Consumes({"application/json", "application/json"})
    public Status create(JsonObject req) {
        String name = req.getString("name");
        String description = req.getString("description");

        ts.register(name, description);
        return Status.OK;
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json", "application/json"})
    public Status edit(@PathParam("id") Integer id, JsonObject req) {
        String name = req.getString("name");
        String description = req.getString("description");

        ts.edit(id, name, description);
        return Status.OK;
    }

    @DELETE
    @Path("{id}")
    public Status remove(@PathParam("id") Integer id) {
        ts.remove(id);
        return Status.OK;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json", "application/json"})
    public Ttype find(@PathParam("id") Integer id) {
        Ttype type = ts.find(id);
        return type;
    }

    @GET
    @Produces({"application/json", "application/json"})
    public List<Ttype> findAll() {
        return ts.getAllTypes();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json", "application/json"})
    public List<Ttype> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
        return null;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
//        return String.valueOf(super.count());
        return null;
    }

}
