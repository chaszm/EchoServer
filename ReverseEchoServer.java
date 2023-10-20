import java.io.*;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.Socket;
import java.net.ServerSocket;

//Echo Server
// Receives string message from client
//displays message, then reverses and sends back to client
//terminates when reversed message is "dne"

public class ReverseEchoServer {
    public static void main(String[] args) throws IOException {

        Socket sock = null;
        InputStreamReader input = null;
        OutputStreamWriter output = null;
        BufferedReader bufferR = null;
        BufferedWriter bufferW = null;

        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(5555); // port number of client must match

        while(true){

            try{
               sock = serverSocket.accept();

               input = new InputStreamReader(sock.getInputStream());
               output = new OutputStreamWriter(sock.getOutputStream());

               bufferR = new BufferedReader(input);
               bufferW = new BufferedWriter(output);


               while(true){

                   String message = bufferR.readLine();

                   System.out.println("client: " + message);

                    char charIndex;
                    String reverse = "";

                   for (int i=0; i<message.length(); i++){  //reverses the message from client

                       charIndex = message.charAt(i); //index i of the string
                       reverse = charIndex+reverse;
                   }





                   bufferW.write(reverse); // make server send REVERSE message
                   bufferW.newLine();
                   bufferW.flush();





                   if(reverse.equalsIgnoreCase("dne")) { //if message is dne, break out of while
                       break;
                   }
               }

                System.exit(0); //exit server



            } catch (IOException e){
                e.printStackTrace();
            }
        }




    }
}
