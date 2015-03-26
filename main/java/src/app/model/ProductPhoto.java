package app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product_photos database table.
 * 
 */
@Entity
@Table(name="product_photos")
@NamedQuery(name="ProductPhoto.findAll", query="SELECT p FROM ProductPhoto p")
public class ProductPhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="img_src")
	private String imgSrc;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public ProductPhoto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgSrc() {
		return this.imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}