package lt2021.projektas.parentdetails;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {
	
	@NotBlank
	private String city;

	@NotBlank
	private String street;

	@NotBlank
	private String houseNumber;

	private String flatNumber;
	
	public Address() {
	}
	
	public Address(@NotBlank String city, @NotBlank String street, @NotBlank String houseNumber, String flatNumber) {
		super();
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	
	@Override
	public String toString() {
		if (this.flatNumber.length() > 0) {
			return this.city + ", " + this.street + " " + this.houseNumber + "-" + this.flatNumber;
		} else {
			return this.city + ", " + this.street + " " + this.houseNumber;
		}
	}
	
}
