package lt2021.projektas.parentdetails;

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

import lt2021.projektas.userRegister.UserService;

@RestController
@RequestMapping(value = "/api/users/{id}/parentdetails")
public class ParentDetailsController {
	
	@Autowired
	private ParentDetailsService detailsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ADMIN')")
	public CreateDetailsCommand getParentDetails(@PathVariable final long id) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getId() != id && !user.getRole().toString().equals("ADMIN")) {
			return null;
		}
		ServiceLayerDetails parent = detailsService.getParentDetails(id);
		return new CreateDetailsCommand(parent.getId(), parent.getFirstname(), parent.getLastname(), parent.getEmail(), parent.getPhone(), parent.getPersonalCode(), parent.getLivingAddress().getCity(), 
				parent.getLivingAddress().getStreet(), parent.getLivingAddress().getHouseNumber(), parent.getLivingAddress().getFlatNumber(), 
				parent.getNumberOfKids(), parent.isStudying(), parent.getStudyingInstitution(), parent.isHasDisability(), parent.isDeclaredResidenceSameAsLiving(), 
				parent.getDeclaredAddress().getCity(), parent.getDeclaredAddress().getStreet(), parent.getDeclaredAddress().getHouseNumber(), parent.getDeclaredAddress().getFlatNumber());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> addParentDetails(@RequestBody final CreateDetailsCommand details, @PathVariable final long id) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getId() != id && !user.getRole().toString().equals("ADMIN")) {
			return new ResponseEntity<String>("Pateiktas svetimo vartotojo ID", HttpStatus.BAD_REQUEST);
		}
		if (details.isDeclaredResidenceSameAsLiving()) {
			return detailsService.addParentDetails(new ServiceLayerDetails(id, details.getFirstname(), details.getLastname(), details.getEmail(), details.getPhone(), details.getPersonalCode(),
					new Address(details.getCity(), details.getStreet(), 
					details.getHouseNumber(), details.getFlatNumber()), details.getNumberOfKids(), details.isStudying(), details.getStudyingInstitution(), 
					details.isHasDisability(), details.isDeclaredResidenceSameAsLiving(), new Address(details.getCity(), details.getStreet(), 
							details.getHouseNumber(), details.getFlatNumber())));
		} else {
			return detailsService.addParentDetails(new ServiceLayerDetails(id, details.getFirstname(), details.getLastname(), details.getEmail(), details.getPhone(), details.getPersonalCode(),
					new Address(details.getCity(), details.getStreet(), 
					details.getHouseNumber(), details.getFlatNumber()), details.getNumberOfKids(), details.isStudying(), details.getStudyingInstitution(), 
					details.isHasDisability(), details.isDeclaredResidenceSameAsLiving(), new Address(details.getDeclaredCity(), details.getDeclaredStreet(), 
							details.getDeclaredHouseNumber(), details.getDeclaredFlatNumber())));
		}
		
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateParentDetails(@RequestBody final CreateDetailsCommand details, @PathVariable final long id) {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getId() != id && !user.getRole().toString().equals("ADMIN")) {
			return new ResponseEntity<String>("Pateiktas svetimo vartotojo ID", HttpStatus.BAD_REQUEST);
		}
		return detailsService.updateParentDetails(new ServiceLayerDetails(details.getId(), details.getFirstname(), details.getLastname(), details.getEmail(), details.getPhone(), details.getPersonalCode(),
				new Address(details.getCity(), details.getStreet(), 
				details.getHouseNumber(), details.getFlatNumber()), details.getNumberOfKids(), details.isStudying(), details.getStudyingInstitution(), 
				details.isHasDisability(), details.isDeclaredResidenceSameAsLiving(), new Address(details.getDeclaredCity(), details.getDeclaredStreet(), 
						details.getDeclaredHouseNumber(), details.getDeclaredFlatNumber())), id);
	}
}
