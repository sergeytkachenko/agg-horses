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

	//bi-directional many-to-one association to CategoryProperty
	@OneToMany(mappedBy="property")
	private List<CategoryProperty> categoryProperties;

	//bi-directional many-to-one association to ProductProperty
	@OneToMany(mappedBy="property")
	private List<ProductProperty> productProperties;

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

	public List<CategoryProperty> getCategoryProperties() {
		return this.categoryProperties;
	}

	public void setCategoryProperties(List<CategoryProperty> categoryProperties) {
		this.categoryProperties = categoryProperties;
	}

	public CategoryProperty addCategoryProperty(CategoryProperty categoryProperty) {
		getCategoryProperties().add(categoryProperty);
		categoryProperty.setProperty(this);

		return categoryProperty;
	}

	public CategoryProperty removeCategoryProperty(CategoryProperty categoryProperty) {
		getCategoryProperties().remove(categoryProperty);
		categoryProperty.setProperty(null);

		return categoryProperty;
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

}