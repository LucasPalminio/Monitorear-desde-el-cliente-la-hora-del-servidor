package ServidorTCP;

import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
* Monitorear desde el cliente, la hora de servidor, incluidos segundos y milisegundos.
* */
public class ServidorTCP {
    public static void main(String[] args) {
        LocalDateTime reloj = LocalDateTime.now();


        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

        String tiempoActual = reloj.format(formato);

        System.out.println("Hello world");

        System.out.println(tiempoActual);

        ServerSocket servidorHora;

        try{
            servidorHora = new ServerSocket(50000);

            Socket horaSocket = servidorHora.accept();
            do{
                reloj = LocalDateTime.now();
                tiempoActual = reloj.format(formato);
            }while(true);
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
