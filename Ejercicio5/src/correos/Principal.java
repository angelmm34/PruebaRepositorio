package correos;


import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {
        
        System.out.println("=====================================");
        System.out.println("   SISTEMA DE CORREOS - PRUEBAS");
        System.out.println("=====================================\n");

        try {
            // 1. Crear varios alumnos
            System.out.println("[+] Creando alumnos...");
            Alumno ana = new Alumno("11111111A", "Ana");
            Alumno carlos = new Alumno("22222222B", "Carlos");
            Alumno lucia = new Alumno("33333333C", "Lucia");

            // 2. Mandar mensajes entre ellos
            System.out.println("[+] Enviando mensajes al buzón de Ana...\n");
            carlos.enviarMensaje(ana, "Hola Ana, ¿tienes los apuntes?");
            lucia.enviarMensaje(ana, "Ana, acuérdate de la reunión de hoy.");
            carlos.enviarMensaje(ana, "Por cierto, el examen es mañana.");
            
            // Enviamos un mensaje a Carlos para que su buzón no esté vacío
            ana.enviarMensaje(carlos, "Sí, luego te los paso a tu correo.");

            // 3. Probar métodos de lectura (sobre el buzón de Ana)
            System.out.println("--- BUZÓN DE ANA (mensajesDelBuzon) ---");
            // Nota: Tu método pide un parámetro Mensaje aunque internamente no lo usa. 
            // Pasamos null para que compile y funcione.
            System.out.println(ana.mensajesDelBuzon(null));

            System.out.println("--- BUZÓN DE ANA ORDENADO POR REMITENTE ---");
            System.out.println(ana.mensajesDelBuzonOrdenadoPorRemitente());

            // 4. Buscar mensajes por texto
            System.out.println("--- BUSCAR MENSAJES CON LA PALABRA 'examen' ---");
            System.out.println(ana.mensajesDelBuzonConTexto("examen"));

            // 5. Buscar mensajes del día de hoy ordenados por texto
            System.out.println("\n--- MENSAJES DE HOY ORDENADOS POR TEXTO ---");
            System.out.println(ana.mensajesDeUnDiaOrdenadosPorTexto(LocalDate.now()));

            // 6. Eliminar un mensaje
            System.out.println("--- ELIMINANDO EL MENSAJE 1 DE ANA ---");
            ana.eliminarMensaje(1);
            System.out.println("Buzón actualizado:");
            System.out.println(ana.mensajesDelBuzon(null));

            // 7. Forzar la excepción de IESException (buzón vacío)
            System.out.println("--- LEYENDO BUZÓN DE LUCÍA (Debería fallar por estar vacío) ---");
            System.out.println(lucia.mensajesDelBuzon(null));

        } catch (IESException e) {
            // Aquí capturamos las excepciones que lanzan tus métodos (como la del buzón vacío)
            System.out.println( e.getMessage());
        } 
    }
}