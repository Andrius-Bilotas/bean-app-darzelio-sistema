package lt2021.projektas.errors;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lt2021.projektas.logging.Log;
import lt2021.projektas.logging.LogDao;

@RestController
@ControllerAdvice
public class ErrorResponse extends ResponseEntityExceptionHandler {
	
	@Autowired
	private LogDao logDao;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
	protected ResponseEntity<Object> handleQLIntegrityConstraintViolation(HttpServletRequest req, Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Item already exists", ex.getMessage());
		String roleName = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		roleName = roleName.substring(6);
		String roleForLog = roleName.substring(0, roleName.length() - 1);
		logDao.save(new Log(new Date(), SecurityContextHolder.getContext().getAuthentication().getName(),
				roleForLog, "Bloga registracija. Bandyta registruotis naudojant jau egzistuojančius duomenis"));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(HttpServletRequest req, Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid field entry", ex.getMessage());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ParseException.class)
	protected ResponseEntity<Object> handleParseException(HttpServletRequest req, Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Bad birthdate format", ex.getMessage());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	protected ResponseEntity<Object> handleMaxUploadSizeExceededException(HttpServletRequest req, Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "File upload size cap reached", ex.getMessage());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}