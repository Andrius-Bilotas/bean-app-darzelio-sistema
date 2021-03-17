package lt2021.projektas.child;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lt2021.projektas.kindergarten.registration.KindergartenRegistration;
import lt2021.projektas.parentdetails.Address;
import lt2021.projektas.parentdetails.ParentDetails;

@Entity
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String firstname;

	@NotBlank
	private String lastname;

	@NotNull
	@Min(value = 50000000000L, message = "Personal code must be valid")
	@Max(value = 69999999999L, message = "Personal code must be valid")
	@Column(length = 11, unique = true)
	private long personalCode;
	
	@NotNull
	private boolean isAdopted;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Embedded
	@Valid
	private Address livingAddress;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Parents_Children", joinColumns = @JoinColumn(name = "Child_id"), inverseJoinColumns = @JoinColumn(name = "Parent_details_id"))
	private Set<ParentDetails> parents;
	
	@OneToOne(mappedBy = "child")
	private KindergartenRegistration registrationForm;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "file_id")
	private DBFile healthRecord;

	public Child() {
	}

	public Child(@NotBlank String firstname, @NotBlank String lastname, @NotNull long personalCode,
			@NotNull boolean isAdopted, Date birthdate, Address livingAddress) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalCode = personalCode;
		this.isAdopted = isAdopted;
		this.birthdate = birthdate;
		this.livingAddress = livingAddress;
		this.parents = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public long getPersonalCode() {
		return personalCode;
	}

	public void setPersonalCode(long personalCode) {
		this.personalCode = personalCode;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Address getLivingAddress() {
		return livingAddress;
	}

	public void setLivingAddress(Address livingAddress) {
		this.livingAddress = livingAddress;
	}

	public Set<ParentDetails> getParents() {
		return parents;
	}

	public void setParents(Set<ParentDetails> parents) {
		this.parents = parents;
	}

	public boolean isAdopted() {
		return isAdopted;
	}

	public void setAdopted(boolean isAdopted) {
		this.isAdopted = isAdopted;
	}

	public KindergartenRegistration getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(KindergartenRegistration registrationForm) {
		this.registrationForm = registrationForm;
	}

	public DBFile getHealthRecord() {
		return healthRecord;
	}

	public void setHealthRecord(DBFile healthRecord) {
		this.healthRecord = healthRecord;
	}

	@Override
	public String toString() {
		return
				"\n  Vardas: " + this.firstname + ",\n" +
				"  Pavardė: " + this.lastname + ",\n" +
				"  Asmens kodas: " + this.personalCode + ",\n" +
				"  Įvaikintas: " + (this.isAdopted ? "Taip" : "Ne") + ",\n" +
				"  Gimimo data: " + new SimpleDateFormat("yyyy-MM-dd").format(this.birthdate).toString() + ",\n" +
				"  Gyvenamasis adresas: " + this.livingAddress + ",\n" +
				(this.parents.stream().filter(parent -> parent.getParent() == null).findFirst().orElse(null) == null ? "" :
					"  Antro tėvo duomenys: \n" + this.parents.stream().filter(parent -> parent.getParent() == null).findFirst().orElse(null)) + "" + 
				"  Registracijos forma: " + (this.registrationForm == null ? "nėra,\n" : "\n" + this.registrationForm);
	}

	
}
