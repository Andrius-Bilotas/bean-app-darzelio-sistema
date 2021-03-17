package lt2021.projektas.errors;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(java.util.Date date, String message, String details) {
		super();
		this.timestamp = (Date) date;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
