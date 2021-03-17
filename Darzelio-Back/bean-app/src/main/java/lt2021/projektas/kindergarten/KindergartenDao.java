package lt2021.projektas.kindergarten;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KindergartenDao extends JpaRepository<Kindergarten, Long> {
	
	Optional<Kindergarten> findByName(String name);

}
