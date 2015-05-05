package app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the phash_photos database table.
 * 
 */
@Entity
@Table(name="phash_photos")
@NamedQuery(name="PhashPhoto.findAll", query="SELECT p FROM PhashPhoto p")
public class PhashPhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int distance;

	@Column(name="photo_id_x")
	private int photoIdX;

	@Column(name="photo_id_y")
	private int photoIdY;

	public PhashPhoto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getPhotoIdX() {
		return this.photoIdX;
	}

	public void setPhotoIdX(int photoIdX) {
		this.photoIdX = photoIdX;
	}

	public int getPhotoIdY() {
		return this.photoIdY;
	}

	public void setPhotoIdY(int photoIdY) {
		this.photoIdY = photoIdY;
	}

}