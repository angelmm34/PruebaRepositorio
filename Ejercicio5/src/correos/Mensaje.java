package correos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje implements Comparable<Mensaje> {
	private String nombreRemitente;
	private String texto;
	private LocalDateTime fechaHora;

	public Mensaje(String nombreRemitente, String texto) throws IESException {

		if (texto.equals(null)) {
			throw new IESException("No puedes dejar el texto vacio");
		}
		if (nombreRemitente.equals(null)) {
			throw new IESException("No puedes dejar el texto vacio");
		}

		this.nombreRemitente = nombreRemitente;
		this.texto = texto;
		fechaHora = LocalDateTime.now();
	}

	public String getNombreRemitente() {
		return nombreRemitente;
	}

	public String getTexto() {
		return texto;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	@Override
	public String toString() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-uuuu hh:mm");
		return "Mensaje De: " + nombreRemitente + " Texto: " + texto + ", Fecha " + df.format(fechaHora);
	}

	@Override
	public int compareTo(Mensaje distinto) {
		return this.nombreRemitente.compareTo(distinto.nombreRemitente);

	}

}
