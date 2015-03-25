package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-25T10:03:24.061+0200")
@StaticMetamodel(ProductProperty.class)
public class ProductProperty_ {
	public static volatile SingularAttribute<ProductProperty, Integer> id;
	public static volatile SingularAttribute<ProductProperty, String> value;
	public static volatile SingularAttribute<ProductProperty, Product> product;
	public static volatile SingularAttribute<ProductProperty, Property> property;
}
