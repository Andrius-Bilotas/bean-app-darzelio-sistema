package lt2021.projektas.parentdetails;

import lt2021.projektas.userRegister.User;

public class ServiceLayerDetails {

	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String phone;

	private long personalCode;

	private Address livingAddress;

	private int numberOfKids;

	private boolean isStudying;

	private String studyingInstitution;

	private boolean hasDisability;

	private boolean declaredResidenceSameAsLiving;

	private Address declaredAddress;

	public ServiceLayerDetails() {
	}

	public ServiceLayerDetails(Long id, String firstname, String lastname, String email, String phone, Long personalCode, Address livingAddress,
			int numberOfKids, boolean isStudying, String studyingInstitution, boolean hasDisability,
			boolean declaredResidenceSameAsLiving, Address declaredAddress) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.personalCode = personalCode;
		this.livingAddress = livingAddress;
		this.numberOfKids = numberOfKids;
		this.isStudying = isStudying;
		this.studyingInstitution = studyingInstitution;
		this.hasDisability = hasDisability;
		this.declaredResidenceSameAsLiving = declaredResidenceSameAsLiving;
		this.declaredAddress = declaredAddress;
	}

	public ServiceLayerDetails(String firstname, String lastname, String email, String phone, Long personalCode, Address livingAddress, int numberOfKids,
			boolean isStudying, String studyingInstitution, boolean hasDisability,
			boolean declaredResidenceSameAsLiving, Address declaredAddress) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.personalCode = personalCode;
		this.livingAddress = livingAddress;
		this.numberOfKids = numberOfKids;
		this.isStudying = isStudying;
		this.studyingInstitution = studyingInstitution;
		this.hasDisability = hasDisability;
		this.declaredResidenceSameAsLiving = declaredResidenceSameAsLiving;
		this.declaredAddress = declaredAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Address getLivingAddress() {
		return livingAddress;
	}

	public void setLivingAddress(Address livingAddress) {
		this.livingAddress = livingAddress;
	}

	public int getNumberOfKids() {
		return numberOfKids;
	}

	public void setNumberOfKids(int numberOfKids) {
		this.numberOfKids = numberOfKids;
	}

	public boolean isStudying() {
		return isStudying;
	}

	public void setStudying(boolean isStudying) {
		this.isStudying = isStudying;
	}

	public String getStudyingInstitution() {
		return studyingInstitution;
	}

	public void setStudyingInstitution(String studyingInstitution) {
		this.studyingInstitution = studyingInstitution;
	}

	public boolean isHasDisability() {
		return hasDisability;
	}

	public void setHasDisability(boolean hasDisability) {
		this.hasDisability = hasDisability;
	}

	public boolean isDeclaredResidenceSameAsLiving() {
		return declaredResidenceSameAsLiving;
	}

	public void setDeclaredResidenceSameAsLiving(boolean declaredResidenceSameAsLiving) {
		this.declaredResidenceSameAsLiving = declaredResidenceSameAsLiving;
	}

	public Address getDeclaredAddress() {
		return declaredAddress;
	}

	public void setDeclaredAddress(Address declaredAddress) {
		this.declaredAddress = declaredAddress;
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

	public void setPersonalCode(long personalCode) {
		this.personalCode = personalCode;
	}

	public long getPersonalCode() {
		return personalCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
