package app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-25T10:03:23.854+0200")
@StaticMetamodel(Proxy.class)
public class Proxy_ {
	public static volatile SingularAttribute<Proxy, Integer> id;
	public static volatile SingularAttribute<Proxy, String> ip;
	public static volatile SingularAttribute<Proxy, Boolean> isWorked;
	public static volatile SingularAttribute<Proxy, Integer> pingedCount;
	public static volatile SingularAttribute<Proxy, String> port;
	public static volatile SingularAttribute<Proxy, Integer> timeout;
}
