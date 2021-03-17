package lt2021.projektas.child;

public class ChildStatusObject {
	
	private long childId;
	private String firstname;
	private String lastname;
	private boolean applicationFilled;
	private boolean applicationAccepted;
	private String notAcceptedReason;
	private String firstPriority;
	private int placeInFirstQueue;
	private String secondPriority;
	private int placeInSecondQueue;
	private String thirdPriority;
	private int placeInThirdQueue;
	private String fourthPriority;
	private int placeInFourthQueue;
	private String fifthPriority;
	private int placeInFifthQueue;
	private String acceptedKindergarten;

	public ChildStatusObject() {
		// TODO Auto-generated constructor stub
	}

	public ChildStatusObject(long childId, String firstname, String lastname, boolean applicationFilled, boolean applicationAccepted,
			String notAcceptedReason, String firstPriority, int placeInFirstQueue, String secondPriority, int placeInSecondQueue,
			String thirdPriority, int placeInThirdQueue, String fourthPriority, int placeInFourthQueue, String fifthPriority,
			int placeInFifthQueue, String acceptedKindergarten) {
		super();
		this.childId = childId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.applicationFilled = applicationFilled;
		this.applicationAccepted = applicationAccepted;
		this.notAcceptedReason = notAcceptedReason;
		this.firstPriority = firstPriority;
		this.placeInFirstQueue = placeInFirstQueue;
		this.secondPriority = secondPriority;
		this.placeInSecondQueue = placeInSecondQueue;
		this.thirdPriority = thirdPriority;
		this.placeInThirdQueue = placeInThirdQueue;
		this.fourthPriority = fourthPriority;
		this.placeInFourthQueue = placeInFourthQueue;
		this.fifthPriority = fifthPriority;
		this.placeInFifthQueue = placeInFifthQueue;
		this.acceptedKindergarten = acceptedKindergarten;
	}

	public boolean isApplicationFilled() {
		return applicationFilled;
	}

	public void setApplicationFilled(boolean applicationFilled) {
		this.applicationFilled = applicationFilled;
	}

	public boolean isApplicationAccepted() {
		return applicationAccepted;
	}

	public void setApplicationAccepted(boolean applicationAccepted) {
		this.applicationAccepted = applicationAccepted;
	}

	public String getNotAcceptedReason() {
		return notAcceptedReason;
	}

	public void setNotAcceptedReason(String notAcceptedReason) {
		this.notAcceptedReason = notAcceptedReason;
	}

	public int getPlaceInFirstQueue() {
		return placeInFirstQueue;
	}

	public void setPlaceInFirstQueue(int placeInFirstQueue) {
		this.placeInFirstQueue = placeInFirstQueue;
	}

	public int getPlaceInSecondQueue() {
		return placeInSecondQueue;
	}

	public void setPlaceInSecondQueue(int placeInSecondQueue) {
		this.placeInSecondQueue = placeInSecondQueue;
	}

	public int getPlaceInThirdQueue() {
		return placeInThirdQueue;
	}

	public void setPlaceInThirdQueue(int placeInThirdQueue) {
		this.placeInThirdQueue = placeInThirdQueue;
	}

	public int getPlaceInFourthQueue() {
		return placeInFourthQueue;
	}

	public void setPlaceInFourthQueue(int placeInFourthQueue) {
		this.placeInFourthQueue = placeInFourthQueue;
	}

	public int getPlaceInFifthQueue() {
		return placeInFifthQueue;
	}

	public void setPlaceInFifthQueue(int placeInFifthQueue) {
		this.placeInFifthQueue = placeInFifthQueue;
	}

	public String getAcceptedKindergarten() {
		return acceptedKindergarten;
	}

	public void setAcceptedKindergarten(String acceptedKindergarten) {
		this.acceptedKindergarten = acceptedKindergarten;
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

	public String getFirstPriority() {
		return firstPriority;
	}

	public void setFirstPriority(String firstPriority) {
		this.firstPriority = firstPriority;
	}

	public String getSecondPriority() {
		return secondPriority;
	}

	public void setSecondPriority(String secondPriority) {
		this.secondPriority = secondPriority;
	}

	public String getThirdPriority() {
		return thirdPriority;
	}

	public void setThirdPriority(String thirdPriority) {
		this.thirdPriority = thirdPriority;
	}

	public String getFourthPriority() {
		return fourthPriority;
	}

	public void setFourthPriority(String fourthPriority) {
		this.fourthPriority = fourthPriority;
	}

	public String getFifthPriority() {
		return fifthPriority;
	}

	public void setFifthPriority(String fifthPriority) {
		this.fifthPriority = fifthPriority;
	}

	public long getChildId() {
		return childId;
	}

	public void setChildId(long childId) {
		this.childId = childId;
	}
	
	

}
