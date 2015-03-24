package app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categories_sites database table.
 * 
 */
@Entity
@Table(name="categories_sites")
@NamedQuery(name="CategoriesSite.findAll", query="SELECT c FROM CategoriesSite c")
public class CategoriesSite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="a_selector")
	private String aSelector;

	@Column(name="page_iterator_selector")
	private String pageIteratorSelector;

	@Column(name="path_url")
	private String pathUrl;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Site
	@ManyToOne
	private Site site;

	public CategoriesSite() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getASelector() {
		return this.aSelector;
	}

	public void setASelector(String aSelector) {
		this.aSelector = aSelector;
	}

	public String getPageIteratorSelector() {
		return this.pageIteratorSelector;
	}

	public void setPageIteratorSelector(String pageIteratorSelector) {
		this.pageIteratorSelector = pageIteratorSelector;
	}

	public String getPathUrl() {
		return this.pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
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