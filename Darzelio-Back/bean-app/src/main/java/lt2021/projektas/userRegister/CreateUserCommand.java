package lt2021.projektas.userRegister;

public class CreateUserCommand {

	private String firstname;
	private String lastname;
	private String email;

	private UserRole role;

	private String password;
//	private String confirmPassword;
	


	public CreateUserCommand() {
		super();
	}

	public CreateUserCommand(String firstname, String lastname, String email, String password, UserRole role) {

		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
