package app.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-25T09:24:26.811+0200")
@StaticMetamodel(Product.class)
public class Product_ {
	public static volatile SingularAttribute<Product, Integer> id;
	public static volatile SingularAttribute<Product, Date> dateCreate;
	public static volatile SingularAttribute<Product, Date> dateUpdate;
	public static volatile SingularAttribute<Product, String> pathHash;
	public static volatile SingularAttribute<Product, String> pathUrl;
	public static volatile ListAttribute<Product, ProductProperty> productProperties;
	public static volatile SingularAttribute<Product, Category> category;
	public static volatile SingularAttribute<Product, Site> site;
}
