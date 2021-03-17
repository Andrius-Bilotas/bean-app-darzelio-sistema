package lt2021.projektas.userRegister.archive;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserArchiveService {
	
	@Autowired
	private UserArchiveDao archiveDao;
	
	@Transactional
	public List<UserArchiveObject> getUserArchive() {
		return archiveDao.findAll().stream().map(item -> new UserArchiveObject(item.getId(), item.getEmail(), item.getFilename(),
				new SimpleDateFormat("yyyy-MM-dd").format(item.getDeletionDate()).toString())).collect(Collectors.toList());
	}
	
	@Transactional
	public UserArchive downloadArchivedUserData(long archiveId) {
		var archiveData = archiveDao.findById(archiveId).orElse(null);
		if (archiveData != null) {
			return archiveData;
		} else {
			return null;
		}
	}

}
