package lt2021.projektas.userRegister;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lt2021.projektas.parentdetails.ParentDetails;
import lt2021.projektas.passwordreset.PasswordResetToken;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "first name can't be blank")
	private String firstname;

	@NotBlank(message = "last name can't be blank")
	private String lastname;

	@NotBlank
	@Column(unique = true)
	@Email(message = "email format needs to be correct")
	private String email;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	// @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
	@NotBlank(message = "password can't be blank")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parentDetails_id")
	private ParentDetails parentDetails;
//	@NotNull
//	private String confirmPassword;

	@OneToOne(mappedBy = "user")
	private PasswordResetToken token;


	public User() {
		super();
	}

	public User(@NotBlank String firstname, @NotBlank String lastname, @NotBlank String email, UserRole role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;

	}
	
	public User(@NotBlank String firstname, @NotBlank String lastname, @NotBlank String email, String password, UserRole role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;

	}

	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
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

	public UserRole getRole() {
		return role;
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

	public ParentDetails getParentDetails() {
		return parentDetails;
	}

	public void setParentDetails(ParentDetails parentDetails) {
		this.parentDetails = parentDetails;
	}

	public PasswordResetToken getToken() {
		return token;
	}

	public void setToken(PasswordResetToken token) {
		this.token = token;
	}

	

	@Override
	public String toString() {
		return "Vardas: " + this.firstname + ",\n" + "Pavardė: " + this.lastname + ",\n" + "Pašto adresas: "
				+ this.email + ",\n" + "Registracijos duomenys: \n" + this.parentDetails;
	}

}
