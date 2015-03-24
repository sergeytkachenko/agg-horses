package app.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the product_properties database table.
 * 
 */
@Embeddable
public class ProductPropertyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="product_id", insertable=false, updatable=false)
	private int productId;

	@Column(name="property_id", insertable=false, updatable=false)
	private int propertyId;

	public ProductPropertyPK() {
	}
	public int getProductId() {
		return this.productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getPropertyId() {
		return this.propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductPropertyPK)) {
			return false;
		}
		ProductPropertyPK castOther = (ProductPropertyPK)other;
		return 
			(this.productId == castOther.productId)
			&& (this.propertyId == castOther.propertyId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productId;
		hash = hash * prime + this.propertyId;
		
		return hash;
	}
}