package ClienteUDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Bienvenido al solicitador de hora (cliente)");
        int nroSolicitudes;
        while (true) {
            try {
                System.out.print("¿Cuantas solicitudes desea hacer?: ");
                nroSolicitudes = scanner.nextInt();


                DatagramSocket clienteSocket;
                InetAddress address;

                clienteSocket = new DatagramSocket();
                address = InetAddress.getByName("localhost");

                byte[] mensaje_bytes = new byte[256];
                String mensaje = String.valueOf(nroSolicitudes);
                mensaje_bytes = mensaje.getBytes();
                DatagramPacket paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 6000);
                clienteSocket.send(paquete);
                int contador = 0;
                do {

                    byte[] recogerServidor_bytes = new byte[256];
                    DatagramPacket servPaquete = new DatagramPacket(recogerServidor_bytes, 256);
                    clienteSocket.receive(servPaquete);
                    String cadenaMensaje = new String(recogerServidor_bytes).trim();
                    if (!cadenaMensaje.equals("")) {
                        System.out.println(cadenaMensaje);
                        contador++;
                    }
                } while (contador < nroSolicitudes);

            } catch (InputMismatchException e) {
                System.err.println("Error, ¡ingrese un numero! " + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
