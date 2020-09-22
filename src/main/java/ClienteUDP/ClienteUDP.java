package ClienteUDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Bienvenido al solicitador de hora (cliente)");
        int nroSolicitudes;
        while(true) {
            try {
                System.out.print("¿Cuantas solicitudes desea hacer?: ");
                nroSolicitudes = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Error, ¡ingrese un numero! "+e.getMessage());
                scanner.nextLine();
            }
        }

        try {
            DatagramSocket clienteSocket;
            InetAddress address;

            clienteSocket = new DatagramSocket();
            address = InetAddress.getByName("localhost");

        byte[] mensaje_bytes = new byte[256];
        String mensaje = String.valueOf(nroSolicitudes);
        mensaje_bytes = mensaje.getBytes();
        DatagramPacket paquete = new DatagramPacket(mensaje_bytes, mensaje.length(),address,6000);
        clienteSocket.send(paquete);

        byte[] recogerServidor_bytes = new byte[256];

        DatagramPacket servPaquete = new DatagramPacket(recogerServidor_bytes, 256);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
