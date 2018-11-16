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
import entity.Ttwit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
import service.TwitService;
import util.enums.Status;

/**
 *
 * @author Hamid
 */
@Stateless
@Path("twits")
public class TwitController {

    @EJB
    private TwitService ts;

    @POST
    @Consumes({"application/json", "application/json"})
    public void create(Ttwit entity) {
//        super.create(entity);
    }

    @POST
    @Path("{username}/all")
    @Consumes({"application/json", "application/json"})
    public Status create(@PathParam("username") String username) {
        System.out.println("here");
        ts.registerTwits(username);
        return Status.OK;
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json", "application/json"})
    public void edit(@PathParam("id") BigDecimal id, Ttwit entity) {
//        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigDecimal id) {
//        super.remove(super.find(id));
    }

    @GET
    @Path("{userID}/all")
    @Produces({"application/json", "application/json"})
    public List<Ttwit> find(@PathParam("userID") Integer userID) {
        List<Ttwit> userTwits = ts.getUserTwits(userID);
        return userTwits;
    }

    @GET
    @Path("show")
    @Produces({"application/json", "application/json"})
    public List<Ttwit> searchUserTweetsOnline(@QueryParam("username")
            @DefaultValue("") String username) {
        List<Ttwit> userTwits = ts.showTweetsOnline(username);
        return userTwits;
    }

    @GET
    @Path("search")
    @Produces({"application/json", "application/json"})
    public List<Ttwit> findKeyword(@QueryParam("keyword")
            @DefaultValue("") String keyword,
            @QueryParam("since")
            @DefaultValue("") String since,
            @QueryParam("until")
            @DefaultValue("") String until) {
        List<Ttwit> tweets = ts.searchTweets(keyword, since, until);
        return tweets;
    }

    @GET
    @Path("search/year")
    @Produces({"application/json", "application/json"})
    public String keywordPerYear(@QueryParam("keyword")
            @DefaultValue("") String keyword,
            @QueryParam("since")
            @DefaultValue("") String since) {
        JSONObject resp = ts.tweetsPerYear(keyword, since);
        return resp.toString();
    }

    @GET
    @Path("trends")
    @Produces({"application/json", "application/json"})
    public String trends() {
        JSONObject tweets = ts.getDailyTrends();
        return tweets.toString();
    }

    @GET
    @Produces({"application/json", "application/json"})
    public List<Ttwit> findAll() {
//        return super.findAll();
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json", "application/json"})
    public List<Ttwit> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
