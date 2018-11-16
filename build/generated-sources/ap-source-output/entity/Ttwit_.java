package entity;

import entity.Tuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-23T23:10:54")
@StaticMetamodel(Ttwit.class)
public class Ttwit_ { 

    public static volatile SingularAttribute<Ttwit, Date> creationdate;
    public static volatile SingularAttribute<Ttwit, String> description;
    public static volatile SingularAttribute<Ttwit, Integer> id;
    public static volatile SingularAttribute<Ttwit, Tuser> userid;

}