package correos;
import java.util.Comparator;

public class ComparadorPorTexto implements Comparator<Mensaje>{

	@Override
	public int compare(Mensaje o1, Mensaje o2) {
		
		return o1.getTexto().compareTo(o2.getTexto());
	}
}
