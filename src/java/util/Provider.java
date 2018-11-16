package util;

/**
 *
 * @author Hamid
 */
public class Provider {
    private static Provider ourInstance = new Provider();

    private String AuthConsumerKey = "nlLNmgxoi9Q2RMxhiyEEVwUzY";

    private String AuthConsumerSecret = "lPPs49mNg9hqzuxG6Xzu39Bzw4Z1zNdsrvl6Yff0Da91uotiA4";
    
    private String AuthAccessToken = "575435164-wF8tZEEUxSnYc67TFX4tZpAyNJyhsUar2Ndbs3Wf";
    
    private String AuthAccessTokenSecret = "Cirkv0UTN8AhmiMsAlybbp5nHEqGTA7riMqMWE0MlsKnd";
    
    public static Provider getInstance() {
        return ourInstance;
    }

    public String getAuthConsumerKey() {
        return AuthConsumerKey;
    }

    public String getAuthConsumerSecret() {
        return AuthConsumerSecret;
    }

    public String getAuthAccessToken() {
        return AuthAccessToken;
    }

    public String getAuthAccessTokenSecret() {
        return AuthAccessTokenSecret;
    }

    
}
