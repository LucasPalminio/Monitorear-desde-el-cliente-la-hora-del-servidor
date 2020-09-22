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
        LocalDateTime reloj = LocalDateTime.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

        String tiempoActual = reloj.format(formato);
        ///////////////////////////////////////////////////////////////////////
        DatagramSocket socket;

        try{

            socket = new DatagramSocket(6000);
            byte[] mensajeBytes = new byte[256];
            String mensaje = "";
            mensaje = new String(mensajeBytes);
            String mensajeComp = "";

            DatagramPacket paquete = new DatagramPacket(mensajeBytes,256);
            DatagramPacket paqueteEnv = new DatagramPacket(mensajeBytes,256);

            int puerto;
            InetAddress address;
            byte[] mensaje2_bytes = new byte[256];
            do{
                socket.receive(paquete);
                mensaje = new String(mensajeBytes).trim();
                System.out.println(mensaje);

                puerto = paquete.getPort();
                address = paquete.getAddress();
                if(mensaje !=null) {
                    for (int i = 0; i < Integer.parseInt(mensaje); i++) {
                        reloj = LocalDateTime.now();
                        tiempoActual = reloj.format(formato);
                        mensajeComp = tiempoActual;
                        mensaje2_bytes = mensajeComp.getBytes();
                        paqueteEnv = new DatagramPacket(mensaje2_bytes, mensajeComp.length(), address, puerto);

                        socket.send(paqueteEnv);
                    }
                }

                /*
                mensaje2_bytes = mensajeComp.getBytes();
                paqueteEnv = new DatagramPacket(mensaje2_bytes,mensajeComp.length(),address,puerto);

                socket.send(paqueteEnv);
                */
            }while(true);

        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
