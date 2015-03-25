package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-25T10:03:24.009+0200")
@StaticMetamodel(Category.class)
public class Category_ {
	public static volatile SingularAttribute<Category, Integer> id;
	public static volatile SingularAttribute<Category, String> title;
	public static volatile SingularAttribute<Category, Category> category;
	public static volatile ListAttribute<Category, Category> categories;
	public static volatile ListAttribute<Category, CategoriesSite> categoriesSites;
	public static volatile ListAttribute<Category, CategoryProperty> categoryProperties;
	public static volatile ListAttribute<Category, Product> products;
}
