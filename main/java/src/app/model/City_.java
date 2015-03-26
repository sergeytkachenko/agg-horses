package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-26T14:46:58.602+0200")
@StaticMetamodel(City.class)
public class City_ {
	public static volatile SingularAttribute<City, Integer> id;
	public static volatile SingularAttribute<City, String> title;
	public static volatile SingularAttribute<City, Region> region;
}
