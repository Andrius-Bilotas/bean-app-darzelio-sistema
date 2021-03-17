package lt2021.projektas.parentdetails;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lt2021.projektas.child.Child;
import lt2021.projektas.userRegister.User;

@Entity
public class ParentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "parentDetails")
	private User parent;
	
	@NotBlank
	private String firstname;
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	@Email(message = "Email format must be correct")
	private String email;
	
	@NotBlank
	private String phone;

	@NotNull
	@Min(value = 30000000000L, message = "Personal code must be valid")
	@Max(value = 69999999999L, message = "Personal code must be valid")
	@Column(length = 11, unique = true)
	private long personalCode;

	@Embedded
	@Valid
	private Address livingAddress;

	@NotNull
	private int numberOfKids;

	@NotNull
	private boolean isStudying;

	private String studyingInstitution;

	@NotNull
	private boolean hasDisability;

	@NotNull
	private boolean declaredResidenceSameAsLiving;

	@Embedded
	@Valid
	@AttributeOverrides({ @AttributeOverride(name = "city", column = @Column(name = "declared_city")),
			@AttributeOverride(name = "street", column = @Column(name = "declared_street")),
			@AttributeOverride(name = "houseNumber", column = @Column(name = "declared_houseNumber")),
			@AttributeOverride(name = "flatNumber", column = @Column(name = "declared_flatNumber")) })
	private Address declaredAddress;

	@ManyToMany(mappedBy = "parents", cascade = CascadeType.ALL)
	private Set<Child> children;

	public ParentDetails() {
	}

	public ParentDetails(@NotBlank String firstname, @NotBlank String lastname, @NotBlank String email, @NotBlank String phone, @NotBlank Long personalCode, Address livingAddress,
			@NotBlank int numberOfKids, @NotBlank boolean isStudying, String studyingInstitution,
			@NotBlank boolean hasDisability, @NotBlank boolean declaredResidenceSameAsLiving, Address declaredAddress) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.personalCode = personalCode;
		this.livingAddress = livingAddress;
		this.numberOfKids = numberOfKids;
		this.isStudying = isStudying;
		this.studyingInstitution = studyingInstitution;
		this.hasDisability = hasDisability;
		this.declaredResidenceSameAsLiving = declaredResidenceSameAsLiving;
		this.declaredAddress = declaredAddress;
		this.children = new HashSet<>();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNumberOfKids() {
		return numberOfKids;
	}

	public void setNumberOfKids(int numberOfKids) {
		this.numberOfKids = numberOfKids;
	}

	public String getStudyingInstitution() {
		return studyingInstitution;
	}

	public void setStudyingInstitution(String studyingInstitution) {
		this.studyingInstitution = studyingInstitution;
	}

	public boolean isHasDisability() {
		return hasDisability;
	}

	public void setHasDisability(boolean hasDisability) {
		this.hasDisability = hasDisability;
	}

	public boolean isDeclaredResidenceSameAsLiving() {
		return declaredResidenceSameAsLiving;
	}

	public void setDeclaredResidenceSameAsLiving(boolean declaredResidenceSameAsLiving) {
		this.declaredResidenceSameAsLiving = declaredResidenceSameAsLiving;
	}

	public Address getLivingAddress() {
		return livingAddress;
	}

	public void setLivingAddress(Address livingAddress) {
		this.livingAddress = livingAddress;
	}

	public Address getDeclaredAddress() {
		return declaredAddress;
	}

	public void setDeclaredAddress(Address declaredAddress) {
		this.declaredAddress = declaredAddress;
	}

	public boolean isStudying() {
		return isStudying;
	}

	public void setStudying(boolean isStudying) {
		this.isStudying = isStudying;
	}

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	public void setPersonalCode(long personalCode) {
		this.personalCode = personalCode;
	}

	public long getPersonalCode() {
		return personalCode;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return
				" Registruotas vardas: " + this.firstname + ",\n" +
				" Registruota pavardė: " + this.lastname + ",\n" +
				" Registruotas el. pašto adresas: " + this.email + ",\n" +
				" Telefono numeris: " + this.phone + ",\n" +
				" Asmens kodas: " + this.personalCode + ",\n" +
				" Registruotas vardas: " + this.firstname + ",\n" +
				" Gyvenamasis adresas: " + this.livingAddress + ",\n" +
				" Vaikų skaičius: " + this.numberOfKids + ",\n" +
				" Studijuoja: " + (this.isStudying ? "Taip" : "Ne") + ",\n" +
				" Studijų institucijos pavadinimas: " + this.studyingInstitution + ",\n" +
				" Darbingumo lygis mažiau 40%: " + (this.hasDisability ? "Taip" : "Ne") + ",\n" +
				" Deklaruotas adresas sutampa su gyvenamuoju: " + (this.declaredResidenceSameAsLiving ? "Taip" : "Ne") + ",\n" +
				" Deklaruotas adresas: " + this.declaredAddress + ",\n" +
				( this.parent == null ? "" : " Vaikai: \n" + this.children);
	}

}
