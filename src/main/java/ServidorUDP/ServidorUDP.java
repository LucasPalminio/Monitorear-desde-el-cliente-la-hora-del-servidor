package ServidorUDP;

import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
* Monitorear desde el cliente, la hora de servidor, incluidos segundos y milisegundos.
* */
public class ServidorUDP {
    public static void main(String[] args) {
        //////////////////////////////// Reloj ////////////////////////////////
        LocalDateTime reloj = LocalDateTime.now(); // Esta variable obtiene la fecha y hora exacta

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss:SSS"); // Esta variable asigna un determinado formato a algún String, en este caso, la hora, los minutos, los segundos y los milisegundos

        String tiempoActual = reloj.format(formato); // Al aplicar el formato a la fecha y hora actual sobre una variable String se obrtiene la hora en el formato requerido
        ///////////////////////////////////////////////////////////////////////
        DatagramSocket socket;

        try{

            socket = new DatagramSocket(6000);

            String mensaje = "";

            String mensajeComp = "";


            //DatagramPacket paqueteEnv = new DatagramPacket(mensajeBytes,256);

            int puerto;
            InetAddress address;

            do{
                byte[] mensajeBytes = new byte[256]; //Mensaje que recibe, cuantas horas desea el cliente
                byte[] mensaje2_bytes = new byte[256]; //Mensaje que envia, enviar hora con milisegundos
                DatagramPacket paquete = new DatagramPacket(mensajeBytes,256);
                socket.receive(paquete);
                mensaje = new String(mensajeBytes).trim();
                System.out.println(mensaje);

                puerto = paquete.getPort(); // El puerto para enviar la respuesta se obtiene del mismo paquete recibido de la solicitud
                address = paquete.getAddress(); // La dirección IP para enviar la respuesta se obtiene del mismo paquete recibido de la solicitud
                if(mensaje !=null) {
                    for (int i = 0; i < Integer.parseInt(mensaje); i++) { // Se registra el tiempo exacto, se comprime en el mensaje y se envía en un paquete al cliente en forma de un arreglo de bytes
                        reloj = LocalDateTime.now();
                        tiempoActual = reloj.format(formato); // Obtención del tiempo actual
                        mensajeComp = "("+i+") "+tiempoActual; // Se crea el mensaje que irá en el paquete UDP
                        mensaje2_bytes = mensajeComp.getBytes(); // Se obtiene el arreglo de bytes equivalente del mensaje(Lo que se envía)
                        System.out.println(mensajeComp); // Se imprime para el servidor el mensaje que se enviará
                        DatagramPacket paqueteEnv = new DatagramPacket(mensaje2_bytes, mensajeComp.length(), address, puerto); // Se crea el paquete a enviar

                        socket.send(paqueteEnv); // Se envía el paquete a su destino
                    }
                }
            }while(true);

        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
