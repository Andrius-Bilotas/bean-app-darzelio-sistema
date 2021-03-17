package lt2021.projektas.kindergarten.registration;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt2021.projektas.child.ChildDao;
import lt2021.projektas.kindergarten.admission.AdmissionDao;
import lt2021.projektas.kindergarten.admission.AdmissionService;
import lt2021.projektas.kindergarten.queue.QueueDao;
import lt2021.projektas.kindergarten.queue.QueueService;
import lt2021.projektas.logging.Log;
import lt2021.projektas.logging.LogDao;
import lt2021.projektas.userRegister.User;

@Service
public class KindergartenRegistrationService {
	
	@Autowired
	private KindergartenRegistrationDao kgRegDao;
	
	@Autowired
	private ChildDao childDao;
	
	@Autowired
	private QueueService queueService;
	
	@Autowired
	private QueueDao queueDao;
	
	@Autowired
	private AdmissionDao admissionDao;
	
	@Autowired
	private AdmissionService admissionService;
	
	@Autowired
	private LogDao logDao;
	
	
	@Transactional
	public ResponseEntity<String> addRegistration(CreateRegistrationCommand registrationForm) {
		var child = childDao.findById(registrationForm.getChildId()).orElse(null);
		if (child != null) {
			if (child.getRegistrationForm() == null) {
				if (admissionService.areAdmissionsActive()) {
					var registration = new KindergartenRegistration(child, registrationForm.getFirstPriority(), registrationForm.getSecondPriority(), 
							registrationForm.getThirdPriority(), registrationForm.getFourthPriority(), registrationForm.getFifthPriority());
					if (child.getLivingAddress().getCity().toLowerCase().contains("viln")) {
						registration.setRating(registration.getRating() + 5);
					}
					if (child.isAdopted()) {
						registration.setRating(registration.getRating() + 1);
					}
					if (!(child.getParents().stream().filter(parent -> parent.isStudying()).findFirst().isEmpty())) {
						registration.setRating(registration.getRating() + 1);
					}
					if (!(child.getParents().stream().filter(parent -> parent.getNumberOfKids() >= 3).findFirst().isEmpty())) {
						registration.setRating(registration.getRating() + 1);
					}
					if (!(child.getParents().stream().filter(parent -> parent.isHasDisability()).findFirst().isEmpty())) {
						registration.setRating(registration.getRating() + 1);
					}
					child.setRegistrationForm(registration);
					var user = child.getParents().stream().filter(p -> p.getParent() != null).findFirst().orElse(null);
					queueService.addRegistrationToQueues(kgRegDao.save(registration));
					logDao.save(new Log(new Date(), user.getParent().getEmail(), user.getParent().getRole().toString(),
							"Sukurta nauja registracijos forma vaikui: " + child.getFirstname() + " " + child.getLastname()));
					return new ResponseEntity<String>("Vaiko registracija išsaugota", HttpStatus.OK);
				}
				return new ResponseEntity<String>("Registracijos šiuo metu užrakintos", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Šio vaiko registracija jau užpildyta!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Vaikas nerastas sistemoje", HttpStatus.BAD_REQUEST);
	}
	
	@Transactional
	public void updateRegistrationOnParentOrChildInfoChange(long childId) {
		var updatedChild = childDao.findById(childId).orElse(null);
		var childRegistration = updatedChild.getRegistrationForm();
		if (childRegistration != null) {
			if (childRegistration.getAcceptedKindergarten() == null) {
				childRegistration.setRating(0);
				if (updatedChild.getLivingAddress().getCity().toLowerCase().contains("viln")) {
					childRegistration.setRating(childRegistration.getRating() + 5);
				}
				if (updatedChild.isAdopted()) {
					childRegistration.setRating(childRegistration.getRating() + 1);
				}
				if (!(updatedChild.getParents().stream().filter(parent -> parent.isStudying()).findFirst().isEmpty())) {
					childRegistration.setRating(childRegistration.getRating() + 1);
				}
				if (!(updatedChild.getParents().stream().filter(parent -> parent.getNumberOfKids() >= 3).findFirst().isEmpty())) {
					childRegistration.setRating(childRegistration.getRating() + 1);
				}
				if (!(updatedChild.getParents().stream().filter(parent -> parent.isHasDisability()).findFirst().isEmpty())) {
					childRegistration.setRating(childRegistration.getRating() + 1);
				}
				kgRegDao.save(childRegistration);
			}
		}
	}
	
	@Transactional
	public void updateRegistration(CreateRegistrationCommand updatedRegistration, User user) {
		//var admission = admissionDao.findAll().get(0);
		var registration = kgRegDao.findById(updatedRegistration.getId()).orElse(null);
		if (registration.getAcceptedKindergarten() == null) {
			registration.setFirstPriority(updatedRegistration.getFirstPriority());
			registration.setSecondPriority(updatedRegistration.getSecondPriority());
			registration.setThirdPriority(updatedRegistration.getThirdPriority());
			registration.setFourthPriority(updatedRegistration.getFourthPriority());
			registration.setFifthPriority(updatedRegistration.getFifthPriority());
			registration.getQueues().forEach(queue -> {
				var regs = queue.getRegistrations();
				regs.remove(registration);
				queue.setRegistrations(regs);
			});
			queueService.addRegistrationToQueues(registration);
			//var user = registration.getChild().getParents().stream().filter(p -> p.getParent() != null).findFirst().orElse(null);
			logDao.save(new Log(new Date(), user.getEmail(), user.getRole().toString(),
					"Pakoreguota registracijos forma vaikui: " + registration.getChild().getFirstname() + " " + registration.getChild().getLastname()));
		}
	}
	
	@Transactional
	public List<CreateRegistrationCommand> getAllRegistrations() {
		return kgRegDao.findAll().stream()
					.map(reg -> new CreateRegistrationCommand(reg.getId(), reg.getChild().getId(), reg.getFirstPriority(), 
							reg.getSecondPriority(), reg.getThirdPriority(), reg.getFourthPriority(), reg.getFifthPriority(), reg.getRating()))
					.collect(Collectors.toList());
	}
	
	@Transactional
	public CreateRegistrationCommand getChildRegistration(long childId) {
		var child = childDao.findById(childId).orElse(null);
		if (child != null) {
			var reg = kgRegDao.findByChild(child).orElse(null);
			return new CreateRegistrationCommand(reg.getId(), reg.getChild().getId(), reg.getFirstPriority(), reg.getSecondPriority(),
					reg.getThirdPriority(), reg.getFourthPriority(), reg.getFifthPriority(), reg.getRating());
		} else {
			return null;
		}
	}
	
	@Transactional
	public List<CreateRegistrationCommand> getRegistrationsWithSpecifiedKindergarten(String kindergartenName) {
		return kgRegDao.findRegistrationsWithSpecifiedKindergarten(kindergartenName).stream()
					.map(kgReg -> new CreateRegistrationCommand(kgReg.getId(), kgReg.getChild().getId(), kgReg.getFirstPriority(), kgReg.getSecondPriority(),
							kgReg.getThirdPriority(), kgReg.getFourthPriority(), kgReg.getFifthPriority(), kgReg.getRating()))
					.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteRegistration(long childId, User user) {
		var admission = admissionDao.findAll().get(0);
		var child = childDao.findById(childId).orElse(null);
		var registration = child.getRegistrationForm();
		if (registration != null) {
			
			registration.getQueues().forEach(queue -> {
				var regs = queue.getRegistrations();
				regs.remove(registration);
				queue.setRegistrations(regs);
				queueDao.save(queue);
			});
			var adRegs = admission.getRegistrations();
			adRegs.remove(registration);
			admission.setRegistrations(adRegs);
			admissionDao.save(admission);
			child.setRegistrationForm(null);
			kgRegDao.delete(registration);
			logDao.save(new Log(new Date(),user.getEmail(), user.getRole().toString(),
					"Ištrinta registracijos forma vaikui: " + child.getFirstname() + " " + child.getLastname()));
		}
	}
	
}
