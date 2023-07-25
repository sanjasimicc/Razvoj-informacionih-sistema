package pozoriste.converter;

import org.springframework.core.convert.converter.Converter;

import model.Posetilac;
import pozoriste.repository.PosetilacRepository;

public class PosetilacConverter implements Converter<String, Posetilac> {
	
	PosetilacRepository pr;
	
	public PosetilacConverter(PosetilacRepository pr) {
		this.pr = pr;
	}
	@Override
	public Posetilac convert(String source) {
		int posetilacid = -1;
		try {
			posetilacid = Integer.parseInt(source);
		} catch (NumberFormatException nfe) {
			return null;
		}
		Posetilac posetilac = pr.findById(posetilacid).get();
		return posetilac;
	}
	
}