package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-26T14:46:58.605+0200")
@StaticMetamodel(ProductPhoto.class)
public class ProductPhoto_ {
	public static volatile SingularAttribute<ProductPhoto, Integer> id;
	public static volatile SingularAttribute<ProductPhoto, String> imgSrc;
	public static volatile SingularAttribute<ProductPhoto, Product> product;
}
