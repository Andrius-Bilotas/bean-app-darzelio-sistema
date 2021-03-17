package lt2021.projektas.child;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildDao extends JpaRepository<Child, Long> {

	Optional<Child> findByPersonalCode(long personalCode);
	
}
