package lt2021.projektas.kindergarten.registration;

public class CreateRegistrationCommand {
	
	private Long id;
	
	private Long childId;
	
	private String firstPriority;
	
	private String secondPriority;
	
	private String thirdPriority;
	
	private String fourthPriority;
	
	private String fifthPriority;
	
	private int rating;
	
	public CreateRegistrationCommand() {
	}

	public CreateRegistrationCommand(Long id, Long childId, String firstPriority, String secondPriority, String thirdPriority,
			String fourthPriority, String fifthPriority, int rating) {
		super();
		this.id = id;
		this.childId = childId;
		this.firstPriority = firstPriority;
		this.secondPriority = secondPriority;
		this.thirdPriority = thirdPriority;
		this.fourthPriority = fourthPriority;
		this.fifthPriority = fifthPriority;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
