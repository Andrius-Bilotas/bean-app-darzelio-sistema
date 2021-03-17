package lt2021.projektas.kindergarten;

public class KindergartenStatisticsObject {
	
	private String kindergartenName;
	private int firstPriorityRegistrations;
	
	public KindergartenStatisticsObject() {
		// TODO Auto-generated constructor stub
		this.firstPriorityRegistrations = 0;
	}
	
	public KindergartenStatisticsObject(String kindergartenName, int firstPriorityRegistrations) {
		super();
		this.kindergartenName = kindergartenName;
		this.firstPriorityRegistrations = firstPriorityRegistrations;
	}

	public String getKindergartenName() {
		return kindergartenName;
	}

	public void setKindergartenName(String kindergartenName) {
		this.kindergartenName = kindergartenName;
	}

	public int getFirstPriorityRegistrations() {
		return firstPriorityRegistrations;
	}

	public void setFirstPriorityRegistrations(int firstPriorityRegistrations) {
		this.firstPriorityRegistrations = firstPriorityRegistrations;
	}
	
	

}
