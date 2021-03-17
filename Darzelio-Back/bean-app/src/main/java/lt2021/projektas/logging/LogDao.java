package lt2021.projektas.logging;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LogDao extends JpaRepository<Log, Long> {
	
	@Query("select log from Log log where log.user like :email%")
	Page<Log> findByEmail(@Param("email") String email, Pageable pageable);

}
