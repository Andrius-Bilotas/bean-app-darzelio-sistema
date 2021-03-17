package lt2021.projektas.userRegister.archive;

public class UserArchiveObject {
	
	private Long id;
	private String email;
	private String filename;
	private String deletionDate;
	
	public UserArchiveObject() {
		// TODO Auto-generated constructor stub
	}

	public UserArchiveObject(Long id, String email, String filename, String deletionDate) {
		super();
		this.id = id;
		this.email = email;
		this.filename = filename;
		this.deletionDate = deletionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDeletionDate() {
		return deletionDate;
	}

	public void setDeletionDate(String deletionDate) {
		this.deletionDate = deletionDate;
	}
	
	
	
}
