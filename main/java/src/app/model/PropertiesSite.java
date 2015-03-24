package app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the properties_sites database table.
 * 
 */
@Entity
@Table(name="properties_sites")
@NamedQuery(name="PropertiesSite.findAll", query="SELECT p FROM PropertiesSite p")
public class PropertiesSite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="value_selector")
	private String valueSelector;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Property
	@ManyToOne
	private Property property;

	//bi-directional many-to-one association to Site
	@ManyToOne
	private Site site;

	public PropertiesSite() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValueSelector() {
		return this.valueSelector;
	}

	public void setValueSelector(String valueSelector) {
		this.valueSelector = valueSelector;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}