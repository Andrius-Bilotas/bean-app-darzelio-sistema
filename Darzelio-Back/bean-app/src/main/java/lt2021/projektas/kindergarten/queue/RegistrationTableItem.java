package lt2021.projektas.kindergarten.queue;

public class RegistrationTableItem {

	private long childId;
	private String firstname;
	private String lastname;
	private String kindergartenName;
	private long personalCode;
	private int rating;
	private boolean accepted;

	public RegistrationTableItem() {
	}

	public RegistrationTableItem(long childId, String firstname, String lastname,
			String kindergartenName, long personalCode, int rating, boolean accepted) {
		super();
		this.childId = childId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.kindergartenName = kindergartenName;
		this.personalCode = personalCode;
		this.rating = rating;
		this.accepted = accepted;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public long getChildId() {
		return childId;
	}

	public void setChildId(long childId) {
		this.childId = childId;
	}

	public String getKindergartenName() {
		return kindergartenName;
	}

	public void setKindergartenName(String kindergartenName) {
		this.kindergartenName = kindergartenName;
	}

	public long getPersonalCode() {
		return personalCode;
	}

	public void setPersonalCode(long personalCode) {
		this.personalCode = personalCode;
	}

	

}
