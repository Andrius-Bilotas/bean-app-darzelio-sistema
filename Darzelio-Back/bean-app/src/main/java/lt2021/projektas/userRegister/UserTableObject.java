package lt2021.projektas.userRegister;

import java.util.List;

import lt2021.projektas.kindergarten.queue.RegistrationTableItem;

public class UserTableObject {
	
	private int currentPage;
	private int totalPages;
	private long totalUsers;
	private List<ServiceLayerUser> users;
	
	public UserTableObject() {
		// TODO Auto-generated constructor stub
	}

	public UserTableObject(int currentPage, int totalPages, long totalUsers, List<ServiceLayerUser> users) {
		super();
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalUsers = totalUsers;
		this.users = users;
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

	public long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public List<ServiceLayerUser> getUsers() {
		return users;
	}

	public void setUsers(List<ServiceLayerUser> users) {
		this.users = users;
	}
	
	
	
}
