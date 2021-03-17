package lt2021.projektas.kindergarten.queue;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt2021.projektas.kindergarten.Kindergarten;
import lt2021.projektas.kindergarten.KindergartenDao;
import lt2021.projektas.kindergarten.admission.AdmissionDao;
import lt2021.projektas.kindergarten.admission.AdmissionService;
import lt2021.projektas.kindergarten.registration.KindergartenRegistration;
import lt2021.projektas.kindergarten.registration.KindergartenRegistrationDao;
import lt2021.projektas.logging.Log;
import lt2021.projektas.logging.LogDao;
import lt2021.projektas.userRegister.User;

@Service
public class QueueService {

	@Autowired
	private QueueDao queueDao;

	@Autowired
	private AdmissionService admissionService;

	@Autowired
	private AdmissionDao admissionDao;

	@Autowired
	private KindergartenDao kindergartenDao;

	@Autowired
	private KindergartenRegistrationDao registrationDao;
	
	@Autowired
	private LogDao logDao;

	@Transactional
	public void createNewQueuesForKindergarten(Kindergarten kg, User user) {
		var admission = admissionDao.findAll().get(0);
		if (!(kg.getQueues().stream().anyMatch(queue -> queue.getAgeGroup().equals(AgeGroup.PRESCHOOL)))
				&& kg.getSpotsInFirstAgeGroup() > 0) {
			var preSchool = queueDao.save(new KindergartenQueue(admission, kg, AgeGroup.PRESCHOOL));
			var queues = kg.getQueues();
			queues.add(preSchool);
			kg.setQueues(queues);
			var adQueues = admission.getQueues();
			adQueues.add(preSchool);
			admission.setQueues(adQueues);
			logDao.save(new Log(new Date(), user.getEmail(), user.getRole().toString(), "Sukurta nauja darželio: " + kg.getName() + ", 2-3m. grupės eilė"));
		}
		if (!(kg.getQueues().stream().anyMatch(queue -> queue.getAgeGroup().equals(AgeGroup.KINDERGARTEN)))
				&& kg.getSpotsInSecondAgeGroup() > 0) {
			var kindergarten = queueDao.save(new KindergartenQueue(admission, kg, AgeGroup.KINDERGARTEN));
			var queues = kg.getQueues();
			queues.add(kindergarten);
			kg.setQueues(queues);
			var adQueues = admission.getQueues();
			adQueues.add(kindergarten);
			admission.setQueues(adQueues);
			logDao.save(new Log(new Date(), user.getEmail(), user.getRole().toString(), "Sukurta nauja darželio: " + kg.getName() + ", 3-6m. grupės eilė"));
		}
		kindergartenDao.save(kg);
		admissionDao.save(admission);
	}

	@Transactional
	public void addRegistrationToQueues(KindergartenRegistration kgReg) {
		var admission = admissionDao.findAll().get(0);
		if (admission.isActive()) {
			Date today = new Date();
			var timeDiff = today.getTime() - kgReg.getChild().getBirthdate().getTime();
			var timeDiffDays = TimeUnit.MILLISECONDS.toDays(timeDiff);
			var yearDiff = timeDiffDays / 365;
			AgeGroup ageGroup = null;
			if (yearDiff >= 2 && yearDiff < 3) {
				ageGroup = AgeGroup.PRESCHOOL;
			} else if (yearDiff >= 3 && yearDiff <= 6) {
				ageGroup = AgeGroup.KINDERGARTEN;
			}
			var kindergarten = kindergartenDao.findByName(kgReg.getFirstPriority()).orElse(null);
			if (kindergarten != null && ageGroup != null) {
				var queue = queueDao.findQueueByKindergartenNameAndAgeGroup(kindergarten, ageGroup).orElse(null);
				if (queue != null) {
					var registrations = queue.getRegistrations();
					registrations.add(kgReg);
					queue.setRegistrations(registrations);
					var queues = kgReg.getQueues();
					queues.add(queue);
					kgReg.setQueues(queues);
					kindergartenDao.save(kindergarten);
				}
				kindergarten = kindergartenDao.findByName(kgReg.getSecondPriority()).orElse(null);
				if (kindergarten != null) {
					queue = queueDao.findQueueByKindergartenNameAndAgeGroup(kindergarten, ageGroup).orElse(null);
					if (queue != null) {
						var registrations = queue.getRegistrations();
						registrations.add(kgReg);
						queue.setRegistrations(registrations);
						var queues = kgReg.getQueues();
						queues.add(queue);
						kgReg.setQueues(queues);
						kindergartenDao.save(kindergarten);
					}
				}
				kindergarten = kindergartenDao.findByName(kgReg.getThirdPriority()).orElse(null);
				if (kindergarten != null) {
					queue = queueDao.findQueueByKindergartenNameAndAgeGroup(kindergarten, ageGroup).orElse(null);
					if (queue != null) {
						var registrations = queue.getRegistrations();
						registrations.add(kgReg);
						queue.setRegistrations(registrations);
						var queues = kgReg.getQueues();
						queues.add(queue);
						kgReg.setQueues(queues);
						kindergartenDao.save(kindergarten);
					}
				}
				kindergarten = kindergartenDao.findByName(kgReg.getFourthPriority()).orElse(null);
				if (kindergarten != null) {
					queue = queueDao.findQueueByKindergartenNameAndAgeGroup(kindergarten, ageGroup).orElse(null);
					if (queue != null) {
						var registrations = queue.getRegistrations();
						registrations.add(kgReg);
						queue.setRegistrations(registrations);
						var queues = kgReg.getQueues();
						queues.add(queue);
						kgReg.setQueues(queues);
						kindergartenDao.save(kindergarten);
					}
				}
				kindergarten = kindergartenDao.findByName(kgReg.getFifthPriority()).orElse(null);
				if (kindergarten != null) {
					queue = queueDao.findQueueByKindergartenNameAndAgeGroup(kindergarten, ageGroup).orElse(null);
					if (queue != null) {
						var registrations = queue.getRegistrations();
						registrations.add(kgReg);
						queue.setRegistrations(registrations);
						var queues = kgReg.getQueues();
						queues.add(queue);
						kgReg.setQueues(queues);
						kindergartenDao.save(kindergarten);
					}
				}
				var adRegistrations = admission.getRegistrations();
				adRegistrations.add(kgReg);
				admission.setRegistrations(adRegistrations);
				kgReg.setAdmission(admission);
				registrationDao.save(kgReg);
				admissionDao.save(admission);
			}
		} else {
			registrationDao.save(kgReg);
		}
	}

	@Transactional
	public List<QueueTableObject> getAllAdmissionQueues() {
		return queueDao.findAll().stream()
				.map(queue -> new QueueTableObject(queue.getId(), queue.getKindergarten().getName(),
						queue.getAgeGroup().toString(), queue.getRegistrations().size(),
						queue.getAgeGroup().equals(AgeGroup.PRESCHOOL)
								? queue.getKindergarten().getSpotsInFirstAgeGroup()
								: queue.getKindergarten().getSpotsInSecondAgeGroup()))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public int getChildPositionInKindergartenQueue(String kgname, KindergartenRegistration childReg) {
		var kindergarten = kindergartenDao.findByName(kgname).orElse(null);
		if (kindergarten != null) {
			var queue = kindergarten.getQueues().stream().filter(q -> q.getRegistrations().contains(childReg)).findFirst().orElse(null);
			if (queue != null) {
				var registrations = queue.getRegistrations().stream().filter(reg -> reg.getAcceptedKindergarten() == null).collect(Collectors.toList());
				registrations.sort((r1, r2) -> {
					if (r1.getRating() == r2.getRating()) {
						if (r1.getChild().getBirthdate().compareTo(r2.getChild().getBirthdate()) == 0) {
							return r1.getChild().getLastname().compareTo(r2.getChild().getLastname());
						} else {
							return r1.getChild().getBirthdate().compareTo(r2.getChild().getBirthdate());
						}
					} else {
						return r2.getRating() - r1.getRating();
					}
				});
				return registrations.indexOf(childReg) + 1;
			}
			return 0;
		}
		return 0;
	}
	

}
