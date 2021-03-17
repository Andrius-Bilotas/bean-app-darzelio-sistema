package lt2021.projektas.parentdetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentDetailsDao extends JpaRepository<ParentDetails, Long> {
	
	Optional<ParentDetails> findByPersonalCode(long personalCode);
	
}
