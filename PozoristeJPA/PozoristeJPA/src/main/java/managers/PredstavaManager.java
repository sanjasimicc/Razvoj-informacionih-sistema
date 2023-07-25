package managers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import model.Glumac;
import model.Glumi;
import model.Izvodjenje;
import model.Mesto;
import model.Predstava;
import model.Uloga;
import model.ZanrPredstave;

public class PredstavaManager {

	public List<Izvodjenje> vratiIzvodjenja(String naslov){
		EntityManager em = JPAUtil.getEntityManager();
		List<Izvodjenje> izvodjenja = em.createQuery("select i from Izvodjenje i where i.predstava.naziv like :naslovP", Izvodjenje.class).
			setParameter("naslovP", naslov).getResultList();
		return izvodjenja;
	}
	
	public List<Predstava> vratiPredstaveGlumca(String ime, String prezime){
		EntityManager em = JPAUtil.getEntityManager();
		List<Predstava> predstave = em.createQuery("select p from Predstava p where p in"
				+ " (select g.uloga.predstava from Glumi g where g.glumac.ime like :ime and g.glumac.prezime like :prezime)", Predstava.class).
			setParameter("ime", ime).setParameter("prezime", prezime).
			getResultList();
		return predstave;
	}
	
	public List<Mesto> getSlobodnaMesta(Date datumIzvodjenja, String nazivPredstave) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Mesto> mesta = em
				.createQuery(
						"select m from Mesto m inner join m.scena.izvodjenjes i "
								+ "where i.predstava.naziv like :naziv " + "and i.datum = :datumIzv "
								+ " and not exists (select k from Karta k where k.mesto=m  " + " and k.izvodjenje=i)",
						Mesto.class)
				.setParameter("naziv", nazivPredstave).setParameter("datumIzv", datumIzvodjenja).getResultList();
		return mesta;
	}
	
	public Glumac dodajGlumca(String ime, String prezime) {
		try {
			EntityManager em =JPAUtil.getEntityManager();
			Glumac g = new Glumac();
			g.setIme(ime);
			g.setPrezime(prezime);
			em.getTransaction().begin();
			em.persist(g);
			em.getTransaction().commit();
			return g;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Predstava> getSvePredstave() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Predstava> predstave = em.createQuery("from Predstava p", Predstava.class).getResultList();
		return predstave;
	}
	
	public List<Uloga> getUlogePredstave(Integer idP){
		EntityManager em = JPAUtil.getEntityManager();
		Predstava p = em.find(Predstava.class, idP);
		List<Uloga> ulogePredstave = em.createQuery("select u from Uloga u where u.predstava = :p", Uloga.class).setParameter("p", p).getResultList();
		return ulogePredstave;
	}


	public Glumi dodajUloguGlumca(Integer idUloga, Integer idGlumac) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Uloga u = em.find(Uloga.class, idUloga);
			Glumac g = em.find(Glumac.class, idGlumac);
			Glumi glumi = new Glumi();
			glumi.setGlumac(g);
			glumi.setUloga(u);
			em.getTransaction().begin();
			em.persist(glumi);
			em.getTransaction().commit();
			return glumi;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Izvodjenje> prikazipredstojecaIzvodjenjaPredstave(String naziv){
		EntityManager em = JPAUtil.getEntityManager();
		Date danas = new Date();
		List<Izvodjenje> izvodjenja = em.createQuery("select i from Izvodjenje i where i.predstava.naziv like :naziv"
				+ " and i.datum > :d",
				Izvodjenje.class).setParameter("naziv", naziv)
				.setParameter("d", danas).getResultList();
		return izvodjenja;
	}

	public List<Mesto> getSlobodnaMesta(Integer idIzvodjenje) {
		EntityManager em = JPAUtil.getEntityManager();
		String upit = "select m from Mesto m inner join m.scena.izvodjenjes i "
				+ "where i.idIzvodjenje=:idI and not exists"
				+ "(select k from Karta k where k.izvodjenje.idIzvodjenje = :idI and "
				+ "k.mesto=m)";
		List<Mesto> mesta = em.createQuery(upit, Mesto.class).setParameter("idI", idIzvodjenje).getResultList();
		return mesta;
	}
	
	public List<Mesto> getSlobodnaMesta1(Integer idIzvodjenje) {
		EntityManager em = JPAUtil.getEntityManager();
		Izvodjenje i = em.find(Izvodjenje.class, idIzvodjenje);
		String upit = "select m from Mesto m "
				+ "where m.scena.idScena = :idSc and m not in "
				+ "(select k.mesto from Karta k where "
				+ "k.izvodjenje=:i)";
		List<Mesto> mesta = em.createQuery(upit, Mesto.class).setParameter("idSc", i.getScena().getIdScena()).setParameter("i", i).getResultList();
		return mesta;
	}

	
	public static void main(String[] args) {
		PredstavaManager pm = new PredstavaManager();
		//2.
		List<Izvodjenje> izvodjenja = pm.vratiIzvodjenja("Laza i paralaza");
		System.out.println("Izvodjenja za predstavu sa naslovom Laza i paralaza:");
		for(Izvodjenje i:izvodjenja) {
			System.out.println("datum: "+i.getDatum()+", scena: "+i.getScena().getNaziv());
		}
		
		//3.
		List<Predstava> predstave = pm.vratiPredstaveGlumca("Mirjana", "Miric");
		System.out.println();
		System.out.println("Predstave Mirjane Miric: ");
		for(Predstava p:predstave) {
			System.out.print("Naziv:"+p.getNaziv()+", trajanje: "+p.getTrajanje()+", "
					+"reziser: "+p.getReziser().getIme()+" "+p.getReziser().getPrezime());
			System.out.print(" zanr: ");
			for(ZanrPredstave z: p.getZanrPredstaves()) {
				System.out.print(z.getZanr().getNaziv()+" ");
			}
			System.out.println();
		}
		
		// Druge Vezbe
		try {
			List<Izvodjenje> izvodjenja1 = pm.vratiIzvodjenja("Laza i paralaza");

			System.out.println("Datumi izvodjenja predstave Laza i paralaza su: ");
			for (Izvodjenje i : izvodjenja1)
				System.out.println(i.getDatum());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date datum = sdf.parse("2021-11-05");
			if (izvodjenja1.size() > 0) {
				List<Mesto> slobodnaMesta = pm.getSlobodnaMesta(datum, "Laza i paralaza");
				System.out.println("Prikaz slobodnih mesta za predstavu Laza i paralaza, dana " + datum + ":");
				for (Mesto m : slobodnaMesta)
					System.out.println(
							m.getIdMesto() + " " + m.getBroj() + ", red " + m.getRed() + ",sekcija " + m.getSekcija());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		List<Izvodjenje> izv = pm.prikazipredstojecaIzvodjenjaPredstave("Laza i paralaza");
		System.out.println(izv.size());
		
	}

}