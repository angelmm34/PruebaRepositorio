package correos;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/*Este es el primer comentario para la prueba
 */
public class Alumno {

	// Atributos
	private String dni;
	private String nombre;

	private LinkedList<Mensaje> buzonEntrada;

	// Constructor
	public Alumno(String dni, String nombre) throws IESException {
		this.dni = dni;
		this.nombre = nombre;
		buzonEntrada = new LinkedList<Mensaje>();

	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre;
	}

	public void enviarMensaje(Alumno destinatario, String texto) throws IESException {
		Mensaje nuevoMensaje = new Mensaje(this.nombre, texto);
		destinatario.buzonEntrada.add(0,nuevoMensaje);

	}

	private String listadoMensajes (LinkedList<Mensaje> mensajes) {
		StringBuilder sb = new StringBuilder();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("DD-MM-yyyy HH:MM");
		int contador = 1;
		for (Mensaje mensaje2 : mensajes) {
			sb.append("Mensaje ").append(contador).append(": ");
			sb.append("De: ").append(mensaje2.getNombreRemitente());
			sb.append(" Texto: ").append(mensaje2.getTexto());
			sb.append(" Fecha y hora: ").append(mensaje2.getFechaHora().format(df));
			sb.append("\n");
			contador++;

		}

		return sb.toString();
		
	}
	public String mensajesDelBuzon(Mensaje mensaje) throws IESException {

		if (buzonEntrada.isEmpty()) {
			throw new IESException("No hay ningun mensaje");
		}

		return listadoMensajes( buzonEntrada);

	}

	public String mensajesDelBuzonOrdenadoPorRemitente() throws IESException {

		
		if (buzonEntrada.isEmpty()) {
			throw new IESException("No hay mensajes para leer.");
		}

		LinkedList<Mensaje> listaOrdenada = new LinkedList<>(buzonEntrada);

		Collections.sort(listaOrdenada);

		
		return listadoMensajes(listaOrdenada);
	}

	public void eliminarMensaje(int orden) throws IESException {

		if (orden < 1 || orden > buzonEntrada.size()) {
			throw new IESException("No existe ese numero de mensaje.");
		}

		buzonEntrada.remove(orden - 1);
	}

	public String mensajesDelBuzonConTexto(String textoABuscar) throws IESException {//ordenado por el texto
		StringBuilder sb = new StringBuilder();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		boolean encontrado = false;

		for (Mensaje mensaje : buzonEntrada) {
			if (mensaje.getTexto().contains(textoABuscar)) {
				sb.append("De: ").append(mensaje.getNombreRemitente());
				sb.append(" Texto: ").append(mensaje.getTexto());
				sb.append(" Fecha y hora: ").append(mensaje.getFechaHora().format(df));
				sb.append("\n");
				encontrado = true;
			}
		}

		if (!encontrado) {
			throw new IESException("No se ha encontrado ningun mensaje con esa frase.");
		}

		return sb.toString();
	}

	
	public String mensajesDeUnDiaOrdenadosPorTexto(LocalDate fechaBuscada) {
		
		LinkedList<Mensaje> lista=new LinkedList<Mensaje>();
		
		//Primero filtrar
		for (Mensaje mensaje: buzonEntrada) {
			if (mensaje.getFechaHora().toLocalDate().equals( fechaBuscada)) {
				lista.add(mensaje);
			}
		}
		//Luego ordenar
		
//		Collections.sort( lista, new Comparator<Mensaje>() {
//
//			@Override
//			public int compare(Mensaje o1, Mensaje o2) {
//				
//				return o1.getTexto().compareTo(o2.getTexto());
//			}
//			
//		});
		
		Collections.sort(lista, new ComparadorPorTexto());
		
		return listadoMensajes(lista);
		
		
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

}
