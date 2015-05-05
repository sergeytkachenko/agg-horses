package app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the services database table.
 * 
 */
@Entity
@Table(name="services")
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String title;

	//bi-directional many-to-one association to ProductService
	@OneToMany(mappedBy="service")
	private List<ProductService> productServices;

	//bi-directional many-to-many association to Product
	@ManyToMany
	@JoinTable(
		name="product_services"
		, joinColumns={
			@JoinColumn(name="service_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="product_id")
			}
		)
	private List<Product> products;

	//bi-directional many-to-one association to Site
	@ManyToOne
	private Site site;

	public Service() {
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

	public List<ProductService> getProductServices() {
		return this.productServices;
	}

	public void setProductServices(List<ProductService> productServices) {
		this.productServices = productServices;
	}

	public ProductService addProductService(ProductService productService) {
		getProductServices().add(productService);
		productService.setService(this);

		return productService;
	}

	public ProductService removeProductService(ProductService productService) {
		getProductServices().remove(productService);
		productService.setService(null);

		return productService;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}