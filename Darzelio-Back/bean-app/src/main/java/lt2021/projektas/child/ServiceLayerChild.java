package lt2021.projektas.child;

import lt2021.projektas.parentdetails.Address;
import lt2021.projektas.parentdetails.ServiceLayerDetails;

public class ServiceLayerChild {
	
	private Long id;

	private String firstname;

	private String lastname;

	private long personalCode;
	
	private boolean isAdopted;

	private String birthdate;

	private Address livingAddress;
	
	private long healthRecordId;
	
	private ServiceLayerDetails secondParentDetails;

	public ServiceLayerChild(String firstname, String lastname, long personalCode, boolean isAdopted, String birthdate,
			Address livingAddress) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalCode = personalCode;
		this.isAdopted = isAdopted;
		this.birthdate = birthdate;
		this.livingAddress = livingAddress;
		this.secondParentDetails = null;
	}

	public ServiceLayerChild(Long id, String firstname, String lastname, long personalCode, boolean isAdopted,
			String birthdate, Address livingAddress) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalCode = personalCode;
		this.isAdopted = isAdopted;
		this.birthdate = birthdate;
		this.livingAddress = livingAddress;
		this.secondParentDetails = null;
	}
	
	public ServiceLayerChild(String firstname, String lastname, long personalCode, boolean isAdopted, String birthdate,
			Address livingAddress, ServiceLayerDetails secondParentDetails) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalCode = personalCode;
		this.isAdopted = isAdopted;
		this.birthdate = birthdate;
		this.livingAddress = livingAddress;
		this.secondParentDetails = secondParentDetails;
	}
	
	public ServiceLayerChild(Long id, String firstname, String lastname, long personalCode, boolean isAdopted, String birthdate,
			Address livingAddress, ServiceLayerDetails secondParentDetails) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalCode = personalCode;
		this.isAdopted = isAdopted;
		this.birthdate = birthdate;
		this.livingAddress = livingAddress;
		this.secondParentDetails = secondParentDetails;
	}
	

	public ServiceLayerChild(Long id, String firstname, String lastname, long personalCode, boolean isAdopted,
			String birthdate, Address livingAddress, long healthRecordId) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalCode = personalCode;
		this.isAdopted = isAdopted;
		this.birthdate = birthdate;
		this.livingAddress = livingAddress;
		this.healthRecordId = healthRecordId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getPersonalCode() {
		return personalCode;
	}

	public void setPersonalCode(long personalCode) {
		this.personalCode = personalCode;
	}

	public boolean isAdopted() {
		return isAdopted;
	}

	public void setAdopted(boolean isAdopted) {
		this.isAdopted = isAdopted;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Address getLivingAddress() {
		return livingAddress;
	}

	public void setLivingAddress(Address livingAddress) {
		this.livingAddress = livingAddress;
	}

	public ServiceLayerDetails getSecondParentDetails() {
		return secondParentDetails;
	}

	public void setSecondParentDetails(ServiceLayerDetails secondParentDetails) {
		this.secondParentDetails = secondParentDetails;
	}

	public long getHealthRecordId() {
		return healthRecordId;
	}

	public void setHealthRecordId(long healthRecordId) {
		this.healthRecordId = healthRecordId;
	}
	
	
	
}
