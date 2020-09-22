package ClienteUDP;

import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) {
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
