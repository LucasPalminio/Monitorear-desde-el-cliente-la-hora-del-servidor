package ClienteUDP;

import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

        Socket clienteSocket;
        try{
            clienteSocket = new Socket("127.0.0.1",50000);
            do{
                System.out.println();
            }while(true);
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
