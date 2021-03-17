package lt2021.projektas.kindergarten;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lt2021.projektas.kindergarten.registration.CreateRegistrationCommand;
import lt2021.projektas.kindergarten.registration.KindergartenRegistrationService;
import lt2021.projektas.userRegister.UserService;

@RestController
@RequestMapping(value = "/api/kindergartens")
public class KindergartenController {
	
	@Autowired
	private KindergartenService kgService;
	
	@Autowired
	private KindergartenRegistrationService kgRegService;
	
	@Autowired
	private UserService userService;
	
	/*
	 * Kindergarten CRUD
	 */
	
	@RequestMapping(path = "/{kgId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_EDU') or hasRole('ROLE_ADMIN')")
	public CreateKindergartenCommand getKindergarten(@PathVariable final long kgId) {
		return kgService.getKindergarten(kgId);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_EDU') or hasRole('ROLE_ADMIN') or hasRole('ROLE_PARENT')")
	public List<CreateKindergartenCommand> getAllKindergartens() {
		return kgService.getAllKindergartens();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_EDU') or hasRole('ROLE_ADMIN')")
	public void createKindergarten(@RequestBody final CreateKindergartenCommand kg) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		kgService.addKindergarten(kg, user);
	}
	
	@RequestMapping(path = "/{kgId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_EDU') or hasRole('ROLE_ADMIN')")
	public void updateKindergarten(@RequestBody final CreateKindergartenCommand kg, @PathVariable final long kgId) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		kgService.updateKindergarten(kg, kgId, user);
	}
	
	@RequestMapping(path = "/{kgId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_EDU') or hasRole('ROLE_ADMIN')")
	public List<CreateKindergartenCommand> deleteKinderkarten(@PathVariable final long kgId) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return kgService.deleteKindergarten(kgId, user);
	}
	
	/*
	 * Kindergarten registration CRUD
	 */
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ADMIN')")
	public ResponseEntity<String> addRegistration(@RequestBody final CreateRegistrationCommand reg) {
		return kgRegService.addRegistration(reg);
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_EDU') or hasRole('ROLE_ADMIN')")
	public List<CreateRegistrationCommand> getAllRegistrations() {
		return kgRegService.getAllRegistrations();
	}
	
	@RequestMapping(path = "/register/{childId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ADMIN') or hasRole('ROLE_EDU')")
	public CreateRegistrationCommand getChildRegistration(@PathVariable("childId") final long childId) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getParentDetails() != null) {
			if (!user.getParentDetails().getChildren().stream().anyMatch(child -> child.getId() == childId)) {
				return null;
			} 
		} else if (user.getRole().toString().equals("ADMIN") || user.getRole().toString().equals("EDU")) {
			return kgRegService.getChildRegistration(childId);
		}
		return kgRegService.getChildRegistration(childId);
	}
	
	@RequestMapping(path = "/register/{childId}/update", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ADMIN')")
	public void updateRegistration(@RequestBody final CreateRegistrationCommand reg) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		kgRegService.updateRegistration(reg, user);
	}
	
	@RequestMapping(path = "/register/{childId}/delete", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ADMIN')")
	public void deleteRegistration(@PathVariable("childId") final long childId) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getParentDetails() != null || user.getRole().toString().equals("ADMIN")) {
			if (user.getParentDetails().getChildren().stream().anyMatch(child -> child.getId() == childId)) {
				kgRegService.deleteRegistration(childId, user);
			} else if (user.getRole().toString().equals("ADMIN")) {
				kgRegService.deleteRegistration(childId, user);
			}
		}
	}
	
	
}
