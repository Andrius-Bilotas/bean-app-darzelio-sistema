package lt2021.projektas.kindergarten.admission;

public class ChildAndParentDetailsObject {
	
	private long childId;
	private String mainParentFirstname;
	private String mainParentLastname;
	private String mainParentEmail;
	private String mainParentPhone;
	private String mainParentAddress;
	private int mainParentNumberOfKids;
	private boolean mainParentStudying;
	private String mainParentStudyingInstitution;
	private boolean mainParentDisabled;
	private String childFirstname;
	private String childLastname;
	private String childBirthdate;
	private String childAddress;
	private boolean childAdopted;
	private int childRating;
	private String kindergartenName;
	private boolean secondParent;
	private String secondParentFirstname;
	private String secondParentLastname;
	private int secondParentNumberofKids;
	private boolean secondParentStudying;
	private String secondParentStudyingInstitution;
	private boolean secondParentDisabled;
	
	public ChildAndParentDetailsObject() {
	}

	public ChildAndParentDetailsObject(long childId, String mainParentFirstname, String mainParentLastname,
			String mainParentEmail, String mainParentPhone, String mainParentAddress, int mainParentNumberOfKids,
			boolean mainParentStudying, String mainParentStudyingInstitution, boolean mainParentDisabled,
			String childFirstname, String childLastname, String childBirthdate, String childAddress, boolean childAdopted, int childRating,
			String kindergartenName, boolean secondParent, String secondParentFirstname, String secondParentLastname,
			int secondParentNumberofKids, boolean secondParentStudying, String secondParentStudyingInstitution,
			boolean secondParentDisabled) {
		super();
		this.childId = childId;
		this.mainParentFirstname = mainParentFirstname;
		this.mainParentLastname = mainParentLastname;
		this.mainParentEmail = mainParentEmail;
		this.mainParentPhone = mainParentPhone;
		this.mainParentAddress = mainParentAddress;
		this.mainParentNumberOfKids = mainParentNumberOfKids;
		this.mainParentStudying = mainParentStudying;
		this.mainParentStudyingInstitution = mainParentStudyingInstitution;
		this.mainParentDisabled = mainParentDisabled;
		this.childFirstname = childFirstname;
		this.childLastname = childLastname;
		this.childBirthdate = childBirthdate;
		this.childAddress = childAddress;
		this.childAdopted = childAdopted;
		this.childRating = childRating;
		this.kindergartenName = kindergartenName;
		this.secondParent = secondParent;
		this.secondParentFirstname = secondParentFirstname;
		this.secondParentLastname = secondParentLastname;
		this.secondParentNumberofKids = secondParentNumberofKids;
		this.secondParentStudying = secondParentStudying;
		this.secondParentStudyingInstitution = secondParentStudyingInstitution;
		this.secondParentDisabled = secondParentDisabled;
	}



	public long getChildId() {
		return childId;
	}

	public void setChildId(long childId) {
		this.childId = childId;
	}

	public String getMainParentFirstname() {
		return mainParentFirstname;
	}

	public void setMainParentFirstname(String mainParentFirstname) {
		this.mainParentFirstname = mainParentFirstname;
	}

	public String getMainParentLastname() {
		return mainParentLastname;
	}

	public void setMainParentLastname(String mainParentLastname) {
		this.mainParentLastname = mainParentLastname;
	}

	public String getMainParentEmail() {
		return mainParentEmail;
	}

	public void setMainParentEmail(String mainParentEmail) {
		this.mainParentEmail = mainParentEmail;
	}

	public String getMainParentPhone() {
		return mainParentPhone;
	}

	public void setMainParentPhone(String mainParentPhone) {
		this.mainParentPhone = mainParentPhone;
	}

	public String getMainParentAddress() {
		return mainParentAddress;
	}

	public void setMainParentAddress(String mainParentAddress) {
		this.mainParentAddress = mainParentAddress;
	}

	public int getMainParentNumberOfKids() {
		return mainParentNumberOfKids;
	}

	public void setMainParentNumberOfKids(int mainParentNumberOfKids) {
		this.mainParentNumberOfKids = mainParentNumberOfKids;
	}

	public boolean isMainParentStudying() {
		return mainParentStudying;
	}

	public void setMainParentStudying(boolean mainParentStudying) {
		this.mainParentStudying = mainParentStudying;
	}

	public String getMainParentStudyingInstitution() {
		return mainParentStudyingInstitution;
	}

	public void setMainParentStudyingInstitution(String mainParentStudyingInstitution) {
		this.mainParentStudyingInstitution = mainParentStudyingInstitution;
	}

	public boolean isMainParentDisabled() {
		return mainParentDisabled;
	}

	public void setMainParentDisabled(boolean mainParentDisabled) {
		this.mainParentDisabled = mainParentDisabled;
	}

	public String getChildFirstname() {
		return childFirstname;
	}

	public void setChildFirstname(String childFirstname) {
		this.childFirstname = childFirstname;
	}

	public String getChildLastname() {
		return childLastname;
	}

	public void setChildLastname(String childLastname) {
		this.childLastname = childLastname;
	}

	public String getChildAddress() {
		return childAddress;
	}

	public void setChildAddress(String childAddress) {
		this.childAddress = childAddress;
	}

	public boolean isChildAdopted() {
		return childAdopted;
	}

	public void setChildAdopted(boolean childAdopted) {
		this.childAdopted = childAdopted;
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

	public int getSecondParentNumberofKids() {
		return secondParentNumberofKids;
	}

	public void setSecondParentNumberofKids(int secondParentNumberofKids) {
		this.secondParentNumberofKids = secondParentNumberofKids;
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

	public boolean isSecondParentDisabled() {
		return secondParentDisabled;
	}

	public void setSecondParentDisabled(boolean secondParentDisabled) {
		this.secondParentDisabled = secondParentDisabled;
	}

	public int getChildRating() {
		return childRating;
	}

	public void setChildRating(int childRating) {
		this.childRating = childRating;
	}

	public String getKindergartenName() {
		return kindergartenName;
	}

	public void setKindergartenName(String kindergartenName) {
		this.kindergartenName = kindergartenName;
	}

	public String getChildBirthdate() {
		return childBirthdate;
	}

	public void setChildBirthdate(String childBirthdate) {
		this.childBirthdate = childBirthdate;
	}
	
	
	
}
