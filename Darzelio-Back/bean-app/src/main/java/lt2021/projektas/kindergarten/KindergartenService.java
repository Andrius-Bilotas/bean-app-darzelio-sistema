package lt2021.projektas.kindergarten;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt2021.projektas.kindergarten.admission.AdmissionDao;
import lt2021.projektas.kindergarten.queue.QueueService;
import lt2021.projektas.kindergarten.registration.KindergartenRegistrationDao;
import lt2021.projektas.logging.Log;
import lt2021.projektas.logging.LogDao;
import lt2021.projektas.userRegister.User;

@Service
public class KindergartenService {
	
	@Autowired
	private KindergartenDao kgDao;
	
	@Autowired
	private KindergartenRegistrationDao registrationDao;
	
	@Autowired
	private QueueService queueService;
	
	@Autowired
	private AdmissionDao admissionDao;
	
	@Autowired
	private LogDao logDao;
	
	@Transactional
	public void addKindergarten(CreateKindergartenCommand kindergarten, User user) {
		logDao.save(new Log(new Date(), user.getEmail(), user.getRole().toString(), "Sukurtas naujas darželis: " + kindergarten.getName().toUpperCase()));
		queueService.createNewQueuesForKindergarten(new Kindergarten(kindergarten.getName().toUpperCase(), kindergarten.getAddress(),
				kindergarten.getSpotsInFirstAgeGroup(), kindergarten.getSpotsInSecondAgeGroup()), user);
	}
	
	@Transactional
	public CreateKindergartenCommand getKindergarten(long kgId) {
		Kindergarten kg = kgDao.findById(kgId).orElse(null);
		if (kg != null) {
			return new CreateKindergartenCommand(kg.getId(), kg.getName(), kg.getAddress(),
					kg.getSpotsInFirstAgeGroup(), kg.getSpotsInSecondAgeGroup());
		} else {
			return null;
		}
	}
	
	@Transactional
	public List<CreateKindergartenCommand> getAllKindergartens() {
		return kgDao.findAll().stream()
					.map(kg -> new CreateKindergartenCommand(kg.getId(), kg.getName().toUpperCase(), kg.getAddress(), kg.getSpotsInFirstAgeGroup(), kg.getSpotsInSecondAgeGroup()))
					.collect(Collectors.toList());
	}
	
	@Transactional
	public List<CreateKindergartenCommand> deleteKindergarten(long kgId, User user) {
		var admission = admissionDao.findAll().get(0);
		var kindergarten = kgDao.findById(kgId).orElse(null);
		if (kindergarten != null) {
			var adQueues = admission.getQueues();
			kindergarten.getQueues().forEach(queue -> {
				adQueues.remove(queue);
				var regs = queue.getRegistrations();
				regs.forEach(r -> {
					var queues = r.getQueues();
					queues.remove(queue);
					r.setQueues(queues);
					registrationDao.save(r);
				});
				regs.clear();
				queue.setRegistrations(regs);
				queue.setKindergarten(null);
				queue.setAdmissionProcess(null);
			});
			admission.setQueues(adQueues);
		}
		logDao.save(new Log(new Date(), user.getEmail(), user.getRole().toString(), "Ištrintas darželis: " + kindergarten.getName().toUpperCase()));
		kgDao.delete(kindergarten);
		return getAllKindergartens();
	}
	
	@Transactional
	public void updateKindergarten(CreateKindergartenCommand kindergarten, long kgId, User user) {
		Kindergarten kg = kgDao.findById(kgId).orElse(null);
		if (!(kg.getName().equals(kindergarten.getName()))) {
			var registrations = registrationDao.findRegistrationsWithSpecifiedKindergarten(kg.getName());
			registrations.forEach(reg -> {
				if (reg.getFirstPriority().equals(kg.getName())) {
					reg.setFirstPriority(kindergarten.getName().toUpperCase());
				}
				if (reg.getSecondPriority().equals(kg.getName())) {
					reg.setSecondPriority(kindergarten.getName().toUpperCase());
				}
				if (reg.getThirdPriority().equals(kg.getName())) {
					reg.setThirdPriority(kindergarten.getName().toUpperCase());
				}
				if (reg.getFourthPriority().equals(kg.getName())) {
					reg.setFourthPriority(kindergarten.getName().toUpperCase());
				}
				if (reg.getFifthPriority().equals(kg.getName())) {
					reg.setFifthPriority(kindergarten.getName().toUpperCase());
				}
				registrationDao.save(reg);
			});
		}
		kg.setName(kindergarten.getName().toUpperCase());
		kg.setAddress(kindergarten.getAddress());
		kg.setSpotsInFirstAgeGroup(kindergarten.getSpotsInFirstAgeGroup());
		kg.setSpotsInSecondAgeGroup(kindergarten.getSpotsInSecondAgeGroup());
		logDao.save(new Log(new Date(), user.getEmail(), user.getRole().toString(), "Pakoreguotas darželis: " + kindergarten.getName().toUpperCase()));
		queueService.createNewQueuesForKindergarten(kg, user);
	}
	
}
