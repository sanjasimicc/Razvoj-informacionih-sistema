package pozoriste.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.PozoristeUser;

@Repository
@Transactional
public interface PozoristeUserRepository extends JpaRepository<PozoristeUser, Integer> {
	PozoristeUser findByUsername(String username);
}
