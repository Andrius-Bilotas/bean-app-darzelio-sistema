package lt2021.projektas.kindergarten;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lt2021.projektas.kindergarten.queue.KindergartenQueue;
import lt2021.projektas.kindergarten.registration.KindergartenRegistration;

@Entity
public class Kindergarten {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "kindergarten_name", unique = true)
	private String name;

	@NotBlank
	private String address;

	@NotNull
	private int spotsInFirstAgeGroup;

	@NotNull
	private int spotsInSecondAgeGroup;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "kindergarten_queues")
	private Set<KindergartenQueue> queues;
	
	
	public Kindergarten() {
	}

	public Kindergarten(@NotBlank String name, @NotBlank String address, @NotNull int spotsInFirstAgeGroup,
			@NotNull int spotsInSecondAgeGroup) {
		super();
		this.name = name;
		this.address = address;
		this.spotsInFirstAgeGroup = spotsInFirstAgeGroup;
		this.spotsInSecondAgeGroup = spotsInSecondAgeGroup;
		this.queues = new HashSet<>();
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

	public Set<KindergartenQueue> getQueues() {
		return queues;
	}

	public void setQueues(Set<KindergartenQueue> queues) {
		this.queues = queues;
	}



}
