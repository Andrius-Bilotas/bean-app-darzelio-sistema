package lt2021.projektas.child;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lt2021.projektas.kindergarten.registration.KindergartenRegistrationService;
import lt2021.projektas.logging.Log;
import lt2021.projektas.logging.LogDao;
import lt2021.projektas.parentdetails.ParentDetails;
import lt2021.projektas.parentdetails.ParentDetailsDao;
import lt2021.projektas.parentdetails.ServiceLayerDetails;
import lt2021.projektas.userRegister.User;
import lt2021.projektas.userRegister.UserDao;

@Service
public class ChildService {

	@Autowired
	private ChildDao childDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ParentDetailsDao detailsDao;

	@Autowired
	private KindergartenRegistrationService kgRegService;

	@Autowired
	private DBFileDao fileDao;
	
	@Autowired
	private LogDao logDao;

	@Transactional
	public ResponseEntity<String> addChild(Long parentId, ServiceLayerChild child) throws ParseException {
		User mainParent = userDao.findById(parentId).orElse(null);
		if (mainParent == null) {
			return new ResponseEntity<String>("Tėvas/globėjas neregistruotas sistemoje", HttpStatus.BAD_REQUEST);
		}
		if (new SimpleDateFormat("y-MM-dd").parse(child.getBirthdate()).after(new Date())) {
			logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Bandyta priregistruoti naują vaiką. Suvesta gimimo data iš ateities"));
			return new ResponseEntity<String>("Gimimo data negali būti iš ateities!", HttpStatus.BAD_REQUEST);
		}
		if (detailsDao.findByPersonalCode(child.getPersonalCode()).isPresent()) {
			logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Bandyta priregistruoti naują vaiką. Įvestas jau užimtas asmens kodas"));
			return new ResponseEntity<>("Šis asmens kodas jau egzistuoja sistemoje!", HttpStatus.BAD_REQUEST);
		} else if (child.getSecondParentDetails() != null) {
			if (child.getSecondParentDetails().getPersonalCode() == child.getPersonalCode()
					|| childDao.findByPersonalCode(child.getSecondParentDetails().getPersonalCode()).isPresent()
					|| mainParent.getParentDetails().getPersonalCode() == child.getSecondParentDetails()
							.getPersonalCode()) {
				logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Bandyta priregistruoti naują vaiką. Įvestas jau užimtas asmens kodas"));
				return new ResponseEntity<String>("Šis asmens kodas jau egzistuoja sistemoje!", HttpStatus.BAD_REQUEST);
			}
		}
		if (mainParent.getParentDetails() == null) {
			logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Bandyta priregistruoti naują vaiką. Neužpildyta tėvo registracijos forma"));
			return new ResponseEntity<>("Neužpildyta tėvo/globėjo registracijos forma!", HttpStatus.BAD_REQUEST);
		}
		if (mainParent != null) {
			Set<Child> childrenSet = mainParent.getParentDetails().getChildren();
			Child newChild = new Child(child.getFirstname(), child.getLastname(), child.getPersonalCode(),
					child.isAdopted(), new SimpleDateFormat("yyyy-MM-dd").parse(child.getBirthdate()),
					child.getLivingAddress());
			Set<ParentDetails> parentSet = newChild.getParents();
			parentSet.add(mainParent.getParentDetails());
			newChild.setParents(parentSet);
			childrenSet.add(newChild);
			mainParent.getParentDetails().setChildren(childrenSet);
			if (child.getSecondParentDetails() != null) {
				if (!(detailsDao.findByPersonalCode(child.getSecondParentDetails().getPersonalCode()).isPresent())) {
					if (child.getSecondParentDetails().isDeclaredResidenceSameAsLiving()) {
						ParentDetails secondParent = new ParentDetails(child.getSecondParentDetails().getFirstname(),
								child.getSecondParentDetails().getLastname(), child.getSecondParentDetails().getEmail(),
								child.getSecondParentDetails().getPhone(),
								child.getSecondParentDetails().getPersonalCode(),
								child.getSecondParentDetails().getLivingAddress(),
								child.getSecondParentDetails().getNumberOfKids(),
								child.getSecondParentDetails().isStudying(),
								child.getSecondParentDetails().getStudyingInstitution(),
								child.getSecondParentDetails().isHasDisability(),
								child.getSecondParentDetails().isDeclaredResidenceSameAsLiving(),
								child.getSecondParentDetails().getLivingAddress());
						childrenSet = secondParent.getChildren();
						childrenSet.add(newChild);
						secondParent.setChildren(childrenSet);
						parentSet.add(secondParent);
						newChild.setParents(parentSet);
						detailsDao.save(secondParent);
					} else {
						ParentDetails secondParent = new ParentDetails(child.getSecondParentDetails().getFirstname(),
								child.getSecondParentDetails().getLastname(), child.getSecondParentDetails().getEmail(),
								child.getSecondParentDetails().getPhone(),
								child.getSecondParentDetails().getPersonalCode(),
								child.getSecondParentDetails().getLivingAddress(),
								child.getSecondParentDetails().getNumberOfKids(),
								child.getSecondParentDetails().isStudying(),
								child.getSecondParentDetails().getStudyingInstitution(),
								child.getSecondParentDetails().isHasDisability(),
								child.getSecondParentDetails().isDeclaredResidenceSameAsLiving(),
								child.getSecondParentDetails().getDeclaredAddress());
						childrenSet = secondParent.getChildren();
						childrenSet.add(newChild);
						secondParent.setChildren(childrenSet);
						parentSet.add(secondParent);
						newChild.setParents(parentSet);
						detailsDao.save(secondParent);
					}
				} else {
					ParentDetails secondParent = detailsDao
							.findByPersonalCode(child.getSecondParentDetails().getPersonalCode()).get();
					childrenSet = secondParent.getChildren();
					childrenSet.add(newChild);
					secondParent.setChildren(childrenSet);
					parentSet.add(secondParent);
					newChild.setParents(parentSet);
					detailsDao.save(secondParent);
				}
			}

			mainParent = userDao.save(mainParent);
			var logInfo = mainParent.getParentDetails().getChildren().stream().filter(c -> c.getPersonalCode() == newChild.getPersonalCode()).findFirst().orElse(null);
			logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Užregistruotas naujas vaikas (id: " + logInfo.getId() + ") į sistemą"));
			return new ResponseEntity<String>("Vaiko duomenys išsaugoti", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Tėvas/globėjas neregistruotas sistemoje", HttpStatus.BAD_REQUEST);
	}

	@Transactional
	public List<ServiceLayerChild> getChildren(long parentId) throws ParseException {
		User parent = userDao.findById(parentId).orElse(null);
		if (parent != null) {
			if (parent.getParentDetails() == null || parent.getParentDetails().getChildren() == null) {
				return null;
			}
			Set<Child> children = parent.getParentDetails().getChildren();
			List<ServiceLayerChild> childArray = new ArrayList<>();
			for (Child ch : children) {
				ServiceLayerChild child = new ServiceLayerChild(ch.getId(), ch.getFirstname(), ch.getLastname(),
						ch.getPersonalCode(), ch.isAdopted(),
						new SimpleDateFormat("yyyy-MM-dd").format(ch.getBirthdate()), ch.getLivingAddress(), ch.getHealthRecord() == null ? 0 : ch.getHealthRecord().getId());
				ParentDetails secondParent = ch.getParents().stream()
						.filter(p -> !(p.getId().equals(parent.getParentDetails().getId()))).findFirst().orElse(null);
				if (secondParent != null) {
					child.setSecondParentDetails(new ServiceLayerDetails(secondParent.getId(),
							secondParent.getFirstname(), secondParent.getLastname(), secondParent.getEmail(),
							secondParent.getPhone(), secondParent.getPersonalCode(), secondParent.getLivingAddress(),
							secondParent.getNumberOfKids(), secondParent.isStudying(),
							secondParent.getStudyingInstitution(), secondParent.isHasDisability(),
							secondParent.isDeclaredResidenceSameAsLiving(), secondParent.getDeclaredAddress()));
				}
				childArray.add(child);
			}
			childArray.sort((c1, c2) -> {
				if (c1.getFirstname().equals(c2.getFirstname())) {
					return c1.getLastname().compareTo(c2.getLastname());
				} else {
					return c1.getFirstname().compareTo(c2.getFirstname());
				}
			});
			return childArray;
		} else {
			return null;
		}
	}

	@Transactional
	public CreateChildCommand getChildDetails(long parentId, long childId) throws ParseException {
		User currentParent = userDao.findById(parentId).orElse(null);
		if (currentParent != null) {
			Child currentChild = currentParent.getParentDetails().getChildren().stream()
					.filter(child -> child.getId().equals(childId)).findFirst().orElse(null);
			if (currentChild != null) {
				ParentDetails secondParent = currentChild.getParents().stream()
						.filter(parent -> !(parent.getId().equals(currentParent.getParentDetails().getId())))
						.findFirst().orElse(null);
				if (secondParent != null) {
					return new CreateChildCommand(currentChild.getId(), currentChild.getFirstname(),
							currentChild.getLastname(), currentChild.getPersonalCode(), currentChild.isAdopted(),
							new SimpleDateFormat("yyyy-MM-dd").format(currentChild.getBirthdate()),
							currentChild.getLivingAddress().getCity(), currentChild.getLivingAddress().getStreet(),
							currentChild.getLivingAddress().getHouseNumber(),
							currentChild.getLivingAddress().getFlatNumber(),
							currentChild.getHealthRecord() == null ? 0 : currentChild.getHealthRecord().getId(),
							true, secondParent.getId(),
							secondParent.getFirstname(), secondParent.getLastname(), secondParent.getEmail(),
							secondParent.getPhone(), secondParent.getPersonalCode(),
							secondParent.getLivingAddress().getCity(), secondParent.getLivingAddress().getStreet(),
							secondParent.getLivingAddress().getHouseNumber(),
							secondParent.getLivingAddress().getFlatNumber(), secondParent.getNumberOfKids(),
							secondParent.isStudying(), secondParent.getStudyingInstitution(),
							secondParent.isHasDisability(), secondParent.isDeclaredResidenceSameAsLiving(),
							secondParent.getDeclaredAddress().getCity(), secondParent.getDeclaredAddress().getStreet(),
							secondParent.getDeclaredAddress().getHouseNumber(),
							secondParent.getDeclaredAddress().getFlatNumber());
				} else {
					return new CreateChildCommand(currentChild.getId(), currentChild.getFirstname(),
							currentChild.getLastname(), currentChild.getPersonalCode(), currentChild.isAdopted(),
							new SimpleDateFormat("yyyy-MM-dd").format(currentChild.getBirthdate()),
							currentChild.getLivingAddress().getCity(), currentChild.getLivingAddress().getStreet(),
							currentChild.getLivingAddress().getHouseNumber(),
							currentChild.getLivingAddress().getFlatNumber(),
							currentChild.getHealthRecord() == null ? 0 : currentChild.getHealthRecord().getId(),
							false, 0L, "", "", "", "", 0L, "", "", "",
							"", 0, false, "", false, false, "", "", "", "");
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Transactional
	public ResponseEntity<String> updateChild(ServiceLayerChild updatedChild, long userId, long childId)
			throws ParseException {
		User mainParent = userDao.findById(userId).orElse(null);
		if (mainParent != null) {
			Child child = mainParent.getParentDetails().getChildren().stream().filter(ch -> ch.getId().equals(childId))
					.findFirst().orElse(null);
			if (detailsDao.findByPersonalCode(updatedChild.getPersonalCode()).isPresent()) {
				logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Bandyta atnaujinti vaiko (id: " + child.getId() + ") duomenis. Įvestas jau užimtas asmens kodas"));
				return new ResponseEntity<String>("Vaiko asmens kodas jau užimtas", HttpStatus.BAD_REQUEST);
			}
			if (new SimpleDateFormat("y-MM-dd").parse(updatedChild.getBirthdate()).after(new Date())) {
				logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Bandyta atnaujinti vaiko (id: " + child.getId() + ") duomenis. Įvesta ateities gimimo data"));
				return new ResponseEntity<String>("Gimimo data negali būti iš ateities!", HttpStatus.BAD_REQUEST);
			}
			if (updatedChild.getSecondParentDetails() != null) {
				if (updatedChild.getPersonalCode() == updatedChild.getSecondParentDetails().getPersonalCode()) {
					logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Bandyta atnaujinti vaiko (id: " + child.getId() + ") duomenis. Sutapo vaiko ir tėvo asmens kodas"));
					return new ResponseEntity<String>("Vaiko ir tėvo asmens kodai negali sutapti",
							HttpStatus.BAD_REQUEST);
				}
				if (childDao.findByPersonalCode(updatedChild.getSecondParentDetails().getPersonalCode()).isPresent()) {
					logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Bandyta atnaujinti vaiko (id: " + child.getId() + ") duomenis. Antro tėvo asmens kodas jau užimtas"));
					return new ResponseEntity<String>("Tėvo asmens kodas jau užimtas", HttpStatus.BAD_REQUEST);
				}
				child.setFirstname(updatedChild.getFirstname());
				child.setLastname(updatedChild.getLastname());
				child.setPersonalCode(updatedChild.getPersonalCode());
				child.setAdopted(updatedChild.isAdopted());
				child.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(updatedChild.getBirthdate()));
				child.setLivingAddress(updatedChild.getLivingAddress());
				ParentDetails secondParent = detailsDao.findById(updatedChild.getSecondParentDetails().getId())
						.orElse(null);
				if (secondParent != null) {
					secondParent.setFirstname(updatedChild.getSecondParentDetails().getFirstname());
					secondParent.setLastname(updatedChild.getSecondParentDetails().getLastname());
					secondParent.setEmail(updatedChild.getSecondParentDetails().getEmail());
					secondParent.setPhone(updatedChild.getSecondParentDetails().getPhone());
					secondParent.setPersonalCode(updatedChild.getSecondParentDetails().getPersonalCode());
					secondParent.setLivingAddress(updatedChild.getSecondParentDetails().getLivingAddress());
					secondParent.setNumberOfKids(updatedChild.getSecondParentDetails().getNumberOfKids());
					secondParent.setStudying(updatedChild.getSecondParentDetails().isStudying());
					secondParent.setStudyingInstitution(updatedChild.getSecondParentDetails().getStudyingInstitution());
					secondParent.setHasDisability(updatedChild.getSecondParentDetails().isHasDisability());
					secondParent.setDeclaredResidenceSameAsLiving(
							updatedChild.getSecondParentDetails().isDeclaredResidenceSameAsLiving());
					secondParent.setDeclaredAddress(updatedChild.getSecondParentDetails().getDeclaredAddress());
					detailsDao.save(secondParent);
					kgRegService.updateRegistrationOnParentOrChildInfoChange(childDao.save(child).getId());
					logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Atnaujinti vaiko (id: " + child.getId() + ") duomenys."));
					return new ResponseEntity<String>("Vaiko duomenys atnaujinti", HttpStatus.OK);
				} else {
					if (updatedChild.getSecondParentDetails().isDeclaredResidenceSameAsLiving()) {
						secondParent = new ParentDetails(updatedChild.getSecondParentDetails().getFirstname(),
								updatedChild.getSecondParentDetails().getLastname(),
								updatedChild.getSecondParentDetails().getEmail(),
								updatedChild.getSecondParentDetails().getPhone(),
								updatedChild.getSecondParentDetails().getPersonalCode(),
								updatedChild.getSecondParentDetails().getLivingAddress(),
								updatedChild.getSecondParentDetails().getNumberOfKids(),
								updatedChild.getSecondParentDetails().isStudying(),
								updatedChild.getSecondParentDetails().getStudyingInstitution(),
								updatedChild.getSecondParentDetails().isHasDisability(),
								updatedChild.getSecondParentDetails().isDeclaredResidenceSameAsLiving(),
								updatedChild.getSecondParentDetails().getLivingAddress());
						var childrenSet = secondParent.getChildren();
						childrenSet.add(child);
						secondParent.setChildren(childrenSet);
						var parentSet = child.getParents();
						parentSet.add(secondParent);
						child.setParents(parentSet);
						detailsDao.save(secondParent);
						kgRegService.updateRegistrationOnParentOrChildInfoChange(childDao.save(child).getId());
						logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Atnaujinti vaiko (id: " + child.getId() + ") duomenys."));
						return new ResponseEntity<String>("Vaiko duomenys atnaujinti", HttpStatus.OK);
					} else {
						secondParent = new ParentDetails(updatedChild.getSecondParentDetails().getFirstname(),
								updatedChild.getSecondParentDetails().getLastname(),
								updatedChild.getSecondParentDetails().getEmail(),
								updatedChild.getSecondParentDetails().getPhone(),
								updatedChild.getSecondParentDetails().getPersonalCode(),
								updatedChild.getSecondParentDetails().getLivingAddress(),
								updatedChild.getSecondParentDetails().getNumberOfKids(),
								updatedChild.getSecondParentDetails().isStudying(),
								updatedChild.getSecondParentDetails().getStudyingInstitution(),
								updatedChild.getSecondParentDetails().isHasDisability(),
								updatedChild.getSecondParentDetails().isDeclaredResidenceSameAsLiving(),
								updatedChild.getSecondParentDetails().getDeclaredAddress());
						var childrenSet = secondParent.getChildren();
						childrenSet.add(child);
						secondParent.setChildren(childrenSet);
						var parentSet = child.getParents();
						parentSet.add(secondParent);
						child.setParents(parentSet);
						detailsDao.save(secondParent);
						kgRegService.updateRegistrationOnParentOrChildInfoChange(childDao.save(child).getId());
						logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Atnaujinti vaiko (id: " + child.getId() + ") duomenys."));
						return new ResponseEntity<String>("Vaiko duomenys atnaujinti", HttpStatus.OK);
					}
				}
			} else {
				child.setFirstname(updatedChild.getFirstname());
				child.setLastname(updatedChild.getLastname());
				child.setPersonalCode(updatedChild.getPersonalCode());
				child.setAdopted(updatedChild.isAdopted());
				child.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(updatedChild.getBirthdate()));
				child.setLivingAddress(updatedChild.getLivingAddress());
				kgRegService.updateRegistrationOnParentOrChildInfoChange(childDao.save(child).getId());
				logDao.save(new Log(new Date(), mainParent.getEmail(), mainParent.getRole().toString(), "Atnaujinti vaiko (id: " + child.getId() + ") duomenys."));
				return new ResponseEntity<String>("Vaiko duomenys atnaujinti", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Tėvas/globėjas neregistruotas sistemoje", HttpStatus.BAD_REQUEST);
	}

	@Transactional
	public void deleteChild(long userId, long childId) {
		User parent = userDao.findById(userId).orElse(null);
		if (parent != null) {
			ParentDetails details = parent.getParentDetails();
			Set<Child> childList = details.getChildren();
			Child childToDelete = childList.stream().filter(ch -> ch.getId().equals(childId)).findFirst().orElse(null);
			if (childToDelete != null) {
				var parents = childToDelete.getParents();
				parents.clear();
				childToDelete.setParents(parents);
				childList.remove(childToDelete);
				details.setChildren(childList);
				userDao.save(parent);
				kgRegService.deleteRegistration(childId, parent);
				if (childToDelete.getHealthRecord() != null) {
					fileDao.delete(childToDelete.getHealthRecord());
				}
				childDao.delete(childToDelete);
				logDao.save(new Log(new Date(), parent.getEmail(), parent.getRole().toString(), "Ištrinti vaiko (id: " + childId + ") duomenys."));
			}
		}
	}

	@Transactional
	public ResponseEntity<String> uploadHealthRecord(MultipartFile file, long childId) {
		var child = childDao.findById(childId).orElse(null);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (!(fileName.contains(".."))) {
				if (!file.getContentType().equals("application/pdf")) {
					return new ResponseEntity<String>("Blogas failo formatas", HttpStatus.BAD_REQUEST);
				}
				DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
				child.setHealthRecord(dbFile);
				fileDao.save(dbFile);
				var parent = child.getParents().stream().filter(p -> p.getParent() != null).findFirst().orElse(null);
				logDao.save(new Log(new Date(), parent.getParent().getEmail(), parent.getParent().getRole().toString(), "Įkelta vaiko (id: " + childId + ") sveikatos pažyma."));
				return new ResponseEntity<String>("Failas įkeltas sėkmingai", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Blogas failo formatas", HttpStatus.BAD_REQUEST);
			}
		} catch (IOException ex) {
			return new ResponseEntity<String>("Blogas failo formatas", HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional 
	public DBFile getHealthRecord(long childId) {
		var child = childDao.findById(childId).orElse(null);
		if (child != null) {
			return child.getHealthRecord();
		} else {
			return null;
		}
	}
	
	@Transactional
	public ResponseEntity<String> deleteHealthRecord(long childId) {
		var child = childDao.findById(childId).orElse(null);
		if (child != null) {
			var dbfile = child.getHealthRecord();
			child.setHealthRecord(null);
			dbfile.setChild(null);
			fileDao.delete(dbfile);
			var parent = child.getParents().stream().filter(p -> p.getParent() != null).findFirst().orElse(null);
			logDao.save(new Log(new Date(), parent.getParent().getEmail(), parent.getParent().getRole().toString(), "Ištrinta vaiko (id: " + childId + ") sveikatos pažyma."));
			return new ResponseEntity<String>("Failas ištrintas sėkmingai", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Įvyko klaida", HttpStatus.BAD_REQUEST);
	}

}
