package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-26T14:46:58.608+0200")
@StaticMetamodel(Region.class)
public class Region_ {
	public static volatile SingularAttribute<Region, Integer> id;
	public static volatile SingularAttribute<Region, String> title;
	public static volatile ListAttribute<Region, City> cities;
	public static volatile SingularAttribute<Region, Country> country;
}
