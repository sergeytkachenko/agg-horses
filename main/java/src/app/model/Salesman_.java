package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-26T14:46:58.609+0200")
@StaticMetamodel(Salesman.class)
public class Salesman_ {
	public static volatile SingularAttribute<Salesman, Integer> id;
	public static volatile SingularAttribute<Salesman, String> title;
	public static volatile ListAttribute<Salesman, SalesmanPhone> salesmanPhones;
}
