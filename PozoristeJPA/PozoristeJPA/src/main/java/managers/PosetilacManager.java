package managers;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.EntityManager;

import model.Izvodjenje;
import model.Karta;
import model.Mesto;
import model.Posetilac;

public class PosetilacManager {

	public Posetilac savePosetilac(String ime, String prezime, String email, String username, String password) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Posetilac p = new Posetilac();
			p.setIme(ime);
			p.setPrezime(prezime);
			p.setEmail(email);
			p.setUsername(username);
			p.setPassword(password);
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Karta rezervacija(int idPosetioca, int idIzvodjenja, int idMesto) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			// da li je mesto vec zauzeto

			List<Karta> karta = em
					.createQuery("select k from Karta k where k.mesto.idMesto=:idM and k.izvodjenje.idIzvodjenje=:idI",
							Karta.class)
					.setParameter("idM", idMesto).setParameter("idI", idIzvodjenja).getResultList();
			if (!karta.isEmpty())
				return null;

			Karta k = new Karta();
			Posetilac p = em.find(Posetilac.class, idPosetioca);
			Izvodjenje i = em.find(Izvodjenje.class, idIzvodjenja);
			Mesto m = em.find(Mesto.class, idMesto);

			k.setPosetilac(p);
			k.setIzvodjenje(i);
			k.setMesto(m);
			k.setDatumRezervacije(new Date());

			em.getTransaction().begin();
			em.persist(k);
			em.getTransaction().commit();
			return k;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	private String encryptPassword(String passwordToHash) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		try {
			KeySpec spec = new PBEKeySpec(passwordToHash.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] bytes = factory.generateSecret(spec).getEncoded();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			String generatedPassword = null;
			generatedPassword = sb.toString();

			return generatedPassword;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		PosetilacManager pm = new PosetilacManager();
		// 1.
		String pass = pm.encryptPassword("perinaSifra1");
		Posetilac p = pm.savePosetilac("Pera", "Kojot", "pera.kojot@peramail.com", "perica123", pass);
		if (p != null)
			System.out.println("Posetilac je dodat. Id posetioca je " + p.getIdPosetilac());

		// 4.
		Karta k = pm.rezervacija(2, 2, 5);
		System.out.println();
		if (k != null)
			System.out.println("Uspesna rezervacija, broj karte je " + k.getIdKarta());
		else
			System.out.println("Rezervacija nije moguca. Mesto je vec zauzeto.");

	
	}
}