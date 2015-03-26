package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-26T14:46:58.603+0200")
@StaticMetamodel(Country.class)
public class Country_ {
	public static volatile SingularAttribute<Country, Integer> id;
	public static volatile SingularAttribute<Country, String> title;
	public static volatile ListAttribute<Country, Region> regions;
}
