package pl.sda;

import java.io.*;
import java.net.Socket;

public class Client_2 {

    public static void main(String...args){
        //TO JEST KLIENT _2

        System.out.println();

        String toSend = "Cześć uczę się JAVY bo chce";

        try {
            Socket socket = new Socket("localhost",5555);

            final InputStream inputStream = socket.getInputStream();
            final OutputStream outputStream = socket.getOutputStream();

            final BufferedReader readFromSocket = new BufferedReader(
                    new InputStreamReader(inputStream)
            );

            final DataOutputStream writeToSocket =
                    new DataOutputStream(outputStream);

            writeToSocket.writeBytes("state2toServer_2\r");

            writeToSocket.close();
            readFromSocket.close();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
