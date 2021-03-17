package lt2021.projektas.child;

public class CreateChildCommand {

	private Long id;

	private String firstname;

	private String lastname;

	private long personalCode;

	private boolean isAdopted;

	private String birthdate;

	private String city;

	private String street;

	private String houseNumber;

	private String flatNumber;
	
	private Long healthRecordId;

	private boolean secondParent;
	
	private Long secondParentId;

	private String secondParentFirstname;

	private String secondParentLastname;

	private String secondParentEmail;

	private String secondParentPhone;

	private long secondParentPersonalCode;

	private String secondParentCity;

	private String secondParentStreet;

	private String secondParentHouseNumber;

	private String secondParentFlatNumber;

	private int secondParentNumberOfKids;

	private boolean secondParentStudying;

	private String secondParentStudyingInstitution;

	private boolean secondParentHasDisability;

	private boolean secondParentDeclaredResidenceSameAsLiving;

	private String secondParentDeclaredCity;

	private String secondParentDeclaredStreet;

	private String secondParentDeclaredHouseNumber;

	private String secondParentDeclaredFlatNumber;

	public CreateChildCommand(Long id, String firstname, String lastname, long personalCode, boolean isAdopted,
			String birthdate, String city, String street, String houseNumber, String flatNumber, Long healthRecordId, boolean secondParent,
			Long secondParentId, String secondParentFirstname, String secondParentLastname, String secondParentEmail,
			String secondParentPhone, long secondParentPersonalCode, String secondParentCity, String secondParentStreet,
			String secondParentHouseNumber, String secondParentFlatNumber, int secondParentNumberOfKids,
			boolean secondParentStudying, String secondParentStudyingInstitution, boolean secondParentHasDisability,
			boolean secondParentDeclaredResidenceSameAsLiving, String secondParentDeclaredCity,
			String secondParentDeclaredStreet, String secondParentDeclaredHouseNumber,
			String secondParentDeclaredFlatNumber) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalCode = personalCode;
		this.isAdopted = isAdopted;
		this.birthdate = birthdate;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
		this.healthRecordId = healthRecordId;
		this.secondParent = secondParent;
		this.secondParentId = secondParentId;
		this.secondParentFirstname = secondParentFirstname;
		this.secondParentLastname = secondParentLastname;
		this.secondParentEmail = secondParentEmail;
		this.secondParentPhone = secondParentPhone;
		this.secondParentPersonalCode = secondParentPersonalCode;
		this.secondParentCity = secondParentCity;
		this.secondParentStreet = secondParentStreet;
		this.secondParentHouseNumber = secondParentHouseNumber;
		this.secondParentFlatNumber = secondParentFlatNumber;
		this.secondParentNumberOfKids = secondParentNumberOfKids;
		this.secondParentStudying = secondParentStudying;
		this.secondParentStudyingInstitution = secondParentStudyingInstitution;
		this.secondParentHasDisability = secondParentHasDisability;
		this.secondParentDeclaredResidenceSameAsLiving = secondParentDeclaredResidenceSameAsLiving;
		this.secondParentDeclaredCity = secondParentDeclaredCity;
		this.secondParentDeclaredStreet = secondParentDeclaredStreet;
		this.secondParentDeclaredHouseNumber = secondParentDeclaredHouseNumber;
		this.secondParentDeclaredFlatNumber = secondParentDeclaredFlatNumber;
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

	public boolean isSecondParent() {
		return secondParent;
	}

	public void setSecondParent(boolean secondParent) {
		this.secondParent = secondParent;
	}

	public String getSecondParentFirstname() {
		return secondParentFirstname;
	}

	public void setSecondParentFirstname(String secondParentFirstname) {
		this.secondParentFirstname = secondParentFirstname;
	}

	public String getSecondParentLastname() {
		return secondParentLastname;
	}

	public void setSecondParentLastname(String secondParentLastname) {
		this.secondParentLastname = secondParentLastname;
	}

	public String getSecondParentEmail() {
		return secondParentEmail;
	}

	public void setSecondParentEmail(String secondParentEmail) {
		this.secondParentEmail = secondParentEmail;
	}

	public String getSecondParentPhone() {
		return secondParentPhone;
	}

	public void setSecondParentPhone(String secondParentPhone) {
		this.secondParentPhone = secondParentPhone;
	}

	public long getSecondParentPersonalCode() {
		return secondParentPersonalCode;
	}

	public void setSecondParentPersonalCode(long secondParentPersonalCode) {
		this.secondParentPersonalCode = secondParentPersonalCode;
	}

	public String getSecondParentCity() {
		return secondParentCity;
	}

	public void setSecondParentCity(String secondParentCity) {
		this.secondParentCity = secondParentCity;
	}

	public String getSecondParentStreet() {
		return secondParentStreet;
	}

	public void setSecondParentStreet(String secondParentStreet) {
		this.secondParentStreet = secondParentStreet;
	}

	public String getSecondParentHouseNumber() {
		return secondParentHouseNumber;
	}

	public void setSecondParentHouseNumber(String secondParentHouseNumber) {
		this.secondParentHouseNumber = secondParentHouseNumber;
	}

	public String getSecondParentFlatNumber() {
		return secondParentFlatNumber;
	}

	public void setSecondParentFlatNumber(String secondParentFlatNumber) {
		this.secondParentFlatNumber = secondParentFlatNumber;
	}

	public int getSecondParentNumberOfKids() {
		return secondParentNumberOfKids;
	}

	public void setSecondParentNumberOfKids(int secondParentNumberOfKids) {
		this.secondParentNumberOfKids = secondParentNumberOfKids;
	}

	public boolean isSecondParentStudying() {
		return secondParentStudying;
	}

	public void setSecondParentStudying(boolean secondParentStudying) {
		this.secondParentStudying = secondParentStudying;
	}

	public String getSecondParentStudyingInstitution() {
		return secondParentStudyingInstitution;
	}

	public void setSecondParentStudyingInstitution(String secondParentStudyingInstitution) {
		this.secondParentStudyingInstitution = secondParentStudyingInstitution;
	}

	public boolean isSecondParentHasDisability() {
		return secondParentHasDisability;
	}

	public void setSecondParentHasDisability(boolean secondParentHasDisability) {
		this.secondParentHasDisability = secondParentHasDisability;
	}

	public boolean isSecondParentDeclaredResidenceSameAsLiving() {
		return secondParentDeclaredResidenceSameAsLiving;
	}

	public void setSecondParentDeclaredResidenceSameAsLiving(boolean secondParentDeclaredResidenceSameAsLiving) {
		this.secondParentDeclaredResidenceSameAsLiving = secondParentDeclaredResidenceSameAsLiving;
	}

	public String getSecondParentDeclaredCity() {
		return secondParentDeclaredCity;
	}

	public void setSecondParentDeclaredCity(String secondParentDeclaredCity) {
		this.secondParentDeclaredCity = secondParentDeclaredCity;
	}

	public String getSecondParentDeclaredStreet() {
		return secondParentDeclaredStreet;
	}

	public void setSecondParentDeclaredStreet(String secondParentDeclaredStreet) {
		this.secondParentDeclaredStreet = secondParentDeclaredStreet;
	}

	public String getSecondParentDeclaredHouseNumber() {
		return secondParentDeclaredHouseNumber;
	}

	public void setSecondParentDeclaredHouseNumber(String secondParentDeclaredHouseNumber) {
		this.secondParentDeclaredHouseNumber = secondParentDeclaredHouseNumber;
	}

	public String getSecondParentDeclaredFlatNumber() {
		return secondParentDeclaredFlatNumber;
	}

	public void setSecondParentDeclaredFlatNumber(String secondParentDeclaredFlatNumber) {
		this.secondParentDeclaredFlatNumber = secondParentDeclaredFlatNumber;
	}

	public Long getSecondParentId() {
		return secondParentId;
	}

	public void setSecondParentId(Long secondParentId) {
		this.secondParentId = secondParentId;
	}

	public Long getHealthRecordId() {
		return healthRecordId;
	}

	public void setHealthRecordId(Long healthRecordId) {
		this.healthRecordId = healthRecordId;
	}
	
	

}
