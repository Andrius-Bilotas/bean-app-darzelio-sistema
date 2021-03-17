package lt2021.projektas.logging;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {
	
	@Autowired
	private LogDao logDao;
	
	@Transactional
	public LogTableObject retrieveAllLogs(int pageNumber, String sortby, String email) {
		LogTableObject logObject = new LogTableObject();
		if ((sortby.length() == 0 && email.length() == 0) || (sortby.equals("datedesc") && email.length() == 0)) {
			var logs = logDao.findAll(PageRequest.of(pageNumber - 1, 20, Sort.by(Sort.Order.desc("date"))));
			logObject.setTotalPages(logs.getTotalPages());
			logObject.setTotalLogs(logs.getTotalElements());
			logObject.setCurrentPage(pageNumber);
			List<LogTableItem> logList = logs.toList().stream().map(log -> new LogTableItem(log.getId(), log.getUser(), log.getRole(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getDate()), log.getAction())).collect(Collectors.toList());
			logObject.setLogs(logList);
			return logObject;
		} else if (sortby.equals("dateasc") && email.length() == 0) {
			var logs = logDao.findAll(PageRequest.of(pageNumber - 1, 20, Sort.by(Sort.Order.asc("date"))));
			logObject.setTotalPages(logs.getTotalPages());
			logObject.setTotalLogs(logs.getTotalElements());
			logObject.setCurrentPage(pageNumber);
			List<LogTableItem> logList = logs.toList().stream().map(log -> new LogTableItem(log.getId(), log.getUser(), log.getRole(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getDate()), log.getAction())).collect(Collectors.toList());
			logObject.setLogs(logList);
			return logObject;
		} else if ((email.length() > 0 && sortby.length() == 0) || (email.length() > 0 && sortby.equals("datedesc"))) {
			var logs = logDao.findByEmail(email, PageRequest.of(pageNumber - 1, 20, Sort.by(Sort.Order.desc("date"))));
			logObject.setTotalPages(logs.getTotalPages());
			logObject.setTotalLogs(logs.getTotalElements());
			logObject.setCurrentPage(pageNumber);
			List<LogTableItem> logList = logs.toList().stream().map(log -> new LogTableItem(log.getId(), log.getUser(), log.getRole(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getDate()), log.getAction())).collect(Collectors.toList());
			logObject.setLogs(logList);
			return logObject;
		} else if (email.length() > 0 && sortby.equals("dateasc")) {
			var logs = logDao.findByEmail(email, PageRequest.of(pageNumber - 1, 20, Sort.by(Sort.Order.asc("date"))));
			logObject.setTotalPages(logs.getTotalPages());
			logObject.setTotalLogs(logs.getTotalElements());
			logObject.setCurrentPage(pageNumber);
			List<LogTableItem> logList = logs.toList().stream().map(log -> new LogTableItem(log.getId(), log.getUser(), log.getRole(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getDate()), log.getAction())).collect(Collectors.toList());
			logObject.setLogs(logList);
			return logObject;
		} else if (email.length() > 0) {
			var logs = logDao.findByEmail(email, PageRequest.of(pageNumber - 1, 20, Sort.by(Sort.Order.desc("date"))));
			logObject.setTotalPages(logs.getTotalPages());
			logObject.setTotalLogs(logs.getTotalElements());
			logObject.setCurrentPage(pageNumber);
			List<LogTableItem> logList = logs.toList().stream().map(log -> new LogTableItem(log.getId(), log.getUser(), log.getRole(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getDate()), log.getAction())).collect(Collectors.toList());
			logObject.setLogs(logList);
			return logObject;
		} else {
			return null;
		}
	}

}
