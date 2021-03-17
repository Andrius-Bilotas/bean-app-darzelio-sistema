package lt2021.projektas.userRegister;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	Optional<User> searchByEmail(String email);
	
	@Query("select user from User user where user.email like :email%")
	Page<User> filterByEmail(@Param("email") String email, Pageable pageable);
	
}
