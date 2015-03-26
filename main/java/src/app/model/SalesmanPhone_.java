package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-26T14:46:58.609+0200")
@StaticMetamodel(SalesmanPhone.class)
public class SalesmanPhone_ {
	public static volatile SingularAttribute<SalesmanPhone, Integer> id;
	public static volatile SingularAttribute<SalesmanPhone, String> phone;
	public static volatile SingularAttribute<SalesmanPhone, Salesman> salesman;
}
