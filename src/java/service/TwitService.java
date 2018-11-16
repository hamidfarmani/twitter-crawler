package service;

import controller.TwitController;
import entity.Ttwit;
import entity.Ttype;
import entity.Tuser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.Location;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import util.Provider;

@Stateless
public class TwitService {

    @PersistenceContext(unitName = "twitterPU")
    EntityManager em;

    public void register(String description, Date creationDate, Integer userID) {
        Ttwit twit = new Ttwit();

        try {
            Tuser user = em.find(Tuser.class, userID);
            twit.setCreationdate(creationDate);
            twit.setDescription(description);
            twit.setUserid(user);
            em.persist(twit);
            em.flush();
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
    }

    public List<Ttwit> showTweetsOnline(String username) {
        List<Ttwit> tweetsList = new ArrayList<Ttwit>();
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

            ResponseList<Status> statuses = twitter.getUserTimeline(username, new Paging(1, 200));
            for (Status status : statuses) {
                Ttwit t = new Ttwit();
                t.setCreationdate(status.getCreatedAt());
                t.setDescription(status.getText());
                tweetsList.add(t);
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TwitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tweetsList;
    }

    public void registerTwits(String username) {
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

            ResponseList<Status> statuses = twitter.getUserTimeline(username, new Paging(1, 200));
            Tuser user = (Tuser) em.createNamedQuery("Tuser.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
            for (Status status : statuses) {
                Ttwit t = new Ttwit();
                t.setCreationdate(status.getCreatedAt());
                t.setDescription(status.getText());
                t.setUserid(user);
                em.persist(t);
                em.flush();
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TwitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Ttwit> searchTweets(String keyword, String since, String until) {
        List<Ttwit> tweetsList = new ArrayList();
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

            Query query = new Query("#" + keyword);
            query.setLang("en");
            if (!since.equals("")) {
                query.setSince(since);      //"2018-02-27"
            }
            if (!until.equals("")) {
                query.setUntil(until);
            }
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    Ttwit t = new Ttwit();
                    Tuser u = new Tuser();
                    t.setDescription(tweet.getText());
                    t.setCreationdate(tweet.getCreatedAt());
                    u.setFullname(tweet.getUser().getName());
                    u.setUsername(tweet.getUser().getScreenName());
                    t.setUserid(u);
                    tweetsList.add(t);
                    System.out.println(tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);

        } catch (TwitterException ex) {
            Logger.getLogger(TwitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tweetsList;
    }

    public JSONObject tweetsPerYear(String keyword, String since) {
        JSONObject response = new JSONObject();
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

            String until = since;
            QueryResult result;
            for (int i = 1; i < 13; i++) {
                Query query = new Query(keyword);
                query.setLang("en");
                String[] sinceSplit = since.split("-");
                sinceSplit[1] = String.valueOf(i);
                sinceSplit[2] = String.valueOf(1);
                since = sinceSplit[0] + "-" + sinceSplit[1] + "-" + sinceSplit[2];
                query.setSince(since);      //"2018-02-27"
                System.out.println("Since: " + since);
                String[] untilSplit = until.split("-");
                untilSplit[1] = String.valueOf(i + 1);
                untilSplit[2] = String.valueOf(1);
                until = untilSplit[0] + "-" + untilSplit[1] + "-" + untilSplit[2];
                query.setUntil(until);
                System.out.println("until: " + until);
                do {
                    result = twitter.search(query);

                    List<Status> tweets = result.getTweets();
                    System.out.println("tweets: " + tweets);
                    response.put(String.valueOf(i), String.valueOf(tweets.size()));

                } while ((query = result.nextQuery()) != null);
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TwitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(TwitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public void edit(Integer id, String name, String description) {
        try {
            Ttype type = em.find(Ttype.class, id);
            type.setName(name);
            type.setDescription(description);
            em.merge(type);
            em.flush();
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
    }

    public void remove(Integer id) {
        try {
            Ttype type = em.find(Ttype.class, id);
            em.remove(type);
            em.flush();
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
    }

    public Ttype find(Integer id) {
        Ttype type = new Ttype();
        try {
            type = em.find(Ttype.class, id);
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
        return type;
    }

    public List<Ttwit> getUserTwits(Integer userID) {
        List<Ttwit> resultList = em.createNamedQuery("Ttwit.findByUserid")
                .setParameter("userid", userID)
                .getResultList();
        return resultList;
    }

    public JSONObject getDailyTrends() {
        List trendsList = new ArrayList();
        JSONObject json = new JSONObject();
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

            ResponseList<Location> locations;
//            locations = twitter.getAvailableTrends();

//            Integer idTrendLocation = getTrendLocationId("Worldwide");
//            if (idTrendLocation == null) {
//                System.out.println("Trend Location Not Found");
//            }
            Trends trends = twitter.getPlaceTrends(1); // 1 is world wide
            for (int i = 0; i < trends.getTrends().length; i++) {
                json.put(String.valueOf(i), trends.getTrends()[i].getName());
            }

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get trends: " + te.getMessage());
        } catch (JSONException ex) {
            Logger.getLogger(TwitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }

    private static Integer getTrendLocationId(String locationName) {

        int idTrendLocation = 0;

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

            ResponseList<Location> locations;
            locations = twitter.getAvailableTrends();

            for (Location location : locations) {
                System.out.println("location.getName(): " + location.getName());
                System.out.println("ID: " + location.getWoeid());
                if (location.getName().toLowerCase().equals(locationName.toLowerCase())) {
                    idTrendLocation = location.getWoeid();
                    break;
                }
            }

            if (idTrendLocation > 0) {
                return idTrendLocation;
            }

            return null;

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get trends: " + te.getMessage());
            return null;
        }

    }
}
