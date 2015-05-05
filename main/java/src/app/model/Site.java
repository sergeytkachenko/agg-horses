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

	@Column(name="images_iterator_selector")
	private String imagesIteratorSelector;

	@Column(name="limit_pages")
	private int limitPages;

	private String page;

	private String path;

	@Column(name="services_iterator_selector")
	private String servicesIteratorSelector;

	//bi-directional many-to-one association to CategoriesSite
	@OneToMany(mappedBy="site")
	private List<CategoriesSite> categoriesSites;

	//bi-directional many-to-one association to CategoryProperty
	@OneToMany(mappedBy="site")
	private List<CategoryProperty> categoryProperties;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="site")
	private List<Product> products;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="site")
	private List<Service> services;

	public Site() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagesIteratorSelector() {
		return this.imagesIteratorSelector;
	}

	public void setImagesIteratorSelector(String imagesIteratorSelector) {
		this.imagesIteratorSelector = imagesIteratorSelector;
	}

	public int getLimitPages() {
		return this.limitPages;
	}

	public void setLimitPages(int limitPages) {
		this.limitPages = limitPages;
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

	public String getServicesIteratorSelector() {
		return this.servicesIteratorSelector;
	}

	public void setServicesIteratorSelector(String servicesIteratorSelector) {
		this.servicesIteratorSelector = servicesIteratorSelector;
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

	public List<CategoryProperty> getCategoryProperties() {
		return this.categoryProperties;
	}

	public void setCategoryProperties(List<CategoryProperty> categoryProperties) {
		this.categoryProperties = categoryProperties;
	}

	public CategoryProperty addCategoryProperty(CategoryProperty categoryProperty) {
		getCategoryProperties().add(categoryProperty);
		categoryProperty.setSite(this);

		return categoryProperty;
	}

	public CategoryProperty removeCategoryProperty(CategoryProperty categoryProperty) {
		getCategoryProperties().remove(categoryProperty);
		categoryProperty.setSite(null);

		return categoryProperty;
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

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setSite(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setSite(null);

		return service;
	}

}