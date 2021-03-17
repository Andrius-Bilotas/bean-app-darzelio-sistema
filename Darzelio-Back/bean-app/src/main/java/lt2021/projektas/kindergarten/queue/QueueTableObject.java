package lt2021.projektas.kindergarten.queue;

public class QueueTableObject {
	
	private long id;
	private String kindergartenName;
	private String ageGroup;
	private int submissions;
	private int freeSpots;
	
	public QueueTableObject() {
	}

	public QueueTableObject(long id, String kindergartenName, String ageGroup, int submissions, int freeSpots) {
		super();
		this.id = id;
		this.kindergartenName = kindergartenName;
		this.ageGroup = ageGroup;
		this.submissions = submissions;
		this.freeSpots = freeSpots;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKindergartenName() {
		return kindergartenName;
	}

	public void setKindergartenName(String kindergartenName) {
		this.kindergartenName = kindergartenName;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public int getSubmissions() {
		return submissions;
	}

	public void setSubmissions(int submissions) {
		this.submissions = submissions;
	}

	public int getFreeSpots() {
		return freeSpots;
	}

	public void setFreeSpots(int freeSpots) {
		this.freeSpots = freeSpots;
	}

	
	
}
