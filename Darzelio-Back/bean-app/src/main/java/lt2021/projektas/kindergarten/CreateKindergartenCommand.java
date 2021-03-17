package lt2021.projektas.kindergarten;

public class CreateKindergartenCommand {

	private Long id;

	private String name;

	private String address;

	private int spotsInFirstAgeGroup;

	private int spotsInSecondAgeGroup;

	public CreateKindergartenCommand(Long id, String name, String address, int spotsInFirstAgeGroup,
			int spotsInSecondAgeGroup) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.spotsInFirstAgeGroup = spotsInFirstAgeGroup;
		this.spotsInSecondAgeGroup = spotsInSecondAgeGroup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSpotsInFirstAgeGroup() {
		return spotsInFirstAgeGroup;
	}

	public void setSpotsInFirstAgeGroup(int spotsInFirstAgeGroup) {
		this.spotsInFirstAgeGroup = spotsInFirstAgeGroup;
	}

	public int getSpotsInSecondAgeGroup() {
		return spotsInSecondAgeGroup;
	}

	public void setSpotsInSecondAgeGroup(int spotsInSecondAgeGroup) {
		this.spotsInSecondAgeGroup = spotsInSecondAgeGroup;
	}

}
