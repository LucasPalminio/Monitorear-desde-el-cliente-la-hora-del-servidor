package ClienteUDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ClienteUDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Bienvenido al solicitador de hora (cliente)");
        int nroSolicitudes;
        while (true) {
            try {
                System.out.print("¿Cuantas solicitudes desea hacer?: ");
                nroSolicitudes = scanner.nextInt(); // Número de horas que se desea recibir


                DatagramSocket clienteSocket;
                InetAddress address; // Dirección IP del cliente

                clienteSocket = new DatagramSocket();
                address = InetAddress.getByName("localhost"); // Se asigna la IP local

                byte[] mensaje_bytes = new byte[256];
                String mensaje = String.valueOf(nroSolicitudes);
                mensaje_bytes = mensaje.getBytes();
                DatagramPacket paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 6000);
                clienteSocket.send(paquete);
                int contador = 0;
                clienteSocket.setSoTimeout(100);
                try {
                    do {

                        byte[] recogerServidor_bytes = new byte[256];
                        DatagramPacket servPaquete = new DatagramPacket(recogerServidor_bytes, 256);
                        clienteSocket.receive(servPaquete); // Se recibe la respuesta
                        String cadenaMensaje = new String(recogerServidor_bytes).trim(); /* Se almacena la respuesta
                                                                                         en una variable String */
                        if (!cadenaMensaje.equals("")) {
                            System.out.println(cadenaMensaje); // Se imprime la hora recibida del servidor
                            contador++;
                        }
                    } while (contador < nroSolicitudes);
                }catch(SocketTimeoutException e){
                    System.out.println("No se ha podido conectar al servidor");
                    clienteSocket.close();
                }
            } catch (InputMismatchException e) { /* Este mensaje de error se despliega en caso de no introducir un
                                           número para enviar, pero no afecta a la continuidad misma del programa */
                System.err.println("Error, ¡ingrese un numero! " + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) { /* En caso de sufrir cualquier otro error, se despliega el mensaje de error y
                                     el programa se cierra */
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
