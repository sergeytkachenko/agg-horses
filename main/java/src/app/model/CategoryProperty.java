package app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the category_properties database table.
 * 
 */
@Entity
@Table(name="category_properties")
@NamedQuery(name="CategoryProperty.findAll", query="SELECT c FROM CategoryProperty c")
public class CategoryProperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="delete_text")
	private String deleteText;

	@Column(name="value_selector")
	private String valueSelector;

	//bi-directional many-to-one association to Property
	@ManyToOne
	private Property property;

	//bi-directional many-to-one association to Site
	@ManyToOne
	private Site site;

	public CategoryProperty() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeleteText() {
		return this.deleteText;
	}

	public void setDeleteText(String deleteText) {
		this.deleteText = deleteText;
	}

	public String getValueSelector() {
		return this.valueSelector;
	}

	public void setValueSelector(String valueSelector) {
		this.valueSelector = valueSelector;
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