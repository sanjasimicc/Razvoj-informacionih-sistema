package pozoriste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Predstava;
import model.ZanrPredstave;

public interface ZanrPredstaveRepository extends JpaRepository<ZanrPredstave, Integer>{
	public List<ZanrPredstave> findByPredstava(Predstava p);
}
