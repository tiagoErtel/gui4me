package gui4me.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "stores")
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String document;

	private String address;

	@Column(columnDefinition = "geography(Point,4326)")
	private Point location;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
}
