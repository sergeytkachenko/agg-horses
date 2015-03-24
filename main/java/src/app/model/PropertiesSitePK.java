package app.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the properties_sites database table.
 * 
 */
@Embeddable
public class PropertiesSitePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="property_id", insertable=false, updatable=false)
	private int propertyId;

	@Column(name="category_sites_id", insertable=false, updatable=false)
	private int categorySitesId;

	public PropertiesSitePK() {
	}
	public int getPropertyId() {
		return this.propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public int getCategorySitesId() {
		return this.categorySitesId;
	}
	public void setCategorySitesId(int categorySitesId) {
		this.categorySitesId = categorySitesId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PropertiesSitePK)) {
			return false;
		}
		PropertiesSitePK castOther = (PropertiesSitePK)other;
		return 
			(this.propertyId == castOther.propertyId)
			&& (this.categorySitesId == castOther.categorySitesId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.propertyId;
		hash = hash * prime + this.categorySitesId;
		
		return hash;
	}
}