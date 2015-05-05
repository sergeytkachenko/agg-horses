package app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String title;

	//bi-directional many-to-one association to CategoriesSite
	@OneToMany(mappedBy="category")
	private List<CategoriesSite> categoriesSites;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="category")
	private List<Product> products;

	public Category() {
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

	public List<CategoriesSite> getCategoriesSites() {
		return this.categoriesSites;
	}

	public void setCategoriesSites(List<CategoriesSite> categoriesSites) {
		this.categoriesSites = categoriesSites;
	}

	public CategoriesSite addCategoriesSite(CategoriesSite categoriesSite) {
		getCategoriesSites().add(categoriesSite);
		categoriesSite.setCategory(this);

		return categoriesSite;
	}

	public CategoriesSite removeCategoriesSite(CategoriesSite categoriesSite) {
		getCategoriesSites().remove(categoriesSite);
		categoriesSite.setCategory(null);

		return categoriesSite;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

}