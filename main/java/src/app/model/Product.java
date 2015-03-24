package app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_create")
	private Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_update")
	private Date dateUpdate;

	@Column(name="path_hash")
	private String pathHash;

	@Column(name="path_url")
	private String pathUrl;

	//bi-directional many-to-one association to ProductProperty
	@OneToMany(mappedBy="product")
	private List<ProductProperty> productProperties;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Site
	@ManyToOne
	private Site site;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getPathHash() {
		return this.pathHash;
	}

	public void setPathHash(String pathHash) {
		this.pathHash = pathHash;
	}

	public String getPathUrl() {
		return this.pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	public List<ProductProperty> getProductProperties() {
		return this.productProperties;
	}

	public void setProductProperties(List<ProductProperty> productProperties) {
		this.productProperties = productProperties;
	}

	public ProductProperty addProductProperty(ProductProperty productProperty) {
		getProductProperties().add(productProperty);
		productProperty.setProduct(this);

		return productProperty;
	}

	public ProductProperty removeProductProperty(ProductProperty productProperty) {
		getProductProperties().remove(productProperty);
		productProperty.setProduct(null);

		return productProperty;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}