package lt2021.projektas.kindergarten.queue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lt2021.projektas.kindergarten.Kindergarten;

public interface QueueDao extends JpaRepository<KindergartenQueue, Long> {
	
	
	@Query("select queue from KindergartenQueue queue where queue.kindergarten = :kindergarten and queue.ageGroup = :ageGroup")
	Optional<KindergartenQueue> findQueueByKindergartenNameAndAgeGroup(@Param("kindergarten") Kindergarten kindergarten, @Param("ageGroup") AgeGroup ageGroup);
	

}
