package lt2021.projektas.kindergarten.queue;

import java.util.List;

public class RegistrationTableObject {
	
	private int currentPage;
	private int totalPages;
	private long totalRegistrations;
	private List<RegistrationTableItem> registrations;
	
	public RegistrationTableObject() {
		// TODO Auto-generated constructor stub
	}

	public RegistrationTableObject(int currentPage, int totalPages, long totalRegistrations, List<RegistrationTableItem> registrations) {
		super();
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalRegistrations = totalRegistrations;
		this.registrations = registrations;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalRegistrations() {
		return totalRegistrations;
	}

	public void setTotalRegistrations(long totalRegistrations) {
		this.totalRegistrations = totalRegistrations;
	}

	public List<RegistrationTableItem> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<RegistrationTableItem> registrations) {
		this.registrations = registrations;
	}

	
	
	

}
