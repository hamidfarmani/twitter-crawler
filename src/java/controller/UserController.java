/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Ttwit;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import entity.Tuser;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import service.UserService;
import util.enums.Status;

/**
 *
 * @author Hamid
 */
@Path("users")
public class UserController {

//    @PersistenceContext(unitName = "twitterPU")
//    private EntityManager em;
    @EJB
    private UserService us;

    @POST
    @Consumes({"application/json", "application/json"})
    public Status create(JsonObject req) {
        String fullname = req.getString("fullname");
        String username = req.getString("username");
        String description = req.getString("description");
        String typeID = req.getString("typeID");

        us.register(fullname, username, Integer.valueOf(typeID), description);
        return Status.OK;
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json", "application/json"})
    public void edit(@PathParam("id") BigDecimal id, Tuser entity
    ) {
//        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigDecimal id
    ) {
//        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json", "application/json"})
    public Tuser find(@PathParam("id") BigDecimal id
    ) {
//        return super.find(id);
        return null;
    }

    @GET
    @Produces({"application/json", "application/json"})
    public List<Tuser> findAll() {
        return us.getAllUsers();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json", "application/json"})
    public List<Tuser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to
    ) {
//        return super.findRange(new int[]{from, to});
        return null;
    }

    @GET
    @Path("show")
    @Produces({"application/json", "application/json"})
    public List<Tuser> searchUserTweetsOnline(@QueryParam("name")
            @DefaultValue("") String name) {
        List<Tuser> userTwits = us.searchTwitterUser(name);
        return userTwits;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
//        return String.valueOf(super.count());
        return null;
    }

//    @Override
    protected EntityManager getEntityManager() {
        return null;
    }

}
