package lt2021.projektas.userRegister;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt2021.projektas.child.ChildService;
import lt2021.projektas.child.CreateChildCommand;
import lt2021.projektas.child.DBFile;
import lt2021.projektas.child.ServiceLayerChild;
import lt2021.projektas.kindergarten.KindergartenStatisticsObject;
import lt2021.projektas.logging.LogService;
import lt2021.projektas.logging.LogTableObject;
import lt2021.projektas.parentdetails.CreateDetailsCommand;
import lt2021.projektas.parentdetails.ParentDetailsService;
import lt2021.projektas.passwordreset.PasswordResetDTO;
import lt2021.projektas.userRegister.archive.UserArchiveObject;
import lt2021.projektas.userRegister.archive.UserArchiveService;

@RestController
@Api(value = "users")
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChildService childService;

	@Autowired
	private ParentDetailsService detailsService;
	
	@Autowired
	private UserArchiveService archiveService;
	
	@Autowired
	private LogService logService;

	@RequestMapping(method = RequestMethod.GET, params = {"page"})
	@ApiOperation(value = "Get users list", notes = "Returns all users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserTableObject getUsers(@RequestParam("page") int pageNumber) {
		return userService.getUsers(pageNumber, "");
	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"page", "email"})
	@ApiOperation(value = "Get users list", notes = "Returns all users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserTableObject getUsers(@RequestParam("page") int pageNumber, @RequestParam("email") String email) {
		return userService.getUsers(pageNumber, email);
	}

	@RequestMapping(path = "/{userId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARENT') or hasRole('ROLE_EDU')")
	public ServiceLayerUser getSingleUser(@PathVariable final long userId) {
		return userService.getSingleUser(userId);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create user", notes = "Creates users with data")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserTableObject createUser(
			@ApiParam(value = "User Data", required = true) @RequestBody final CreateUserCommand user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		var admin = userService.findByEmail(auth.getName());
		userService.createUser(user, admin);
		return userService.getUsers(1, "");
	}

	@RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARENT') or hasRole('ROLE_EDU')")
	public void updateUser(@PathVariable final long userId, @Valid @RequestBody final CreateUserCommand user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		var loggedUser = userService.findByEmail(auth.getName());
		userService.updateUser(new ServiceLayerUser(userId, user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getPassword(), user.getRole()), loggedUser);
	}

//	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete user", notes = "Deletes user by id")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(@PathVariable final Long userId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		var loggedUser = userService.findByEmail(auth.getName());
		userService.deleteUser(userId, loggedUser);
	}
	
	@RequestMapping(path = "/delete", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_EDU')")
	public void deletedLoggedUser(@RequestParam("eraseData") boolean eraseData) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		var loggedUser = userService.findByEmail(auth.getName());
		userService.deleteUser(loggedUser, eraseData);
	}

	@RequestMapping(path = "/loggedrole", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_EDU') or hasRole('ROLE_ADMIN')")
	public String getLoggedRole() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String currentRole = userService.findByEmail(auth.getName()).getRole().toString();
			return currentRole;
		}
		return "not logged";
	}

	@RequestMapping(path = "/loggeduserid", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_EDU') or hasRole('ROLE_ADMIN')")
	public Long getLoggedInUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.findByEmail(auth.getName());
			return user.getId();
		}
		return null;
	}

	@RequestMapping(path = "/getloggeduserchildren", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public List<CreateChildCommand> getLoggedInUserChildren() throws ParseException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.findByEmail(auth.getName());
			List<ServiceLayerChild> usersChildren = childService.getChildren(user.getId());
			if (usersChildren == null) {
				return null;
			}
			List<CreateChildCommand> finalChildren = new ArrayList<>();
			for (ServiceLayerChild child : usersChildren) {
				if (child.getSecondParentDetails() != null) {
					finalChildren.add(new CreateChildCommand(child.getId(), child.getFirstname(), child.getLastname(),
							child.getPersonalCode(), child.isAdopted(), child.getBirthdate(),
							child.getLivingAddress().getCity(), child.getLivingAddress().getStreet(),
							child.getLivingAddress().getHouseNumber(), child.getLivingAddress().getFlatNumber(), child.getHealthRecordId(), true,
							child.getSecondParentDetails().getId(), child.getSecondParentDetails().getFirstname(),
							child.getSecondParentDetails().getLastname(), child.getSecondParentDetails().getEmail(),
							child.getSecondParentDetails().getPhone(), child.getSecondParentDetails().getPersonalCode(),
							child.getSecondParentDetails().getLivingAddress().getCity(),
							child.getSecondParentDetails().getLivingAddress().getStreet(),
							child.getSecondParentDetails().getLivingAddress().getHouseNumber(),
							child.getSecondParentDetails().getLivingAddress().getFlatNumber(),
							child.getSecondParentDetails().getNumberOfKids(),
							child.getSecondParentDetails().isStudying(),
							child.getSecondParentDetails().getStudyingInstitution(),
							child.getSecondParentDetails().isHasDisability(),
							child.getSecondParentDetails().isDeclaredResidenceSameAsLiving(),
							child.getSecondParentDetails().getDeclaredAddress().getCity(),
							child.getSecondParentDetails().getDeclaredAddress().getStreet(),
							child.getSecondParentDetails().getDeclaredAddress().getHouseNumber(),
							child.getSecondParentDetails().getDeclaredAddress().getFlatNumber()));
				} else {
					finalChildren.add(new CreateChildCommand(child.getId(), child.getFirstname(), child.getLastname(),
							child.getPersonalCode(), child.isAdopted(), child.getBirthdate(),
							child.getLivingAddress().getCity(), child.getLivingAddress().getStreet(),
							child.getLivingAddress().getHouseNumber(), child.getLivingAddress().getFlatNumber(), child.getHealthRecordId(), false,
							0L, "", "", "", "", 0L, "", "", "", "", 0, false, "", false, false, "", "", "", ""));
				}
			}
			return finalChildren;
		}
		return null;
	}
	
	
	
	
	@RequestMapping(path = "/getparentdetails", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public CreateDetailsCommand getLoggedParentDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			var user = userService.findByEmail(auth.getName());
			if (user.getParentDetails() != null) {
				var parent = detailsService.getParentDetails(user.getId());
				return new CreateDetailsCommand(parent.getId(), parent.getFirstname(), parent.getLastname(),
						parent.getEmail(), parent.getPhone(), parent.getPersonalCode(),
						parent.getLivingAddress().getCity(), parent.getLivingAddress().getStreet(),
						parent.getLivingAddress().getHouseNumber(), parent.getLivingAddress().getFlatNumber(),
						parent.getNumberOfKids(), parent.isStudying(), parent.getStudyingInstitution(),
						parent.isHasDisability(), parent.isDeclaredResidenceSameAsLiving(),
						parent.getDeclaredAddress().getCity(), parent.getDeclaredAddress().getStreet(),
						parent.getDeclaredAddress().getHouseNumber(), parent.getDeclaredAddress().getFlatNumber());
			}
			return null;
		}
		return null;
	}
	
	
	@RequestMapping(path = "/changePassword", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARENT') or hasRole('ROLE_EDU')")
	public ResponseEntity<String> changePassword(@RequestBody final PasswordChange passwordChange) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			var user = userService.findByEmail(auth.getName());
			return userService.changePassword(user, passwordChange.getOldPassword(), passwordChange.getNewPassword());
		}
		return new ResponseEntity<String>("Vartotojas nerastas. Prisijunkite iš naujo", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(path = "/pdf", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public ResponseEntity<String> uploadHealthRecord(@RequestParam("data") MultipartFile file, @RequestParam("id") long id) {
		return childService.uploadHealthRecord(file, id);
	}
	
	@RequestMapping(path = "/pdf/{childId}/download", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public ResponseEntity<Resource> downloadHealthRecord(@PathVariable("childId") final long childId) {
		DBFile dbfile = childService.getHealthRecord(childId);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dbfile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbfile.getFileName() + "\"")
				.body(new ByteArrayResource(dbfile.getData()));
	}
	
	@RequestMapping(path = "/pdf/{childId}/delete", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public ResponseEntity<String> deleteHealthRecord(@PathVariable("childId") final long childId) {
		return childService.deleteHealthRecord(childId);
	}
	
	@RequestMapping(path = "/status", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT')")
	public UserStatusObject getUserStatus() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return userService.returnLoggedUserStatus(auth.getName());
		} else {
			return null;
		}
	}
	
	@RequestMapping(path = "/statistics", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARENT') or hasRole('ROLE_EDU')")
	public List<KindergartenStatisticsObject> getStatistics() {
		return userService.getStatistics();
	}
	
	@RequestMapping(path = "/logs", method = RequestMethod.GET, params = "page")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public LogTableObject getLogs(@RequestParam int page) {
		return logService.retrieveAllLogs(page, "", "");
	}
	
	@RequestMapping(path = "/logs", method = RequestMethod.GET, params = {"page", "sortby"})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public LogTableObject getLogs(@RequestParam int page, @RequestParam String sortby) {
		return logService.retrieveAllLogs(page, sortby, "");
	}
	
	@RequestMapping(path = "/logs", method = RequestMethod.GET, params = {"page", "sortby", "email"})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public LogTableObject getLogs(@RequestParam int page, @RequestParam String sortby, @RequestParam String email) {
		return logService.retrieveAllLogs(page, sortby, email);
	}
	
	@RequestMapping(path ="/resetpassword", method = RequestMethod.POST, params = "email")
	public ResponseEntity<String> resetPassword(@RequestParam String email) {
		return userService.resetUserPassword(email);
	}
	
	@RequestMapping(path = "/resetpasswordchange", method = RequestMethod.POST)
	public ResponseEntity<String> changePasswordAfterReset(@RequestBody PasswordResetDTO passwordReset) {
		return userService.changeUserPasswordAfterReset(passwordReset);
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> newUserRegistration(@RequestBody RegistrationObject registration) {
		return userService.newUserRegistration(registration);
	}
	
	@RequestMapping(path = "/userdata/download", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_PARENT') or hasRole('ROLE_EDU')")
	public ResponseEntity<Resource> getUserDataArchive() throws IOException {
		var user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType("application/zip"))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + (user.getFirstname() + "_" + user.getLastname()) + ".zip" + "\"")
					.body(new ByteArrayResource(userService.getUserDataArchive(user)));
					
				
	}
	
	@RequestMapping(path = "/archive", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UserArchiveObject> getUserArchive() {
		return archiveService.getUserArchive();
	}
	
	@RequestMapping(path = "/archive/{archiveId}/download", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Resource> getSpecifiedUserArchive(@PathVariable("archiveId") long archiveId) {
		var data = archiveService.downloadArchivedUserData(archiveId);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/zip"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + data.getFilename() + "\"")
				.body(new ByteArrayResource(data.getData()));
	}

}
