package newspaper.advertisement.system.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * CustomExceptionHandler
 *
 */

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleException(Exception e) {
		//LOG.error("handleAdvertiseNotFoundException");
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("errorMessage", e.getMessage());
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException ae) { // Application
																									// exceptions are
																									// handled

		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("Error", "Category not found");
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(AdvertiseNotFoundException.class)
	public ResponseEntity<?> handleAdvertiseNotFoundException(AdvertiseNotFoundException ae) { // Application
																									// exceptions are
																									// handled

		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("Error", "Advertisement not found");
		//errorBody.put("Details", ae.getMessage());
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException ae) { // Application
																									// exceptions are
																									// handled

		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("Error", "Data not found");
		//errorBody.put("Details", ae.getMessage());
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?> handleInvalidTokenException(InvalidTokenException ae) { // Application
																									// exceptions are
																									// handled

		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("Error", "Invalid Token ");
		//errorBody.put("Details", ae.getMessage());
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);

	}
	

}
