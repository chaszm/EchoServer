import java.io.*;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.Socket;

//client gets message from user and sends message to reverse echo server
//when reversed message arrives to server it displays message to user

//if user wants to stop client program, they type end.
// this will make client send message to server, and then WAITS for the message "dne" from server
// if client recieves dne message, terminates

public class EchoClient {
    public static void main(String[] args){

     Socket sock = null;
     InputStreamReader input = null;
     OutputStreamWriter output = null;


     BufferedReader bufferR = null;
        BufferedWriter bufferW = null;



        try{
            sock = new Socket("localhost", 5555);

            input = new InputStreamReader(sock.getInputStream());
            output = new OutputStreamWriter(sock.getOutputStream());

            bufferR = new BufferedReader(input);
            bufferW = new BufferedWriter(output);

            Scanner sc = new Scanner(System.in);  //input from keyboard

            while(true){  //always runs

            String message = sc.nextLine();                 //SENDING MESSAGE TO SERVER
            bufferW.write(message);
            bufferW.newLine();
            bufferW.flush(); // flush when enter key

            message = bufferR.readLine();
            System.out.println("Server: " + message); // message from SERVER (reversed)





            if(message.equalsIgnoreCase("dne"))  //if message from server is "dne", break out
                break;

            }
        }

        catch (IOException e) {                //error handling
            throw new RuntimeException(e);
        }finally{
            try{
                if(sock != null)
                    sock.close();
                if(input != null)
                    input.close();
                if(output != null)
                    output.close();
                if (bufferR != null)
                    bufferR.close();
                if (bufferW != null)
                    bufferW.close();
            } catch (IOException e){

                e.printStackTrace();

            }
        }


    }

}
