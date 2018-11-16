package entity;

import entity.Ttype;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-23T23:10:54")
@StaticMetamodel(Tuser.class)
public class Tuser_ { 

    public static volatile SingularAttribute<Tuser, String> description;
    public static volatile SingularAttribute<Tuser, Ttype> typeid;
    public static volatile SingularAttribute<Tuser, Integer> id;
    public static volatile SingularAttribute<Tuser, String> fullname;
    public static volatile SingularAttribute<Tuser, Date> dto;
    public static volatile SingularAttribute<Tuser, String> username;

}