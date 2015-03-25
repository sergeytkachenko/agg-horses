package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-25T10:03:23.853+0200")
@StaticMetamodel(Property.class)
public class Property_ {
	public static volatile SingularAttribute<Property, Integer> id;
	public static volatile SingularAttribute<Property, String> title;
	public static volatile ListAttribute<Property, CategoryProperty> categoryProperties;
	public static volatile ListAttribute<Property, ProductProperty> productProperties;
}
