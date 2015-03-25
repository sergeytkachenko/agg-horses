package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-25T09:24:26.627+0200")
@StaticMetamodel(CategoriesSite.class)
public class CategoriesSite_ {
	public static volatile SingularAttribute<CategoriesSite, Integer> id;
	public static volatile SingularAttribute<CategoriesSite, String> aSelector;
	public static volatile SingularAttribute<CategoriesSite, String> pageIteratorSelector;
	public static volatile SingularAttribute<CategoriesSite, String> pathUrl;
	public static volatile SingularAttribute<CategoriesSite, Category> category;
	public static volatile SingularAttribute<CategoriesSite, Site> site;
}
