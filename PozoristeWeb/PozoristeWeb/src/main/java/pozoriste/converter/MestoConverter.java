package pozoriste.converter;

import org.springframework.core.convert.converter.Converter;

import model.Mesto;
import pozoriste.repository.MestoRepository;

public class MestoConverter implements Converter<String, Mesto> {

MestoRepository mr;
	
	public MestoConverter(MestoRepository mr) {
		this.mr = mr;
	}
	
	@Override
	public Mesto convert(String source) {
		int mestoid = -1;
		try {
			mestoid = Integer.parseInt(source);
		} catch (NumberFormatException nfe) {
			return null;
		}
		Mesto mesto = mr.findById(mestoid).get();
		return mesto;
	}
	
}
