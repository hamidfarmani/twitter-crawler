package service;

import controller.TwitController;
import entity.Ttwit;
import entity.Ttype;
import entity.Tuser;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import util.Provider;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "twitterPU")
    EntityManager em;

    public void register(String fullname, String username, int typeID, String description) {
        Tuser user = new Tuser();

        try {
            Ttype type = em.find(Ttype.class, typeID);
            user.setFullname(fullname);
            user.setUsername(username);
            user.setTypeid(type);
            user.setDescription(description);
//            user.setId(2);
            System.out.println(description);
            em.persist(user);
            em.flush();
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
    }
    
    public List<Tuser> getAllUsers(){
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tuser.class));
        return em.createQuery(cq).getResultList();
    }
    
    public List<Tuser> searchTwitterUser(String username) {
        List<Tuser> usersList = new ArrayList();
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setTweetModeExtended(true); //Plus other options
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(Provider.getInstance().getAuthConsumerKey())
                    .setOAuthConsumerSecret(Provider.getInstance().getAuthConsumerSecret())
                    .setOAuthAccessToken(Provider.getInstance().getAuthAccessToken())
                    .setOAuthAccessTokenSecret(Provider.getInstance().getAuthAccessTokenSecret());
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            ResponseList<User> searchUsers = twitter.searchUsers(username, 2);
            
            
            for (User u : searchUsers) {
                Tuser uTuser = new Tuser();
                
                uTuser.setFullname(u.getName());
                
                uTuser.setUsername(u.getScreenName());
                usersList.add(uTuser);
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TwitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }
    
}
