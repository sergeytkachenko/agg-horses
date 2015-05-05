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

	@Column(name="is_downloaded")
	private boolean isDownloaded;

	@Column(name="out_hash")
	private String outHash;

	@Column(name="out_img_src")
	private String outImgSrc;

	private String phash;

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

	public boolean getIsDownloaded() {
		return this.isDownloaded;
	}

	public void setIsDownloaded(boolean isDownloaded) {
		this.isDownloaded = isDownloaded;
	}

	public String getOutHash() {
		return this.outHash;
	}

	public void setOutHash(String outHash) {
		this.outHash = outHash;
	}

	public String getOutImgSrc() {
		return this.outImgSrc;
	}

	public void setOutImgSrc(String outImgSrc) {
		this.outImgSrc = outImgSrc;
	}

	public String getPhash() {
		return this.phash;
	}

	public void setPhash(String phash) {
		this.phash = phash;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}