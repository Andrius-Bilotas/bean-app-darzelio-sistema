package lt2021.projektas.kindergarten.admission;

public class AdmissionTableObject {
	
	private long id;
	private String startDate;
	private String endDate;
	private boolean active;
	private int registrationCount;
	private int totalSpotsInKindergartens;
	private boolean allQueuesConfirmed;
	
	public AdmissionTableObject() {
		// TODO Auto-generated constructor stub
	}

	public AdmissionTableObject(long id, String startDate, String endDate, boolean active, int registrationCount,
			int totalSpotsInKindergartens, boolean allQueuesConfirmed) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
		this.registrationCount = registrationCount;
		this.totalSpotsInKindergartens = totalSpotsInKindergartens;
		this.allQueuesConfirmed = allQueuesConfirmed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getRegistrationCount() {
		return registrationCount;
	}

	public void setRegistrationCount(int registrationCount) {
		this.registrationCount = registrationCount;
	}

	public int getTotalSpotsInKindergartens() {
		return totalSpotsInKindergartens;
	}

	public void setTotalSpotsInKindergartens(int totalSpotsInKindergartens) {
		this.totalSpotsInKindergartens = totalSpotsInKindergartens;
	}

	public boolean isAllQueuesConfirmed() {
		return allQueuesConfirmed;
	}

	public void setAllQueuesConfirmed(boolean allQueuesConfirmed) {
		this.allQueuesConfirmed = allQueuesConfirmed;
	}

	
}
