package app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sites database table.
 * 
 */
@Entity
@Table(name="sites")
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String page;

	private String path;

	//bi-directional many-to-one association to CategoriesSite
	@OneToMany(mappedBy="site")
	private List<CategoriesSite> categoriesSites;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="site")
	private List<Product> products;

	//bi-directional many-to-one association to PropertiesSite
	@OneToMany(mappedBy="site")
	private List<PropertiesSite> propertiesSites;

	public Site() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<CategoriesSite> getCategoriesSites() {
		return this.categoriesSites;
	}

	public void setCategoriesSites(List<CategoriesSite> categoriesSites) {
		this.categoriesSites = categoriesSites;
	}

	public CategoriesSite addCategoriesSite(CategoriesSite categoriesSite) {
		getCategoriesSites().add(categoriesSite);
		categoriesSite.setSite(this);

		return categoriesSite;
	}

	public CategoriesSite removeCategoriesSite(CategoriesSite categoriesSite) {
		getCategoriesSites().remove(categoriesSite);
		categoriesSite.setSite(null);

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
		product.setSite(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setSite(null);

		return product;
	}

	public List<PropertiesSite> getPropertiesSites() {
		return this.propertiesSites;
	}

	public void setPropertiesSites(List<PropertiesSite> propertiesSites) {
		this.propertiesSites = propertiesSites;
	}

	public PropertiesSite addPropertiesSite(PropertiesSite propertiesSite) {
		getPropertiesSites().add(propertiesSite);
		propertiesSite.setSite(this);

		return propertiesSite;
	}

	public PropertiesSite removePropertiesSite(PropertiesSite propertiesSite) {
		getPropertiesSites().remove(propertiesSite);
		propertiesSite.setSite(null);

		return propertiesSite;
	}

}