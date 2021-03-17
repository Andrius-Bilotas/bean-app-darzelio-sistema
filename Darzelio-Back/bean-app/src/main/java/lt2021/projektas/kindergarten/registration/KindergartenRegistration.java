package lt2021.projektas.kindergarten.registration;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lt2021.projektas.child.Child;
import lt2021.projektas.kindergarten.admission.AdmissionProcess;
import lt2021.projektas.kindergarten.queue.KindergartenQueue;

@Entity
public class KindergartenRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "child_id")
	private Child child;

	@NotBlank
	private String firstPriority;

	private String secondPriority;

	private String thirdPriority;

	private String fourthPriority;

	private String fifthPriority;

	@NotNull
	private int rating;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
	@JoinTable(name = "Queue_registrations", joinColumns = @JoinColumn(name = "registration_id"), inverseJoinColumns = @JoinColumn(name = "queue_id"))
	private Set<KindergartenQueue> queues;

	@ManyToOne
	private AdmissionProcess admission;

	private String acceptedKindergarten;

	public KindergartenRegistration() {
	}

	public KindergartenRegistration(Child child, @NotBlank String firstPriority, String secondPriority,
			String thirdPriority, String fourthPriority, String fifthPriority) {
		super();
		this.child = child;
		this.firstPriority = firstPriority;
		this.secondPriority = secondPriority;
		this.thirdPriority = thirdPriority;
		this.fourthPriority = fourthPriority;
		this.fifthPriority = fifthPriority;
		this.rating = 0;
		this.queues = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Set<KindergartenQueue> getQueues() {
		return queues;
	}

	public void setQueues(Set<KindergartenQueue> queues) {
		this.queues = queues;
	}

	public AdmissionProcess getAdmission() {
		return admission;
	}

	public void setAdmission(AdmissionProcess admission) {
		this.admission = admission;
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

	public String getAcceptedKindergarten() {
		return acceptedKindergarten;
	}

	public void setAcceptedKindergarten(String acceptedKindergarten) {
		this.acceptedKindergarten = acceptedKindergarten;
	}
	
	@Override
	public String toString() {
		return
				"   Pirmas prioritetas: " + this.firstPriority + ",\n" +
				"   Antras prioritetas: " + this.secondPriority + ",\n" +
				"   Trečias prioritetas: " + this.thirdPriority + ",\n" +
				"   Ketvirtas prioritetas: " + this.fourthPriority + ",\n" +
				"   Penktas prioritetas: " + this.fifthPriority + ",\n" +
				"   Priimtas į darželį: " + (this.acceptedKindergarten == null ? "Ne" : this.acceptedKindergarten) + "\n";
	}

}
