package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-26T14:46:58.602+0200")
@StaticMetamodel(CategoryProperty.class)
public class CategoryProperty_ {
	public static volatile SingularAttribute<CategoryProperty, Integer> id;
	public static volatile SingularAttribute<CategoryProperty, String> valueSelector;
	public static volatile SingularAttribute<CategoryProperty, Category> category;
	public static volatile SingularAttribute<CategoryProperty, Property> property;
}
