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

	private String html;

	@Column(name="path_hash")
	private String pathHash;

	@Column(name="path_url")
	private String pathUrl;

	private String title;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="product")
	private List<Comment> comments;

	//bi-directional many-to-one association to ProductPhoto
	@OneToMany(mappedBy="product")
	private List<ProductPhoto> productPhotos;

	//bi-directional many-to-one association to ProductProperty
	@OneToMany(mappedBy="product")
	private List<ProductProperty> productProperties;

	//bi-directional many-to-one association to ProductService
	@OneToMany(mappedBy="product")
	private List<ProductService> productServices;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Site
	@ManyToOne
	private Site site;

	//bi-directional many-to-many association to Service
	@ManyToMany(mappedBy="products")
	private List<Service> services;

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

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setProduct(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setProduct(null);

		return comment;
	}

	public List<ProductPhoto> getProductPhotos() {
		return this.productPhotos;
	}

	public void setProductPhotos(List<ProductPhoto> productPhotos) {
		this.productPhotos = productPhotos;
	}

	public ProductPhoto addProductPhoto(ProductPhoto productPhoto) {
		getProductPhotos().add(productPhoto);
		productPhoto.setProduct(this);

		return productPhoto;
	}

	public ProductPhoto removeProductPhoto(ProductPhoto productPhoto) {
		getProductPhotos().remove(productPhoto);
		productPhoto.setProduct(null);

		return productPhoto;
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

	public List<ProductService> getProductServices() {
		return this.productServices;
	}

	public void setProductServices(List<ProductService> productServices) {
		this.productServices = productServices;
	}

	public ProductService addProductService(ProductService productService) {
		getProductServices().add(productService);
		productService.setProduct(this);

		return productService;
	}

	public ProductService removeProductService(ProductService productService) {
		getProductServices().remove(productService);
		productService.setProduct(null);

		return productService;
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

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

}