package app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the salesman_phones database table.
 * 
 */
@Entity
@Table(name="salesman_phones")
@NamedQuery(name="SalesmanPhone.findAll", query="SELECT s FROM SalesmanPhone s")
public class SalesmanPhone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String phone;

	//bi-directional many-to-one association to Salesman
	@ManyToOne
	private Salesman salesman;

	public SalesmanPhone() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Salesman getSalesman() {
		return this.salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

}