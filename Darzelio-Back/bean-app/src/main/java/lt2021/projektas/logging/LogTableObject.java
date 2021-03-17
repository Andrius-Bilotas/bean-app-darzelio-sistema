package lt2021.projektas.logging;

import java.util.List;

public class LogTableObject {
	
	private int currentPage;
	private int totalPages;
	private long totalLogs;
	private List<LogTableItem> logs;
	
	public LogTableObject() {
		// TODO Auto-generated constructor stub
	}

	public LogTableObject(int currentPage, int totalPages, long totalLogs, List<LogTableItem> logs) {
		super();
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalLogs = totalLogs;
		this.logs = logs;
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

	public long getTotalLogs() {
		return totalLogs;
	}

	public void setTotalLogs(long totalLogs) {
		this.totalLogs = totalLogs;
	}

	public List<LogTableItem> getLogs() {
		return logs;
	}

	public void setLogs(List<LogTableItem> logs) {
		this.logs = logs;
	}
	
	
	
}
