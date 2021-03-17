package lt2021.projektas.passwordreset;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetTokenDao extends JpaRepository<PasswordResetToken, String> {
	

}
