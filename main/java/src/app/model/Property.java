package app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the properties database table.
 * 
 */
@Entity
@Table(name="properties")
@NamedQuery(name="Property.findAll", query="SELECT p FROM Property p")
public class Property implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String title;

	//bi-directional many-to-one association to ProductProperty
	@OneToMany(mappedBy="property")
	private List<ProductProperty> productProperties;

	//bi-directional many-to-one association to PropertiesSite
	@OneToMany(mappedBy="property")
	private List<PropertiesSite> propertiesSites;

	public Property() {
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

	public List<ProductProperty> getProductProperties() {
		return this.productProperties;
	}

	public void setProductProperties(List<ProductProperty> productProperties) {
		this.productProperties = productProperties;
	}

	public ProductProperty addProductProperty(ProductProperty productProperty) {
		getProductProperties().add(productProperty);
		productProperty.setProperty(this);

		return productProperty;
	}

	public ProductProperty removeProductProperty(ProductProperty productProperty) {
		getProductProperties().remove(productProperty);
		productProperty.setProperty(null);

		return productProperty;
	}

	public List<PropertiesSite> getPropertiesSites() {
		return this.propertiesSites;
	}

	public void setPropertiesSites(List<PropertiesSite> propertiesSites) {
		this.propertiesSites = propertiesSites;
	}

	public PropertiesSite addPropertiesSite(PropertiesSite propertiesSite) {
		getPropertiesSites().add(propertiesSite);
		propertiesSite.setProperty(this);

		return propertiesSite;
	}

	public PropertiesSite removePropertiesSite(PropertiesSite propertiesSite) {
		getPropertiesSites().remove(propertiesSite);
		propertiesSite.setProperty(null);

		return propertiesSite;
	}

}