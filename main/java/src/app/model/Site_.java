package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-25T09:24:26.635+0200")
@StaticMetamodel(Site.class)
public class Site_ {
	public static volatile SingularAttribute<Site, Integer> id;
	public static volatile SingularAttribute<Site, String> page;
	public static volatile SingularAttribute<Site, String> path;
	public static volatile ListAttribute<Site, CategoriesSite> categoriesSites;
	public static volatile ListAttribute<Site, Product> products;
}
