package lt2021.projektas.logging;

public class LogTableItem {
	
	private Long id;
	private String user;
	private String role;
	private String action;
	private String date;
	
	public LogTableItem() {
		// TODO Auto-generated constructor stub
	}

	public LogTableItem(Long id, String user, String role, String date, String action) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
		this.action = action;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
