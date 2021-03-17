package lt2021.projektas.kindergarten.admission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionDao extends JpaRepository<AdmissionProcess, Long> {

}
