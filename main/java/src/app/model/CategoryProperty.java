package app.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="category_properties")
@NamedQuery(name="CategoryProperty.findAll", query="SELECT c FROM CategoryProperty c")
public class CategoryProperty implements Serializable {
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

	public CategoryProperty() {
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

}