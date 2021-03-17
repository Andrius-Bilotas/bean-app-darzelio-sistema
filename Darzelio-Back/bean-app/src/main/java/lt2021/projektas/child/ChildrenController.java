package lt2021.projektas.child;

import java.text.ParseException;
import java.util.ArrayList;
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

import lt2021.projektas.parentdetails.Address;
import lt2021.projektas.parentdetails.ServiceLayerDetails;
import lt2021.projektas.userRegister.UserService;

@RestController
@RequestMapping(value = "/api/users/{userId}/parentdetails/children")
public class ChildrenController {
	
	@Autowired
	private ChildService childService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> addChild(@RequestBody final CreateChildCommand child, @PathVariable("userId") final long id) throws ParseException {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getId() != id && !user.getRole().toString().equals("ADMIN")) {
			return new ResponseEntity<String>("Pateiktas svetimo vartotojo ID", HttpStatus.BAD_REQUEST);
		}
		if (!child.isSecondParent()) {
			return childService.addChild(id, new ServiceLayerChild(child.getFirstname(), child.getLastname(), child.getPersonalCode(), child.isAdopted(), 
					child.getBirthdate(), new Address(child.getCity(), child.getStreet(), child.getHouseNumber(), child.getFlatNumber())));
		} else {
			return childService.addChild(id, new ServiceLayerChild(child.getFirstname(), child.getLastname(), child.getPersonalCode(), child.isAdopted(), 
					child.getBirthdate(), new Address(child.getCity(), child.getStreet(), child.getHouseNumber(), child.getFlatNumber()), new ServiceLayerDetails(child.getSecondParentFirstname(), 
							child.getSecondParentLastname(), child.getSecondParentEmail(), child.getSecondParentPhone(), child.getSecondParentPersonalCode(), 
							new Address(child.getSecondParentCity(), child.getSecondParentStreet(), child.getSecondParentHouseNumber(), child.getSecondParentFlatNumber()), 
							child.getSecondParentNumberOfKids(), child.isSecondParentStudying(), child.getSecondParentStudyingInstitution(), child.isSecondParentHasDisability(), 
							child.isSecondParentDeclaredResidenceSameAsLiving(), new Address(child.getSecondParentDeclaredCity(), child.getSecondParentDeclaredStreet(), 
									child.getSecondParentDeclaredHouseNumber(), child.getSecondParentDeclaredFlatNumber()))));
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ADMIN')")
	public List<CreateChildCommand> getChildren(@PathVariable("userId") final long id) throws ParseException {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getId() != id && !user.getRole().toString().equals("ADMIN")) {
			return null;
		}
		List<ServiceLayerChild> children = childService.getChildren(id);
		List<CreateChildCommand> finalChildren = new ArrayList<>();
		for (ServiceLayerChild child: children) {
			if (child.getSecondParentDetails() != null) {
				finalChildren.add(new CreateChildCommand(child.getId(), child.getFirstname(), child.getLastname(), child.getPersonalCode(), child.isAdopted(), 
						child.getBirthdate(), child.getLivingAddress().getCity(), child.getLivingAddress().getStreet(), child.getLivingAddress().getHouseNumber(), 
						child.getLivingAddress().getFlatNumber(), 0L, true, child.getSecondParentDetails().getId(), child.getSecondParentDetails().getFirstname(), 
						child.getSecondParentDetails().getLastname(), child.getSecondParentDetails().getEmail(), child.getSecondParentDetails().getPhone(), 
						child.getSecondParentDetails().getPersonalCode(), child.getSecondParentDetails().getLivingAddress().getCity(), 
						child.getSecondParentDetails().getLivingAddress().getStreet(), child.getSecondParentDetails().getLivingAddress().getHouseNumber(), 
						child.getSecondParentDetails().getLivingAddress().getFlatNumber(), child.getSecondParentDetails().getNumberOfKids(), 
						child.getSecondParentDetails().isStudying(), child.getSecondParentDetails().getStudyingInstitution(), child.getSecondParentDetails().isHasDisability(), 
						child.getSecondParentDetails().isDeclaredResidenceSameAsLiving(), child.getSecondParentDetails().getDeclaredAddress().getCity(), 
						child.getSecondParentDetails().getDeclaredAddress().getStreet(), child.getSecondParentDetails().getDeclaredAddress().getHouseNumber(), 
						child.getSecondParentDetails().getDeclaredAddress().getFlatNumber()));
			} else {
				finalChildren.add(new CreateChildCommand(child.getId(), child.getFirstname(), child.getLastname(), child.getPersonalCode(), child.isAdopted(), 
						child.getBirthdate(), child.getLivingAddress().getCity(), child.getLivingAddress().getStreet(), child.getLivingAddress().getHouseNumber(), 
						child.getLivingAddress().getFlatNumber(), 0L, false, 0L, "", "", "", "", 0L, "", "", "", "", 0, false, "", false, false, "", "", "", ""));
			}
		}
		return finalChildren;
	}
	
	
	@RequestMapping(path = "/{childId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ADMIN')")
	public CreateChildCommand getChildDetails(@PathVariable("userId") final long userId, @PathVariable("childId") final long childId) throws ParseException {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getId() != userId && !user.getRole().toString().equals("ADMIN")) {
			return null;
		} else if (user.getParentDetails() != null) {
			if (!user.getParentDetails().getChildren().stream().anyMatch(child -> child.getId() == childId)) {
				return null;
			}
		}
		return childService.getChildDetails(userId, childId);
	}
	

	@RequestMapping(path = "/{childId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateChild(@RequestBody final CreateChildCommand child, @PathVariable("userId") final long userId, @PathVariable("childId") final long childId) throws ParseException {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getId() != userId && !user.getRole().toString().equals("ADMIN")) {
			return new ResponseEntity<String>("Pateiktas svetimo vartotojo ID", HttpStatus.BAD_REQUEST);
		} else if (user.getParentDetails() != null) {
			if (!user.getParentDetails().getChildren().stream().anyMatch(ch -> ch.getId() == childId)) {
				return new ResponseEntity<String>("Pateiktas svetimo vartotojo vaiko ID", HttpStatus.BAD_REQUEST);
			}
		}
		if (!(child.isSecondParent())) {
			return childService.updateChild(new ServiceLayerChild(child.getFirstname(), child.getLastname(), child.getPersonalCode(), child.isAdopted(), 
					child.getBirthdate(), new Address(child.getCity(), child.getStreet(), child.getHouseNumber(), child.getFlatNumber())), userId, childId);
		} else {
			return childService.updateChild(new ServiceLayerChild(child.getFirstname(), child.getLastname(), child.getPersonalCode(), child.isAdopted(), 
					child.getBirthdate(), new Address(child.getCity(), child.getStreet(), child.getHouseNumber(), child.getFlatNumber()), new ServiceLayerDetails(child.getSecondParentId(),
							child.getSecondParentFirstname(), child.getSecondParentLastname(), child.getSecondParentEmail(), child.getSecondParentPhone(), child.getSecondParentPersonalCode(), 
							new Address(child.getSecondParentCity(), child.getSecondParentStreet(), child.getSecondParentHouseNumber(), child.getSecondParentFlatNumber()), 
							child.getSecondParentNumberOfKids(), child.isSecondParentStudying(), child.getSecondParentStudyingInstitution(), child.isSecondParentHasDisability(), 
							child.isSecondParentDeclaredResidenceSameAsLiving(), new Address(child.getSecondParentDeclaredCity(), child.getSecondParentDeclaredStreet(), 
									child.getSecondParentDeclaredHouseNumber(), child.getSecondParentDeclaredFlatNumber()))), userId, childId);
		}
	}
	
	@RequestMapping(path = "/{childId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ADMIN')")
	public void deleteChild(@PathVariable("userId") final long userId, @PathVariable("childId") final long childId) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getId() == userId || user.getRole().toString().equals("ADMIN")) {
			if (user.getParentDetails() != null) {
				if (user.getParentDetails().getChildren().stream().anyMatch(ch -> ch.getId() == childId)) {
					childService.deleteChild(userId, childId);
				}
			}
		}
	}
	

}
