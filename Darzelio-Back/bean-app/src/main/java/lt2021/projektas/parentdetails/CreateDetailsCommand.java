package lt2021.projektas.parentdetails;

public class CreateDetailsCommand {

	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String phone;

	private long personalCode;

	private String city;

	private String street;

	private String houseNumber;

	private String flatNumber;

	private int numberOfKids;

	private boolean isStudying;

	private String studyingInstitution;

	private boolean hasDisability;

	private boolean declaredResidenceSameAsLiving;

	private String declaredCity;

	private String declaredStreet;

	private String declaredHouseNumber;

	private String declaredFlatNumber;

	public CreateDetailsCommand() {
	}

	public CreateDetailsCommand(Long id, String firstname, String lastname, String email, String phone, Long personalCode, String city, String street,
			String houseNumber, String flatNumber, int numberOfKids, boolean isStudying, String studyingInstitution,
			boolean hasDisability, boolean declaredResidenceSameAsLiving, String declaredCity, String declaredStreet,
			String declaredHouseNumber, String declaredFlatNumber) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.personalCode = personalCode;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
		this.numberOfKids = numberOfKids;
		this.isStudying = isStudying;
		this.studyingInstitution = studyingInstitution;
		this.hasDisability = hasDisability;
		this.declaredResidenceSameAsLiving = declaredResidenceSameAsLiving;
		this.declaredCity = declaredCity;
		this.declaredStreet = declaredStreet;
		this.declaredHouseNumber = declaredHouseNumber;
		this.declaredFlatNumber = declaredFlatNumber;
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

	public String getDeclaredCity() {
		return declaredCity;
	}

	public void setDeclaredCity(String declaredCity) {
		this.declaredCity = declaredCity;
	}

	public String getDeclaredStreet() {
		return declaredStreet;
	}

	public void setDeclaredStreet(String declaredStreet) {
		this.declaredStreet = declaredStreet;
	}

	public String getDeclaredHouseNumber() {
		return declaredHouseNumber;
	}

	public void setDeclaredHouseNumber(String declaredHouseNumber) {
		this.declaredHouseNumber = declaredHouseNumber;
	}

	public String getDeclaredFlatNumber() {
		return declaredFlatNumber;
	}

	public void setDeclaredFlatNumber(String declaredFlatNumber) {
		this.declaredFlatNumber = declaredFlatNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
