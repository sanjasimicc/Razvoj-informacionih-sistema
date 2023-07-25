package pozoriste.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Izvodjenje;
import model.Predstava;
import model.Scena;

public interface IzvodjenjeRepository extends JpaRepository<Izvodjenje, Integer> {
	
	List<Izvodjenje> findByPredstava(Predstava p);
	List<Izvodjenje> findByDatum(Date datum);
	List<Izvodjenje> findByScena(Scena s);
}
