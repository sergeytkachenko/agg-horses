package app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the salesmans database table.
 * 
 */
@Entity
@Table(name="salesmans")
@NamedQuery(name="Salesman.findAll", query="SELECT s FROM Salesman s")
public class Salesman implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String title;

	//bi-directional many-to-one association to SalesmanPhone
	@OneToMany(mappedBy="salesman")
	private List<SalesmanPhone> salesmanPhones;

	public Salesman() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SalesmanPhone> getSalesmanPhones() {
		return this.salesmanPhones;
	}

	public void setSalesmanPhones(List<SalesmanPhone> salesmanPhones) {
		this.salesmanPhones = salesmanPhones;
	}

	public SalesmanPhone addSalesmanPhone(SalesmanPhone salesmanPhone) {
		getSalesmanPhones().add(salesmanPhone);
		salesmanPhone.setSalesman(this);

		return salesmanPhone;
	}

	public SalesmanPhone removeSalesmanPhone(SalesmanPhone salesmanPhone) {
		getSalesmanPhones().remove(salesmanPhone);
		salesmanPhone.setSalesman(null);

		return salesmanPhone;
	}

}