package app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product_properties database table.
 * 
 */
@Entity
@Table(name="product_properties")
@NamedQuery(name="ProductProperty.findAll", query="SELECT p FROM ProductProperty p")
public class ProductProperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String value;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	//bi-directional many-to-one association to Property
	@ManyToOne
	private Property property;

	public ProductProperty() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}